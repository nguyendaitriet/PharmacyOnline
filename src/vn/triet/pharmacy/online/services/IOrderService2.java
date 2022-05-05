package vn.triet.pharmacy.online.services;

import vn.triet.pharmacy.online.models.Order2;

import java.util.List;

public interface IOrderService2 {
    List<Order2> getOrders();

    void add(Order2 newOrder);

    void update();

    Order2 getOrderById(long id);

    void remove(Order2 order);

}
