package vn.triet.pharmacy.online.utils.uml;

import java.util.ArrayList;
import java.util.Objects;

public class OrderItem {
    private long id;
    private long orderID;
    private long drugID;
    private String drugName;
    private double drugContent;
    private double pricePerPill;
    private int quantity;
    private double totalPrice;
    private long creationTime;

    void add(ArrayList<vn.triet.pharmacy.online.models.OrderItem> newOrderItemList) {

    }

}
