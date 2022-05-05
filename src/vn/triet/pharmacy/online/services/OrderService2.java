package vn.triet.pharmacy.online.services;

import vn.triet.pharmacy.online.models.Order2;
import vn.triet.pharmacy.online.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderService2 implements IOrderService2 {
    private static final String path = "data/orders.csv";

    @Override
    public List<Order2> getOrders() {
        List<Order2> orderList = new ArrayList<>();
        List<String> records = CSVUtils.readData(path);
        for (String record : records) {
            orderList.add(new Order2(record));
        }
        return orderList;
    }

    @Override
    public void add(Order2 newOrder) {
        List<Order2> orderList = getOrders();
        orderList.add(newOrder);
        CSVUtils.writeData(path, orderList);
    }

    @Override
    public void update() {

    }

    @Override
    public Order2 getOrderById(long id) {
        List<Order2> orderList = getOrders();
        for (Order2 order : orderList) {
            if (order.getId() == id)
                return order;
        }
        return null;
    }

    @Override
    public void remove(Order2 order) {
        List<Order2> orderList = getOrders();
        orderList.remove(order);
        CSVUtils.writeData(path, orderList);
    }
}
