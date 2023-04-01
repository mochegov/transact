package mochegov.transact.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ACCOUNT_NUMBER", columnDefinition = "VARCHAR(20) NOT NULL")
    private String accountNumber;

    @Column(name = "REST")
    private BigDecimal rest;

    public Account() {}

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
        this.rest = new BigDecimal(0);
    }
}