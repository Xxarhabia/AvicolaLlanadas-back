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
    private double quantity;

    @Column(name = "unit_price")
    private double unitPrice;

    @Column(name = "type_sale")
    private String typeSale;
}
