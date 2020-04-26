package com.example.fakefilesystem.models;

import org.json.JSONArray;
import org.json.JSONObject;

public class DisplayModel {
    private String name;
    private String type;
    private Object items;
    //private String items;
    private String content;

    public DisplayModel(String name, String type, Object items, String content) {
        this.name = name;
        this.type = type;
        this.items = items;
        this.content = content;
    }

    @Override
    public String toString() {
        return "DisplayModel{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", items='" + items + '\'' +
                ", content='" + content + '\'' +
                '}';
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

    public Object getItems() {
        return items;
    }

    public void setItems(Object items) {
        this.items = items;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
