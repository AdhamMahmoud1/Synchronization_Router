package sync;

import java.io.FileWriter;
import java.io.IOException;

public class CounterSemaphore {
    int value;

    public CounterSemaphore(int value) {
        this.value = value;
    }

    public synchronized void wait(Device device) throws InterruptedException, IOException {
        FileWriter writeToLog = new FileWriter("output.txt", true);
        value--;
        if (value < 0) {

            writeToLog.write(device.name + " (" + device.type + ")" + " arrived and waiting\n");
            wait();

        }
        else{
            writeToLog.write( device.name +" (" + device.type + ")" +" arrived\n");
        }

        device.router.connect(device);
        writeToLog.flush();
        writeToLog.close();
    }

    public synchronized void signal() {
        value++;
        if (value <= 0) {
            notify();
        }
    }
}