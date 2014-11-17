package xardif.project;


import java.util.ArrayList;
import java.util.List;

public class Donor implements Runnable{
    private List<Item> items;
    private String name;

    public Donor(String name, String[] itemNames) {
        this.name = name;
        items = new ArrayList<>();
        for(String itemName : itemNames){
            items.add(new Item(itemName));
        }
    }



    @Override
    public void run() {
        Main.log.println(getLogPrefix() + " started!");

        while(Main.marketManager.isMarketOpened() && !items.isEmpty()) {
            Item item = items.remove(0);

            try {
                long sleepTime = Main.random.nextInt(25000) + 5000;
                Thread.sleep(sleepTime);
                Main.log.println(getLogPrefix() + " I've just waited " + sleepTime + " before registering an item.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (Main.marketManager.getChairman().isOnItemLimit()) {
                try {
                    Thread.sleep(Main.random.nextInt(5000) + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            Main.log.println(getLogPrefix() +
                            (
                                    Main.marketManager.getChairman().registerItem(item)
                                            ?
                                            " Registered an item: " + item + "."
                                            :
                                            " Item: " + item + " is already registered."
                            )
            );
        }

        Main.log.println(getLogPrefix() + " says goodbye!");
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
