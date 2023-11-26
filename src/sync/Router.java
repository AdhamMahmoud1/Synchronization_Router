package sync;

import static java.lang.Thread.sleep;

public class Router {
    public boolean[] connected;
    public int maxDevices, currentConnectedDevices;
    public CounterSemaphore semaphore;

    Router(int maxDevices) {
        this.maxDevices = maxDevices;
        semaphore = new CounterSemaphore(maxDevices);
        connected = new boolean[maxDevices];
    }

    public synchronized int connect(Device device) throws InterruptedException {
        for (int i = 0; i < maxDevices; i++) {
            if(!connected[i]){
                currentConnectedDevices++;
                device.connectionID = i + 1;
                connected[i] = true;
                sleep((int) (Math.random() * 100));
                break;
            }
        }
        return device.connectionID;
    }

    public synchronized void disconnect(Device device){
        currentConnectedDevices--;
        connected[device.connectionID-1] = false;
        notify();
        System.out.println("Connection " + device.connectionID + ": " + device.name + " Logged out");
    }

}