package com.msara.GestionAvicolaLlanadas.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "client_id"
    )
    private ClientEntity client;
}
