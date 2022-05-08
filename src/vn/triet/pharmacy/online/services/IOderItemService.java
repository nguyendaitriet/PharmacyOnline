package vn.triet.pharmacy.online.services;

import vn.triet.pharmacy.online.models.OrderItem;

import java.util.ArrayList;
import java.util.List;

public interface IOderItemService {
    List<OrderItem> getOrderItems();

    void addMoreOrderItems(ArrayList<OrderItem> newOrderItemList);

    List<OrderItem> getUserOrderItemList(long orderID);
}
