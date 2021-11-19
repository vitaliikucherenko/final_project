package com.nixsolutions.app;

public enum BrowserTypes {

    chrome("chrome"),
    edge("edge");

    private final String type;

    BrowserTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static BrowserTypes init(String type) {
        switch (type) {
            case "chrome" : return chrome;
            case "edge" : return edge;
        }
        return null;
    }
}
