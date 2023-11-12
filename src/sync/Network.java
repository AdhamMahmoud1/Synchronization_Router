package sync;

import java.util.ArrayList;
import java.util.Scanner;

public class Network {
     public static void main(String[] args) throws InterruptedException {
        int  numberOfConnections, numberOfDecives;
        ArrayList<Device> devices = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        System.out.println("What is number of WI-FI Connections?");
        numberOfConnections = input.nextInt();
        Router router = new Router(numberOfConnections);

        System.out.println("What is number of devices Clients want to connect?");
        numberOfDecives = input.nextInt();

        input.nextLine();

        for (int i = 0; i < numberOfDecives; i++) {
            System.out.println("Enter device name:");
            String deviceName = input.nextLine();
            System.out.println("Enter device type:");
            String deviceType = input.nextLine();
            Device newDevice = new Device(deviceName, deviceType, numberOfConnections);
            devices.add(newDevice);
        }

        for (int i = 0; i < numberOfDecives; i++) {
            Thread.sleep(1000);
            devices.get(i).start();
        }
    }

}
