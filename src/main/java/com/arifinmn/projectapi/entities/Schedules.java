package com.arifinmn.projectapi.entities;

import com.arifinmn.projectapi.configs.constans.Statuses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "customers_id", referencedColumnName = "id", unique = true)
    private Customers customer_id;

    @Enumerated(EnumType.STRING)
    private Statuses status;
}
