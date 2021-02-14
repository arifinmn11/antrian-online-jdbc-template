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
@Table(name = "services")
public class Schedules {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    @JoinColumn(name = "customers_id", referencedColumnName = "id")
    private Customers customer_id;

    @Enumerated(EnumType.STRING)
    private Statuses status;
}
