package com.supa.dash;

public class Main {

    private final static DashButton oralB = new DashButton("OralB", "50:F5:DA:3B:A4:81");

    public static void main(String[] args) throws Exception {
        System.out.println("start");
        new DashButtonListener().listen(oralB);
    }
}
