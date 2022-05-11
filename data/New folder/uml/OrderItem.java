package vn.triet.pharmacy.online.uml;

import vn.triet.pharmacy.online.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;
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

    public List<vn.triet.pharmacy.online.models.OrderItem> getOrderItems() {
        List<vn.triet.pharmacy.online.models.OrderItem> orderItemList = new ArrayList<>();
        List<String> records = CSVUtils.readData("String");
        for (String record : records) {
            orderItemList.add(new vn.triet.pharmacy.online.models.OrderItem(record));
        }
        return orderItemList;
    }


    public void addMoreOrderItems(ArrayList<vn.triet.pharmacy.online.models.OrderItem> newOrderItemList) {
        List<vn.triet.pharmacy.online.models.OrderItem> orderItemList = getOrderItems();
        orderItemList.addAll(newOrderItemList);
        CSVUtils.writeData("String", orderItemList);
    }


    public List<vn.triet.pharmacy.online.models.OrderItem> getUserOrderItemList(long orderID) {
        List<vn.triet.pharmacy.online.models.OrderItem> userOrderItemList = new ArrayList<>();
        for (vn.triet.pharmacy.online.models.OrderItem orderItem : getOrderItems()) {
            if (orderItem.getOrderID() == orderID) {
                userOrderItemList.add(orderItem);
            }
        }
        return userOrderItemList;
    }




}
