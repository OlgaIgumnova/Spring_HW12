package ru.gb.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "acts")
public class Act {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String reportingPeriod;
    @Column(nullable = false)
    private String projectSection;
    private Double price;
    @Column(nullable = false)
    private String status;

}
