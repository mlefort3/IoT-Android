package com.example.tp1;

import java.io.Serializable;

public class Mote implements Serializable {

    public int id;
    public String ipaddress;
    public String macaddress;
    public double lat;
    public double lon;

    public Mote(int id, String ipaddress, String macaddress, double lat, double lon) {
        this.id = id;
        this.ipaddress = ipaddress;
        this.macaddress = macaddress;
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public String getMacaddress() {
        return macaddress;
    }

    public void setMacaddress(String macaddress) {
        this.macaddress = macaddress;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }





}
