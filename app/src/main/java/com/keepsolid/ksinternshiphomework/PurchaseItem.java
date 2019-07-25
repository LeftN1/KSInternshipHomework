package com.keepsolid.ksinternshiphomework;

import java.util.Objects;

public class PurchaseItem {

    private boolean isDone;
    private String name;
    private Units measurement;
    private Double qtty;
    private Double price;
    private Double total;
    private String details;



    public PurchaseItem(boolean isDone, String name, Units measurement, Double qtty, Double price, String details) {
        this.isDone = isDone;
        this.name = name;
        this.measurement = measurement;
        this.qtty = qtty;
        this.price = price;
        this.total = qtty * price;
        this.details = details;
    }

    public PurchaseItem(boolean isDone, String name, Units measurement, Double qtty, String details) {
        this(isDone, name, measurement, qtty, 0.0, details);
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Units getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Units measurement) {
        this.measurement = measurement;
    }

    public Double getQtty() {
        return qtty;
    }

    public void setQtty(Double qtty) {
        this.qtty = qtty;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseItem that = (PurchaseItem) o;
        return isDone == that.isDone &&
                name.equals(that.name) &&
                measurement == that.measurement &&
                Objects.equals(qtty, that.qtty) &&
                Objects.equals(price, that.price) &&
                Objects.equals(total, that.total);
    }
}
