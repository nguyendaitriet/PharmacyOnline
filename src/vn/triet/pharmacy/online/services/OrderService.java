//package vn.triet.pharmacy.online.services;
//
//import vn.triet.pharmacy.online.models.Order;
//import vn.triet.pharmacy.online.models.OrderItem;
//
//import java.io.*;
//
//import java.util.ArrayList;
//
//import java.util.Map;
//
//public class OrderService implements IOderService, Serializable {
//    public static String path = "data/orderList.csv";
//
//
//    @Override
//    public void writeToFile(Map<Order, ArrayList<OrderItem>> orderList) {
//        try {
//            FileOutputStream fos = new FileOutputStream(path);
//            ObjectOutputStream oos = new ObjectOutputStream(fos);
//            oos.writeObject(orderList);
//            oos.close();
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public Map<Order, ArrayList<OrderItem>> readDataFromFile() {
//        Map<Order, ArrayList<OrderItem>> orderList = null;
//        try {
//            FileInputStream fis = new FileInputStream(path);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            orderList = (Map<Order, ArrayList<OrderItem>>) ois.readObject();
//            fis.close();
//            ois.close();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return orderList;
//    }
//
//    @Override
//    public void addOrder(Order oder, ArrayList<OrderItem> orderItemsList) {
//        Map<Order, ArrayList<OrderItem>> orderList = readDataFromFile();
//        orderList.put(oder,orderItemsList);
//        writeToFile(orderList);
//    }
//}
