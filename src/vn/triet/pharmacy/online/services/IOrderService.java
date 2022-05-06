package vn.triet.pharmacy.online.services;

import vn.triet.pharmacy.online.models.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getOrders();

    void add(Order newOrder);

    void update();

    Order getOrderById(long id);

    void remove(Order order);


}
