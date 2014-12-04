package xardif.project;

import java.io.PrintStream;
import java.util.Random;


public class Main {

    public static void oneDonor(){
        MarketManager marketManager = new MarketManager();

        marketManager.registerDonor(
                new Donor(
                        "Jane",
                        new String[]{"walkman", "old bike", "radio"},
                        marketManager
                )
        );

        String[] recipientNames = new String[]{"Jake", "Jade", "Adam", "Will", "Paul", "Tom"};
        for(String name : recipientNames) {
            marketManager.registerRecipient(new Recipient(name, marketManager));
        }

        marketManager.openMarket();
    }

    public static void oneRecipient(){
        MarketManager marketManager = new MarketManager();

        String[] names = new String[]{"Jake", "Jade", "Adam", "Will", "Paul", "Tom"};
        String[] items = new String[]{"radio", "bike", "walkman", "amulet", "pendant", "bracelet"};

        for(int i=0;i<names.length;i++) {
            marketManager.registerDonor(
                    new Donor(names[i], new String[]{items[i], "headphones"}, marketManager)
            );
        }

        marketManager.registerRecipient(new Recipient("David", marketManager));

        marketManager.openMarket();
    }

    public static void main(String[] args){
        //oneRecipient();
        oneDonor();
    }
}
