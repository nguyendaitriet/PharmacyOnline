package vn.triet.pharmacy.online.models;

import java.util.Objects;

public class Drug {
    private long id;
    private String drugName;
    private double drugContent;
    private int quantity;
    private String dosageForm;
    private String usage;
    private String dosagePerDay;
    private int totalDosage5Days;
    private double pricePerPill;
    private String productionDate;
    private String expirationDate;
    private String note;

    public Drug() {}

    public Drug(String raw) {
        String[] drugInformation = raw.split("~");
        this.id = Long.parseLong(drugInformation[0]);
        this.drugName = drugInformation[1];
        this.drugContent = Double.parseDouble(drugInformation[2]);
        this.quantity = Integer.parseInt(drugInformation[3]);
        this.dosageForm = drugInformation[4];
        this.usage = drugInformation[5];
        this.dosagePerDay = drugInformation[6];
        this.totalDosage5Days = Integer.parseInt(drugInformation[7]);
        this.pricePerPill = Double.parseDouble(drugInformation[8]);
        this.productionDate = drugInformation[9];
        this.expirationDate = drugInformation[10];
        this.note = drugInformation[11];
    }

    public String getDosagePerDay() {
        return dosagePerDay;
    }

    public void setDosagePerDay(String dosagePerDay) {
        this.dosagePerDay = dosagePerDay;
    }

    public int getTotalDosage5Days() {
        return totalDosage5Days;
    }

    public void setTotalDosage5Days(int totalDosage5Days) {
        this.totalDosage5Days = totalDosage5Days;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public double getDrugContent() {
        return drugContent;
    }

    public void setDrugContent(double drugContent) {
        this.drugContent = drugContent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerPill() {
        return pricePerPill;
    }

    public void setPricePerPill(double pricePerPill) {
        this.pricePerPill = pricePerPill;
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
        return id +
                "~" + drugName +
                "~" + drugContent +
                "~" + quantity +
                "~" + dosageForm +
                "~" + usage +
                "~" + dosagePerDay +
                "~" + totalDosage5Days +
                "~" + pricePerPill +
                "~" + productionDate +
                "~" + expirationDate +
                "~" + note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drug drug = (Drug) o;
        return drugContent == drug.drugContent && drugName.equalsIgnoreCase(drug.drugName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drugName, drugContent, productionDate);
    }
}
