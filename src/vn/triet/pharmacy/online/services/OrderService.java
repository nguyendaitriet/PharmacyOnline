package vn.triet.pharmacy.online.services;

import vn.triet.pharmacy.online.models.Order;
import vn.triet.pharmacy.online.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    private static final String path = "data/orders.csv";

    @Override
    public List<Order> getOrders() {
        List<Order> orderList = new ArrayList<>();
        List<String> records = CSVUtils.readData(path);
        for (String record : records) {
            orderList.add(new Order(record));
        }
        return orderList;
    }

    @Override
    public void add(Order newOrder) {
        List<Order> orderList = getOrders();
        orderList.add(newOrder);
        CSVUtils.writeData(path, orderList);
    }

    @Override
    public void update() {

    }

    @Override
    public Order getOrderById(long id) {
        List<Order> orderList = getOrders();
        for (Order order : orderList) {
            if (order.getId() == id)
                return order;
        }
        return null;
    }

    @Override
    public void remove(Order order) {
        List<Order> orderList = getOrders();
        orderList.remove(order);
        CSVUtils.writeData(path, orderList);
    }
}
