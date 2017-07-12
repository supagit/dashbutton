package com.supa.dash;

public class DashButton {

    private String name;
    private String macAddress;

    public DashButton(String name, String macAddress) {
        this.name = name;
        this.macAddress = macAddress;
    }

    public String getName() {
        return name;
    }

    public String getMacAddress() {
        return macAddress;
    }
}
