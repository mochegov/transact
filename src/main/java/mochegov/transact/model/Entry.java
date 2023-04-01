package mochegov.transact.model;

import lombok.Getter;
import lombok.Setter;
import mochegov.transact.enums.EntryState;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ENTRY_STATE", nullable = false)
    @Enumerated(EnumType.STRING)
    private EntryState entryState;

    @Column(name = "DATE_ENTRY", columnDefinition = "DATE NOT NULL")
    private Date date;

    @Column(name = "SUM_ENTRY", nullable = false)
    private BigDecimal sum;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "DEBIT_ACCOUNT_ID", nullable = false)
    private Account debitAccount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "CREDIT_ACCOUNT_ID", nullable = false)
    private Account creditAccount;

    @Column(name = "PURPOSE", columnDefinition = "VARCHAR(128) NOT NULL")
    private String purpose;

    public Entry() {}

    public Entry(Date date, BigDecimal sum, Account debitAccount, Account creditAccount, String purpose) {
        this.entryState = EntryState.NEW;
        this.date = date;
        this.sum = sum;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.purpose = purpose;
    }
}
