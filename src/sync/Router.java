package sync;

import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Router {
    public boolean[] connected;
    public int maxDevices, currentConnectedDevices;
    public CounterSemaphore semaphore;
    FileWriter writeToLog;

    Router(int maxDevices) throws IOException {
        this.maxDevices = maxDevices;
        semaphore = new CounterSemaphore(maxDevices);
        connected = new boolean[maxDevices];
        writeToLog = new FileWriter("C:\\Users\\DELL\\IdeaProjects\\Synchronization_Router\\log.txt", true);

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

    public synchronized void disconnect(Device device) throws IOException {
        currentConnectedDevices--;
        connected[device.connectionID-1] = false;
        notify();
        writeToLog.write("Connection " + device.connectionID + ": " + device.name + " Logged out\n");
    }

}