package xardif.project;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Donor implements Runnable{
    private List<Item> items;
    private final String name;
    private final MarketManager marketManager;

    public Donor(String name, String[] itemNames, MarketManager marketManager) {
        this.name = name;
        this.marketManager = marketManager;
        items = new LinkedList<>();
        for(String itemName : itemNames){
            items.add(new Item(itemName));
        }
    }



    @Override
    public void run() {
        System.out.println(getLogPrefix() + " started!");

        while(marketManager.isMarketOpened() && !items.isEmpty()) {
            Item item = items.remove(0);

            try {
                long sleepTime = ThreadLocalRandom.current().nextInt(25000) + 5000;
                Thread.sleep(sleepTime);
                System.out.println(getLogPrefix() + " I've just waited " + sleepTime + " ms before registering an item.");

                boolean registered = false;

                while (marketManager.isMarketOpened() && !(registered = marketManager.getChairman().registerItem(item))) {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(5000) + 1);
                }

                System.out.println(getLogPrefix() + (registered ? " Registered an item: " + item + "." : " Can't register " + item +"."));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(getLogPrefix() + " says goodbye!");
    }


    public String getName() {
        return name;
    }
    public String getLogPrefix(){
        return "[DONOR:" + name + "]";
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
