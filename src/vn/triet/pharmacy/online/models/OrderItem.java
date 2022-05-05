package vn.triet.pharmacy.online.models;

public class OrderItem {
    private long id;
    private long drugID;
    private long orderID;
    private String drugName;
    private double drugContent;
    private double pricePerPill;
    private int quantity;
    private double totalPrice;
    private long creationTime;

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDrugID() {
        return drugID;
    }

    public void setDrugID(long drugID) {
        this.drugID = drugID;
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

    public double getPricePerPill() {
        return pricePerPill;
    }

    public void setPricePerPill(double pricePerPill) {
        this.pricePerPill = pricePerPill;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }
}
