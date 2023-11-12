package sync;

public class Semaphore {
    private int value;

    public Semaphore(int value) {
        this.value = value;
    }

    public synchronized void acquire(Device device) {
            value--;
            if (value < 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println(
                            device.getDeviceName() + " (" + device.getDeviceType() + ")" + " arrived and waiting");
                }
            } 
            else {
                System.out.println(device.getDeviceName() + " (" + device.getDeviceType() + ")" + " arrived");
            }
        }

        public synchronized void release() {
        value++;
        if (value <= 0) {
            notify();
        }
        
    }
}
