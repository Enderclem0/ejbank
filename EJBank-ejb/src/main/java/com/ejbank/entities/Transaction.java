package com.ejbank.entities;

import javax.persistence.*; // Notez le '*' pour inclure GenerationType
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "ejbank_transaction")
public class Transaction implements Serializable {

    // 1. CLÉ PRIMAIRE AVEC GÉNÉRATION
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "date")
    private Date date;
    @Column(name = "account_id_from")
    private Long source;
    @Column(name = "account_id_to")
    private Long destination;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "author")
    private Long author;
    @Column(name = "comment")
    private String comment;
    @Column(name = "applied")
    private Boolean applied;

    public Transaction() {
    }

    public Transaction(Date date, Long source, Long destination, BigDecimal amount, Long author, String comment, Boolean applied) {
        this.date = date;
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.author = author;
        this.comment = comment;
        this.applied = applied;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Long getSource() {
        return source;
    }
    public void setSource(Long source) {
        this.source = source;
    }
    public Long getDestination() {
        return destination;
    }
    public void setDestination(Long destination) {
        this.destination = destination;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Long getAuthor() {
        return author;
    }
    public void setAuthor(Long author) {
        this.author = author;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Boolean getApplied() {
        return applied;
    }
    public void setApplied(Boolean applied) {
        this.applied = applied;
    }
}