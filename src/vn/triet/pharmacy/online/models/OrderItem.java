package vn.triet.pharmacy.online.models;


import java.util.Objects;

public class OrderItem {
    private long id;
    private long orderID;
    private long drugID;
    private String drugName;
    private double drugContent;
    private double pricePerPill;
    private int quantity;
    private long creationTime;

    public OrderItem() {}

    public OrderItem(String record) {
        String[] orderItems = record.split("~");
        id = Long.parseLong(orderItems[0]);
        orderID = Long.parseLong(orderItems[1]);
        drugID = Long.parseLong(orderItems[2]);
        drugName = orderItems[3];
        drugContent = Double.parseDouble(orderItems[4]);
        pricePerPill = Double.parseDouble(orderItems[5]);
        quantity = Integer.parseInt(orderItems[6]);
        creationTime = Long.parseLong(orderItems[7]);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderID() {
        return orderID;
    }

    public void setOrderID(long orderID) {
        this.orderID = orderID;
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

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return id +
                "~" + orderID +
                "~" + drugID +
                "~" + drugName +
                "~" + drugContent +
                "~" + pricePerPill +
                "~" + quantity +
                "~" + creationTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem that = (OrderItem) o;
        return Double.compare(that.drugContent, drugContent) == 0 && drugName.equals(that.drugName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drugName, drugContent);
    }

}
