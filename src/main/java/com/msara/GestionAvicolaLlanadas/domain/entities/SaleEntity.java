package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sale")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private Long saleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "client_id", nullable = false)
    private ClientEntity client;

    // FIX: era Date, ahora OffsetDateTime para TIMESTAMPTZ
    @Column(nullable = false)
    @Builder.Default
    private OffsetDateTime date = OffsetDateTime.now();

    // NUEVO: tipo de venta AVES | ALIMENTO
    @Column(name = "sale_type", nullable = false, length = 10)
    private String saleType;

    // NUEVO: total calculado automáticamente por trigger T5
    @Column(name = "total_amount", nullable = false, precision = 12, scale = 2)
    @Builder.Default
    private BigDecimal totalAmount = BigDecimal.ZERO;

    // NUEVO: método de pago
    @Column(name = "payment_method", nullable = false, length = 20)
    @Builder.Default
    private String paymentMethod = "EFECTIVO";

    // NUEVO: estado de la venta
    @Column(nullable = false, length = 15)
    @Builder.Default
    private String status = "COMPLETADA";

    // NUEVO: vendedor responsable
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sold_by", referencedColumnName = "user_id")
    private UserEntity soldBy;

    // FIX: la relación estaba invertida; sale_detail apunta a sale, no al revés
    // cascade + orphanRemoval permite guardar/eliminar detalles desde la venta
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<SaleDetailEntity> details = new ArrayList<>();
}
