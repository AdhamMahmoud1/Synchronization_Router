package sync;

import java.util.concurrent.Semaphore;

public class Router {
    private Boolean[] connections;
    private int maxConnections;
    private static int currentConnections = 0;

    private Network network;
    private Semaphore semaphore;

    public Router(int maxConnections) {
        this.maxConnections = maxConnections;
        this.currentConnections = 0;
        this.connections = new Boolean[maxConnections];
        connections[0] = false;
        this.network = new Network();
        this.semaphore = new Semaphore(maxConnections);
    }

    public void connect(Device device) throws InterruptedException {
        for (int i = 0; i < maxConnections; i++) {
            if (!connections[i]) {
                currentConnections++;
                connections[i] = true;
                Thread.sleep(1000);
                break;
            }
        }
        semaphore.acquire();
    }

    public void disconnect(Device device) {
            connections[device.ID - 1] = false;
            currentConnections--;
            semaphore.release();
            System.out.println(device.getDeviceName() + " (" + device.getDeviceType() + ")" + " is logged out");

    }



    public int getCurrentConnections() {
        return currentConnections;
    }



}
