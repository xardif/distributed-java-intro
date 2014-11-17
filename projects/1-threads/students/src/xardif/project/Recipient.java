package xardif.project;


import java.util.ArrayList;
import java.util.List;

public class Recipient implements Runnable{
    private String name;
    private List<Item> wonItems;
    private Item lastWonItem;

    public Recipient(String name) {
        this.name = name;
        this.wonItems = new ArrayList<>();
        lastWonItem = null;
    }

    @Override
    public void run() {
        while(Main.marketManager.isMarketOpened()) {
            Main.log.println(getLogPrefix() + " hello!");

            try {
                Thread.sleep(Main.random.nextInt(5000) + 1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            boolean isRegistered = Main.marketManager.getChairman().registerOnAuction(this);
            /*Main.log.println(getLogPrefix() +
                            (
                                    isRegistered
                                            ?
                                            " Successfully registered on upcoming auction!"
                                            :
                                            " Can't register on upcoming auction!"
                            )
            );*/
            if (isRegistered) {
                try {
                    wait();
                } catch (Exception e) {}
            }

            if (lastWonItem != null) {
                Main.log.println(getLogPrefix() + "I've won item: " + lastWonItem + "!");
                lastWonItem = null;

                try {
                    long sleepTime = Main.random.nextInt(10000) + 5000;
                    Thread.sleep(sleepTime);
                    Main.log.println(getLogPrefix() + " I've just waited " + sleepTime + " before registering on another auction.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Main.log.println(getLogPrefix() + " says good bye! Leaving with " + wonItems);
    }

    public void takePrize(Item prize){
        lastWonItem = prize;
        wonItems.add(prize);
    }

    public String getName() {
        return name;
    }

    public String getLogPrefix(){
        return "[RECIPIENT:" + name + "]";
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
