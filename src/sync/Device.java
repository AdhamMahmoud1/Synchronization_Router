package sync;

public class Device extends Thread{
    private String name;
    private String type;
    public static int ID = 0;
    private Router router;

    public Device(String name, String type, int maxConnections) {
        this.name = name;
        this.type = type;
        
        this.router = new Router(maxConnections);
    }

    @Override
    public void run() {
        try {
            router.connect(this);
            System.out.println("Connection " + ID + ": " + name + " Arrived");
            System.out.println("Connection " + ID + ": " + name + " Occupied");
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