package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "sales_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaleDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long detailId;

    // FIX: antes sale apuntaba a sale_detail; ahora sale_detail apunta a sale (correcto)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sale_id", referencedColumnName = "sale_id", nullable = false)
    private SaleEntity sale;

    // Nullable: solo uno de los dos estará presente por venta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", referencedColumnName = "food_id")
    private FoodEntity food;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bird_lot_id", referencedColumnName = "lot_id")
    private BirdLotEntity birdLot;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    // subtotal es GENERATED STORED en la BD; insertable=false, updatable=false
    // para que JPA no intente escribirlo
    @Column(precision = 12, scale = 2, insertable = false, updatable = false)
    private BigDecimal subtotal;
}
