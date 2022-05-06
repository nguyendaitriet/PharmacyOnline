package vn.triet.pharmacy.online.services;

import vn.triet.pharmacy.online.models.OrderItem;
import vn.triet.pharmacy.online.utils.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderItemService implements IOderItemService {

    private static final String path = "data/orderItems.csv";

    @Override
    public List<OrderItem> getOrderItems() {
        List<OrderItem> orderItemList = new ArrayList<>();
        List<String> records = CSVUtils.readData(path);
        for (String record : records) {
            orderItemList.add(new OrderItem(record));
        }
        return orderItemList;
    }

//    @Override
//    public void add(OrderItem2 newOrderItem) {
//        List<OrderItem2> orderItemList = getOrderItems();
//        orderItemList.add(newOrderItem);
//        CSVUtils.writeData(path,orderItemList);
//    }

    @Override
    public void addMoreOrderItems(ArrayList<OrderItem> newOrderItemList) {
        List<OrderItem> orderItemList = getOrderItems();
        orderItemList.addAll(newOrderItemList);
        CSVUtils.writeData(path,orderItemList);
    }

    @Override
    public void update() {

    }
}
