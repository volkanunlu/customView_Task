package com.volkanunlu.ecommerceapp.models;

public class CartModel {

    String orderDate;
    String orderTime;
    String productName;
    String productPrice;
    int totalCount;
    int totalPrice;

    public CartModel() {
    }

    public CartModel(String orderDate, String orderTime, String productName, String productPrice, int totalCount, int totalPrice) {
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.productName = productName;
        this.productPrice = productPrice;
        this.totalCount = totalCount;
        this.totalPrice = totalPrice;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

}
