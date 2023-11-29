package sync;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Network {
    public static void main(String[] args) throws InterruptedException, IOException {
        int  numberOfConnections, numberOfDecives;
        ArrayList<Device> devices = new ArrayList<>();

        Scanner input = new Scanner(System.in);

        System.out.println("What is number of WI-FI Connections?");
        numberOfConnections = input.nextInt();
        Router router = new Router(numberOfConnections);

        System.out.println("What is number of devices Clients want to connect?");
        numberOfDecives = input.nextInt();

        for (int i = 0; i < numberOfDecives; i++) {
            System.out.println("Enter Device Name and Type: ");
            Device newDevice = new Device(input.next(), input.next(), router);
            devices.add(newDevice);
        }
        FileWriter writeToLog = new FileWriter("output.txt", true);
        BufferedWriter writer = new BufferedWriter(writeToLog);
        writer.newLine();
        writer.close();
        for (int i = 0; i < numberOfDecives; i++) {
            sleep((int) (Math.random() * 100));
            devices.get(i).start();
        }
        input.close();
    }
}