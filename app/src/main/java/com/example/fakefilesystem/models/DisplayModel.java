package com.example.fakefilesystem.models;

public class DisplayModel {
    private String fileName;
    private String type;
    private String items;
    private String content;

    public DisplayModel(String fileName, String type, String items, String content) {
        this.fileName = fileName;
        this.type = type;
        this.items = items;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
