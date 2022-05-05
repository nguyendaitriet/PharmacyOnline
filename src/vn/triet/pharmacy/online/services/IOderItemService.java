package vn.triet.pharmacy.online.services;

import vn.triet.pharmacy.online.models.OrderItem2;

import java.util.List;

public interface IOderItemService {
    List<OrderItem2> getOrderItems();

    void add(OrderItem2 newOrderItem);

    void update();
}
