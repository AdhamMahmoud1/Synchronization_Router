package sync;

import java.io.FileWriter;
import java.io.IOException;

public class Device extends Thread {
   public String name, type;
   public int connectionID;
   public  Router router;

   FileWriter writeToLog;

   public Device(String name, String type, Router router) throws IOException {
        this.name = name;
        this.type = type;
        this.router = router;
        connectionID = 1;
        writeToLog = new FileWriter("output.txt", true);

   }

    @Override
    public void run() {
        try {
            router.semaphore.wait(this);
            connectionID = router.connect(this);
            writeToLog.write("Connection " + connectionID + ": " + name + " Occupied\n");
            activity();
            router.disconnect(this);
            router.semaphore.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void activity() throws InterruptedException, IOException {
        writeToLog.write("Connection " + connectionID + ": " + name + " login\n");
        writeToLog.write("Connection " + connectionID + ": " + name + " Performs online activity\n");
        sleep(2000);
        try {
            writeToLog.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writeToLog.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}