package sync;

public class Device extends Thread{
    private String name;
    private String type;
    public int ID;

    public static int sharedID = 1;
    private Router router;

    public Device(String name, String type, int maxConnections) {
        this.name = name;
        this.type = type;
        this.router = new Router(maxConnections);
        this.ID = sharedID;

    }

    public static void incrementID() {
        sharedID++;
    }

    @Override
    public void run() {
        try {
            router.connect(this);
            System.out.println("Connection " + sharedID + ": " + name + " Arrived");
            System.out.println("Connection " + sharedID + ": " + name + " Occupied");
            incrementID();
            activity();
            router.disconnect(this);
        } 
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void activity() {
        System.out.println(name + " (" + type + ")" + " is performing an activity");

    }
    
    public String getDeviceName() {
        return name;
    }

    public String getDeviceType() {
        return type;
    }

    public Router getRouter() {
        return router;
    }




}