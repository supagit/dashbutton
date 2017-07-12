package com.supa.dash;

import org.pcap4j.core.*;
import org.pcap4j.packet.Packet;

import java.net.InetAddress;
import java.util.List;

public class DashButtonListener {

    public void listen(DashButton dashButton) throws Exception {
        String filter = "ether proto 0x0806 and ether host " + dashButton.getMacAddress(); // 0x0806 == ARP

        String ip = "192.168.178.27";
//        String ip = "2003:c0:abd0:ed00:211:32ff:fe13:9c8b";

        System.out.println("getByName");
        InetAddress localhost = InetAddress.getByName(ip);
        System.out.println("getDevByAddress " + localhost);
        PcapNetworkInterface nif = Pcaps.getDevByAddress(localhost);
        System.out.println("Listening now");

        final PcapHandle handle = nif.openLive(65536, PcapNetworkInterface.PromiscuousMode.PROMISCUOUS, 10);
        handle.setFilter(filter, BpfProgram.BpfCompileMode.OPTIMIZE);
        PacketListener listener = new PacketListener() {
            public void gotPacket(Packet packet) {
                printPacket(packet, handle);
                System.out.println("Dash Button Pushed!");
            }
        };
        handle.loop(1, listener);
    }

    private static void printPacket(Packet packet, PcapHandle ph) {
        StringBuilder sb = new StringBuilder();
        sb.append("A packet captured at ")
                .append(ph.getTimestamp())
                .append(":");
        System.out.println(sb);
        System.out.println(packet);
    }

}
