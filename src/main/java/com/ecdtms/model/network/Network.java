package com.ecdtms.model.network;
// ******************************************
// hierarchy of devices in the network
// Network Class - Represents the entire network infrastructure - Composition of Devices - very important class that ties everything together
import java.util.ArrayList;
import java.util.List;

public class Network {

    private String networkName;
    private List<Device> devices; //أي نوع Device (Server / Workstation / Firewall)

    public Network(String networkName) {
        this.networkName = networkName;
        this.devices = new ArrayList<>(); // Composition - Network has a list of Devices creating arraylist to hold devices in the network
    }

// Device -> Firewall, Server, Workstation / MAIN class
// devices -> private variable 
// device -> parameter in addDevice method

    public void addDevice(Device device) {
        devices.add(device);
        System.out.println("Device " + device.getDeviceId()
        + " added to network: " + networkName);
    }
// very important method that demonstrates how devices interact with each other through the network, and also shows how we can treat all devices as the base type (Device) but still have specific behaviors based on their actual type (Firewall, Server, Workstation)
    public void transmit(Device from, Device to, String data) {

        if (from.getStatus() != DeviceStatus.ACTIVE ||
            to.getStatus() != DeviceStatus.ACTIVE) {
            System.out.println("Transmission blocked: inactive device");
            return;
        }

        System.out.println("Network transmitting data..."); // receiver and sender devices must be active to transmit data

     // very important to understand that we can treat all devices as the base type (Device) but still have specific behaviors based on their actual type (Firewall, Server, Workstation)
    for (Device d : devices) {            
        if (d instanceof Firewall) {       
        Firewall firewall = (Firewall) d; //Downcasting to access Firewall - حوّل d من Device إلى Firewall 
        firewall.logActivity(); // أي Firewall في الشبكة لازم يعرف إن في data transmission حصل
        //instanceof Firewall ?
        //cast to Firewall 
       //then logActivity() record the transmission in the firewall logs
       // الموظف اللي بيبعت الطرد
       // الموظف اللي بيستقبل الطرد
       // Firewall قسم الأمن اللي: يسجل كل حركة دخول وخروج 
       // loop → check firewalls → log activity → send data → receive data 
    }
}
        from.sendData(data);
        to.receiveData(data);
    }

    public void scanNetwork() {
        System.out.println("Scanning entire network: " + networkName);

        for (Device d : devices) {
            d.scanVulnerabilities(); // كل جهاز يعمل فحص ثغرات حسب نوعه (polymorphism) 
        }
    }

    public void displayAllDevices() {
        System.out.println("=== Network: " + networkName + " ===");

        for (Device d : devices) {
            System.out.println(d); // طباعة بيانات الجهاز
        }
    }

    public List<Device> getDevices() {
        return devices;
    }
}