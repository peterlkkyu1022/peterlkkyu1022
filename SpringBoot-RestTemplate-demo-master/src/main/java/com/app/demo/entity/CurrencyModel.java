package com.app.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "currency")
public class CurrencyModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cur")
    private String cur;

    @Column(name = "curdesc")
    private String curdesc;

    @Column(name = "exchangerate")
    private BigDecimal exchangerate;

    public CurrencyModel(String cur, String curdesc, BigDecimal exchangerate) {
        this.cur = cur;
        this.curdesc = curdesc;
        this.exchangerate = exchangerate;
    }

    public CurrencyModel() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public String getCurdesc() {
        return curdesc;
    }

    public void setCurdesc(String curdesc) {
        this.curdesc = curdesc;
    }

    public BigDecimal getExchangerate() {
        return exchangerate;
    }

    public void setExchangerate(BigDecimal exchangerate) {
        this.exchangerate = exchangerate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", cur='" + cur + '\'' +
                ", curdesc='" + curdesc + '\'' +
                ", exchangerate=" + exchangerate +
                '}';
    }
}
