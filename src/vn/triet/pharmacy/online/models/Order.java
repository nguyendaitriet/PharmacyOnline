package vn.triet.pharmacy.online.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Order {
    private long id;
    private long userId;
    private String name;
    private String phoneNumber;
    private String address;
    private long creationTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", creationTime=" + creationTime +
                '}';
    }

    public static void main(String[] args) {
        Map<Order, List<OrderItem>> newList = new HashMap<>();
//        newList.put(15,new Drug("112617~Paracetamol~500.0~900~tablet~Headache,Fever~1 Morning, 1 Afternoon, 1 Night~15~500.0~06/11/2021~06/11/2022~Pain Reliever"));
//        newList.put(17,new Drug("112617~Paracetamol~500.0~900~tablet~Headache,Fever~1 Morning, 1 Afternoon, 1 Night~15~500.0~06/11/2021~06/11/2022~Pain Reliever"));
//        System.out.println(newList.get(15).getId());
        System.out.println(newList.keySet());
    }
}
