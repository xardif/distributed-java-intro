package xardif.project;


import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MarketManager {
    private Set<Donor> donors;
    private Set<Recipient> recipients;

    private ExecutorService donorsExecutor, recipientsExecutor;
    private Thread chairmanThread;

    private Chairman chairman;
    private volatile boolean marketOpened;

    public MarketManager() {
        donors = new HashSet<>();
        recipients = new HashSet<>();
        chairman = new Chairman(this);
    }

    public void openMarket(){
        System.out.println("Market is opened");
        marketOpened = true;
        donorsExecutor = Executors.newCachedThreadPool();
        recipientsExecutor = Executors.newCachedThreadPool();
        chairmanThread = new Thread(chairman);
        chairmanThread.start();

        for(Donor donor : donors){
            donorsExecutor.execute(donor);
        }
        donorsExecutor.shutdown();

        for(Recipient recipient : recipients){
            recipientsExecutor.execute(recipient);
        }
        recipientsExecutor.shutdown();
    }

    public void closeMarket(){
        marketOpened = false;
        System.out.println("Market is closing");
        for(Recipient recipient : recipients){
            synchronized (recipient){
                recipient.notify();
            }
        }
        for(Donor donor : donors){
            synchronized (donor){
                donor.notify();
            }
        }
    }

    public Chairman getChairman() {
        return chairman;
    }
    public boolean isMarketOpened(){
        return marketOpened;
    }
    public boolean registerDonor(Donor donor){
        if(!marketOpened) {
            return donors.add(donor);
        }
        return false;
    }
    public boolean registerRecipient(Recipient recipient){
        if(!marketOpened) {
            return recipients.add(recipient);
        }
        return false;
    }



}
