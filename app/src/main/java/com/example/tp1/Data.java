package com.example.tp1;

import java.security.Timestamp;

public class Data {

    public Data(long timestamp, String label, double value, String moteAddress) {
        this.timestamp = timestamp;
        this.label = label;
        this.value = value;
        this.moteAddress = moteAddress;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getMoteAddress() {
        return moteAddress;
    }

    public void setMoteAddress(String moteAddress) {
        this.moteAddress = moteAddress;
    }

    public long timestamp;
    public String label;
    public double value;
    public String moteAddress;
}
