package com.daneshnaik.picturawalls.classes;

public class main_page {
    String Image_name;
    String url;

    public main_page(String image_name, String url) {
        Image_name = image_name;
        this.url = url;
    }

    public main_page() {
    }

    public String getImage_name() {
        return Image_name;
    }

    public void setImage_name(String image_name) {
        Image_name = image_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
