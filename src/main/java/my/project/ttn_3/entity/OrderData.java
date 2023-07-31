package my.project.ttn_3.entity;

import java.time.LocalDate;
import java.util.List;

public class OrderData {
    private String name;
    private String address;
    private String route;
    private String accessKey;
    private LocalDate date;
    private List<Integer> orders;

    public OrderData() {
    }

    public OrderData(String name, String address, String route, String accessKey, LocalDate date, List<Integer> orders) {
        this.name = name;
        this.address = address;
        this.route = route;
        this.accessKey = accessKey;
        this.date = date;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Integer> getOrders() {
        return orders;
    }

    public void setOrders(List<Integer> orders) {
        this.orders = orders;
    }
}
