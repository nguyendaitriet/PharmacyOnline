package vn.triet.pharmacy.online.uml;

import vn.triet.pharmacy.online.utils.CSVUtils;
import vn.triet.pharmacy.online.utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private long userId;
    private String name;
    private String phoneNumber;
    private String address;
    private double grandTotal;
    private long creationTime;

    public List<vn.triet.pharmacy.online.models.Order> getOrders() {
        List<vn.triet.pharmacy.online.models.Order> orderList = new ArrayList<>();
        List<String> records = CSVUtils.readData("string");
        for (String record : records) {
            orderList.add(new vn.triet.pharmacy.online.models.Order(record));
        }
        return orderList;
    }

    public void add(vn.triet.pharmacy.online.models.Order newOrder) {
        List<vn.triet.pharmacy.online.models.Order> orderList = getOrders();
        orderList.add(newOrder);
        CSVUtils.writeData("string", orderList);
    }


    public vn.triet.pharmacy.online.models.Order getOrderById(long id, List<vn.triet.pharmacy.online.models.Order> orderList) {
        for (vn.triet.pharmacy.online.models.Order order : orderList) {
            if (order.getId() == id)
                return order;
        }
        return null;
    }


    public List<vn.triet.pharmacy.online.models.Order> getSearchOrderList(String searchContent, List<vn.triet.pharmacy.online.models.Order> userOrdersList) {
        List<vn.triet.pharmacy.online.models.Order> searchOrderList = new ArrayList<>();
        for (vn.triet.pharmacy.online.models.Order order : userOrdersList) {
            String orderPlus = order.toString() + ValidateUtils.convertMilliToDate(order.getCreationTime());
            if (orderPlus.toLowerCase().contains(searchContent)) {
                searchOrderList.add(order);
            }
        }
        return searchOrderList;
    }

    public List<vn.triet.pharmacy.online.models.Order> getUserOrdersList(long id) {
        List<vn.triet.pharmacy.online.models.Order> userOrderList = new ArrayList<>();
        for (vn.triet.pharmacy.online.models.Order userOrder : getOrders()) {
            if (userOrder.getUserId() == id) {
                userOrderList.add(userOrder);
            }
        }
        return userOrderList;
    }

}
