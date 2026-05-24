package com.msara.GestionAvicolaLlanadas.application.services.impl;

import com.msara.GestionAvicolaLlanadas.adapters.dto.request.SaleDetailRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.request.SaleRequest;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.GeneralResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.SaleDetailResponse;
import com.msara.GestionAvicolaLlanadas.adapters.dto.response.SaleResponse;
import com.msara.GestionAvicolaLlanadas.application.exceptions.BusinessException;
import com.msara.GestionAvicolaLlanadas.application.services.SalesService;
import com.msara.GestionAvicolaLlanadas.domain.entities.*;
import com.msara.GestionAvicolaLlanadas.domain.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final SaleRepository saleRepository;
    private final SaleDetailRepository saleDetailRepository;
    private final ClientRepository clientRepository;
    private final FoodRepository foodRepository;
    private final BirdLotRepository birdLotRepository;

    @Override
    public GeneralResponse recordSale(SaleRequest request) {
        // 1. Validar tipo de venta
        String saleType = request.saleType().toUpperCase();
        if (!List.of("AVES", "ALIMENTO").contains(saleType)) {
            throw new BusinessException("TIPO_VENTA_INVALIDO",
                    "Tipo de venta inválido. Use: AVES o ALIMENTO");
        }

        // 2. Validar método de pago
        String paymentMethod = request.paymentMethod().toUpperCase();
        if (!List.of("EFECTIVO", "TRANSFERENCIA", "CREDITO").contains(paymentMethod)) {
            throw new BusinessException("PAGO_INVALIDO",
                    "Método de pago inválido. Use: EFECTIVO, TRANSFERENCIA o CREDITO");
        }

        // 3. Obtener cliente
        ClientEntity client = clientRepository.findById(request.clientId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cliente #" + request.clientId() + " no encontrado"));

        if (!client.getIsActive()) {
            throw new BusinessException("CLIENTE_INACTIVO", "El cliente está inactivo");
        }

        // 4. Crear cabecera de venta
        // total_amount lo calcula el trigger T5 automáticamente al insertar detalles
        SaleEntity sale = SaleEntity.builder()
                .client(client)
                .saleType(saleType)
                .paymentMethod(paymentMethod)
                .status("COMPLETADA")
                .build();

        saleRepository.save(sale);

        // 5. Registrar detalles
        // Los triggers T3b (aves) y T7 (alimento) validan y descuentan stock en BD.
        // Si hay stock insuficiente, PostgreSQL lanza excepción y hace rollback.
        for (SaleDetailRequest detalle : request.details()) {
            validarDetalle(detalle, saleType);

            SaleDetailEntity detail = buildDetail(sale, detalle, saleType);
            saleDetailRepository.save(detail);
        }

        // 6. Recargar la venta para obtener el total calculado por T5
        SaleEntity savedSale = saleRepository.findById(sale.getSaleId())
                .orElseThrow();

        return new GeneralResponse("00", "Venta registrada exitosamente", true,
                toSaleResponse(savedSale));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleResponse> getSales() {
        return saleRepository.findAll().stream().map(this::toSaleResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public GeneralResponse getSaleById(Long id) {
        SaleEntity sale = saleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta #" + id + " no encontrada"));
        return new GeneralResponse("00", "Venta encontrada", true, toSaleResponse(sale));
    }

    // ── Helpers ──────────────────────────────────────────────────

    private void validarDetalle(SaleDetailRequest detalle, String saleType) {
        if (saleType.equals("AVES")) {
            if (detalle.birdLotId() == null) {
                throw new BusinessException("DETALLE_INVALIDO",
                        "Para venta de AVES se requiere birdLotId en cada detalle");
            }
            if (detalle.foodId() != null) {
                throw new BusinessException("DETALLE_INVALIDO",
                        "Una venta de AVES no puede incluir alimentos");
            }
        } else {
            if (detalle.foodId() == null) {
                throw new BusinessException("DETALLE_INVALIDO",
                        "Para venta de ALIMENTO se requiere foodId en cada detalle");
            }
            if (detalle.birdLotId() != null) {
                throw new BusinessException("DETALLE_INVALIDO",
                        "Una venta de ALIMENTO no puede incluir lotes de aves");
            }
        }
    }

    private SaleDetailEntity buildDetail(SaleEntity sale, SaleDetailRequest req, String saleType) {
        SaleDetailEntity.SaleDetailEntityBuilder builder = SaleDetailEntity.builder()
                .sale(sale)
                .quantity(req.quantity())
                .unitPrice(req.unitPrice());

        if (saleType.equals("AVES")) {
            BirdLotEntity lot = birdLotRepository.findById(req.birdLotId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Lote #" + req.birdLotId() + " no encontrado"));

            if (!lot.getStatus().equals("ACTIVO")) {
                throw new BusinessException("LOTE_INACTIVO",
                        "El lote #" + req.birdLotId() + " no está activo");
            }

            builder.birdLot(lot);
        } else {
            FoodEntity food = foodRepository.findById(req.foodId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Alimento #" + req.foodId() + " no encontrado"));

            if (!food.getIsForSale()) {
                throw new BusinessException("ALIMENTO_NO_VENDIBLE",
                        "El alimento '" + food.getTypeFood() + "' no está marcado para venta al público");
            }

            builder.food(food);
        }

        return builder.build();
    }

    private SaleResponse toSaleResponse(SaleEntity e) {
        List<SaleDetailResponse> detalles = e.getDetails().stream()
                .map(this::toDetailResponse).toList();

        return new SaleResponse(
                e.getSaleId(),
                e.getClient().getClientId(),
                e.getClient().getFullName(),
                e.getDate(),
                e.getSaleType(),
                e.getTotalAmount(),
                e.getPaymentMethod(),
                e.getStatus(),
                detalles
        );
    }

    private SaleDetailResponse toDetailResponse(SaleDetailEntity e) {
        return new SaleDetailResponse(
                e.getDetailId(),
                e.getFood() != null ? e.getFood().getFoodId() : null,
                e.getFood() != null ? e.getFood().getTypeFood() : null,
                e.getBirdLot() != null ? e.getBirdLot().getLotId() : null,
                e.getBirdLot() != null ? e.getBirdLot().getBirdType() : null,
                e.getQuantity(),
                e.getUnitPrice(),
                e.getSubtotal()
        );
    }
}
