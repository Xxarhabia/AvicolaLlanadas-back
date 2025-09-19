package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sales_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long salesDetailId;

    @Column(name = "product_name")
    private String productName;
    private int quantity;

    @Column(name = "unit_price")
    private double unitPrice;

    @ManyToOne
    @JoinColumn(
            name = "sale_id",
            referencedColumnName = "sale_id"
    )
    private SaleEntity sale;
}
