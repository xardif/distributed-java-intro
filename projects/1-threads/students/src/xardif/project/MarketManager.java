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
    private boolean marketOpened;

    public MarketManager() {
        donors = new HashSet<>();
        recipients = new HashSet<>();
        chairman = new Chairman();
    }

    public void openMarket(){
        Main.log.println("Market is opened");
        marketOpened = true;
        donorsExecutor = Executors.newFixedThreadPool(donors.size());
        recipientsExecutor = Executors.newFixedThreadPool(recipients.size());
        chairmanThread = new Thread(chairman);

        chairmanThread.start();

        for(Donor donor : donors){
            donorsExecutor.execute(donor);
        }

        for(Recipient recipient : recipients){
            recipientsExecutor.execute(recipient);
        }
    }

    public void closeMarket(){
        marketOpened = false;
        Main.log.println("Market is closing");
        donorsExecutor.shutdown();
        recipientsExecutor.shutdown();
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
