package xardif.project;


import java.util.*;
import java.util.concurrent.*;

public class Chairman implements Runnable{
    private BlockingQueue<Item> itemQueue;
    private Set<Item> itemSet;
    private final int ITEM_LIMIT = 10,
            AUCTION_RECIPIENT_LIMIT = 10;
    private final MarketManager marketManager;
    private List<Recipient> recipients;

    public Chairman(MarketManager marketManager) {
        this.marketManager = marketManager;
        itemQueue = new ArrayBlockingQueue<>(ITEM_LIMIT);
        itemSet = new HashSet<>();
        recipients = new ArrayList<>(AUCTION_RECIPIENT_LIMIT);
    }

    @Override
    public void run() {

        while(marketManager.isMarketOpened()) {
            try {
                Thread.sleep(5000);

                Item item = null;

                try{
                    item = itemQueue.poll(5, TimeUnit.SECONDS);
                }catch (InterruptedException ex){
                    ex.printStackTrace();
                }

                if (item == null) {
                    marketManager.closeMarket();
                    break;
                }

                synchronized (recipients) {
                    if (!recipients.isEmpty()) {
                        itemSet.remove(item);
                        System.out.println(getLogPrefix() + "Starting auction on " + item);
                        Recipient winner = recipients.get(ThreadLocalRandom.current().nextInt(recipients.size()));
                        System.out.println(getLogPrefix() + winner + " wins the auction!\nCongratulations!");
                        winner.takePrize(item);
                        for(Recipient recipient : recipients) {
                            synchronized (recipient) {
                                recipient.notify();
                            }
                        }
                        recipients.clear();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(getLogPrefix() + " says goodbye!");
    }

    public boolean registerOnAuction(Recipient recipient){
        if(marketManager.isMarketOpened()) {
            synchronized (recipients) {
                if (marketManager.isMarketOpened() && recipients.size() < AUCTION_RECIPIENT_LIMIT) {
                    System.out.println(getLogPrefix() + " registering " + recipient);
                    recipients.add(recipient);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean registerItem(Item item){
        if(itemSet.add(item)){
            return marketManager.isMarketOpened() && itemQueue.offer(item);
        }
        return false;
    }
    public boolean isOnItemLimit(){
        return itemQueue.remainingCapacity() == 0;
    }
    public String getLogPrefix(){
        return "[CHAIRMAN]";
    }
}
