package com.volkanunlu.ecommerceapp.models;

public class CategoryModel {

    //Firebase firestoreda yer alan alanlarıma göre değişkenlerimi oluşturdum.Sonrasında constructor ve getter setter metodlarımı çağırdım.

    String img_url;
    String name;
    String type;

    public CategoryModel() {
    }

    public CategoryModel(String img_url, String name, String type) {
        this.img_url = img_url;
        this.name = name;
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
