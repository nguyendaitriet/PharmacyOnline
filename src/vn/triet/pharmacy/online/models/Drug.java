package vn.triet.pharmacy.online.models;

import java.util.Date;
import java.util.Objects;

public class Drug {
    private String id;
    private String drugName;
    private int drugContent;
    private int quantity;
    private double pricePerTablet;
    private String productionDate;
    private String expirationDate;

    public Drug(String id, String drugName, int drugContent, int quantity, double pricePerTablet, String productionDate, String expirationDate) {
        this.id = id;
        this.drugName = drugName;
        this.drugContent = drugContent;
        this.quantity = quantity;
        this.pricePerTablet = pricePerTablet;
        this.productionDate = productionDate;
        this.expirationDate = expirationDate;
    }

    public Drug(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public int getDrugContent() {
        return drugContent;
    }

    public void setDrugContent(int drugContent) {
        this.drugContent = drugContent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerTablet() {
        return pricePerTablet;
    }

    public void setPricePerTablet(double pricePerTablet) {
        this.pricePerTablet = pricePerTablet;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "id=" + id +
                ", drugName='" + drugName + '\'' +
                ", drugContent=" + drugContent +
                ", quantity=" + quantity +
                ", pricePerTablet=" + pricePerTablet +
                ", productionDate='" + productionDate + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Drug other = (Drug) obj;
        return Objects.equals(id, other.id);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


//    @Override
//    public int compareTo(Drug o) {
//        return this.drugName.compareTo(o.drugName);
//    }
}
