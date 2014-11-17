package xardif.project;


import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class Chairman implements Runnable{
    private BlockingQueue<Item> itemsQueue;
    private Set<Item> items;
    private final int ITEM_LIMIT = 10;
    private final int AUCTION_RECIPIENT_LIMIT = 10;
    private List<Recipient> recipients;

    public Chairman() {
        itemsQueue = new LinkedBlockingQueue<>(ITEM_LIMIT);
        items = new HashSet<>();
        recipients = new ArrayList<>(AUCTION_RECIPIENT_LIMIT);
    }

    @Override
    public void run() {

        while(Main.marketManager.isMarketOpened()) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (itemsQueue.isEmpty()) {
                try {
                    Thread.sleep(Main.random.nextInt(5000) + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (itemsQueue.isEmpty()) {
                    Main.marketManager.closeMarket();
                    return;
                }
            }


            if(!recipients.isEmpty()){
                Item prize = itemsQueue.poll();
                items.remove(prize);
                Main.log.println(getLogPrefix() + "Starting auction on " + prize);
                Recipient winner = recipients.get(Main.random.nextInt(recipients.size()));
                Main.log.println(getLogPrefix() + winner + " wins the auction!\nCongratulations!");
                winner.takePrize(prize);
                try {
                    recipients.notifyAll();
                }catch (Exception ex){ }
                recipients.clear();
            }
        }
    }

    public boolean registerOnAuction(Recipient recipient){
        if(recipients.size() < AUCTION_RECIPIENT_LIMIT) {
            Main.log.println(getLogPrefix() + " registering " + recipient);
            recipients.add(recipient);
            return true;
        }
        return false;
    }
    public boolean registerItem(Item item){
        if(items.add(item)){
            itemsQueue.add(item);
            return true;
        }
        return false;
    }
    public boolean isOnItemLimit(){
        return itemsQueue.remainingCapacity() == 0;
    }
    public String getLogPrefix(){
        return "[CHAIRMAN]";
    }
}
