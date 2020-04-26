package com.example.fakefilesystem.models;

public class DisplayModel {
    private String name;
    private String type;
    private String items;
    private String content;

    public DisplayModel(String name, String type, String items, String content) {
        this.name = name;
        this.type = type;
        this.items = items;
        this.content = content;
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

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
