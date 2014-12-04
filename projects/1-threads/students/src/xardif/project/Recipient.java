package xardif.project;


import com.sun.org.apache.xml.internal.utils.ThreadControllerWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Recipient implements Runnable{
    private final String name;
    private final MarketManager marketManager;
    private List<Item> wonItems;
    private Item lastWonItem;

    public Recipient(String name, MarketManager marketManager) {
        this.name = name;
        this.marketManager = marketManager;
        this.wonItems = new ArrayList<>();
        lastWonItem = null;
    }

    @Override
    public void run() {
        while(marketManager.isMarketOpened()) {
            try {
                System.out.println(getLogPrefix() + " hello!");
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000) + 1);

                if (marketManager.isMarketOpened() && marketManager.getChairman().registerOnAuction(this)) {
                   synchronized (this){
                       wait();
                       if (lastWonItem != null) {
                           System.out.println(getLogPrefix() + "I've won item: " + lastWonItem + "!");
                           lastWonItem = null;

                           long sleepTime = ThreadLocalRandom.current().nextInt(10000) + 5000;
                           Thread.sleep(sleepTime);
                           System.out.println(getLogPrefix() + " I've just waited " + sleepTime + " ms before registering on another auction.");
                       }
                   }
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getLogPrefix() + " says good bye! Leaving with " + wonItems);
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
