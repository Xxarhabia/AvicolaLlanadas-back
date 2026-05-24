package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "food", schema = "avicola_schema")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long foodId;

    // FIX: era "type" sin mapeo explícito; ahora type_food con UNIQUE
    @Column(name = "type_food", nullable = false, unique = true, length = 50)
    private String typeFood;

    @Column(name = "available_quantity", nullable = false, precision = 12, scale = 2)
    @Builder.Default
    private BigDecimal availableQuantity = BigDecimal.ZERO;

    @Column(name = "unit_measurement", nullable = false, length = 10)
    private String unitMeasurement;

    // NUEVO: precio unitario de venta
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal unitPrice = BigDecimal.ZERO;

    // NUEVO: stock mínimo para alertas automáticas
    @Column(name = "min_stock", nullable = false, precision = 12, scale = 2)
    @Builder.Default
    private BigDecimal minStock = BigDecimal.ZERO;

    // NUEVO: indica si el alimento también se vende al público
    @Column(name = "is_for_sale", nullable = false)
    @Builder.Default
    private Boolean isForSale = false;

    @Column(name = "date_insert", nullable = false)
    @Builder.Default
    private LocalDate dateInsert = LocalDate.now();
}
