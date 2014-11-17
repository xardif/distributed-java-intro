package xardif.project;

import java.io.PrintStream;
import java.util.Random;


public class Main {

    public static MarketManager marketManager;
    public static PrintStream log;
    public static Random random;

    public static void main(String[] args){
        log = System.out;
        random = new Random();
        marketManager = new MarketManager();


        /* ONE DONOR
        marketManager.registerDonor(
                new Donor("Jane",
                new String[]{"walkman", "old bike", "radio"})
        );

        String[] recipientNames = new String[]{"Jake", "Jade", "Adam", "Will", "Paul", "Tom"};
        for(String name : recipientNames) {
            marketManager.registerRecipient(new Recipient(name));
        }
        */

        String[] names = new String[]{"Jake", "Jade", "Adam", "Will", "Paul", "Tom"};
        String[] items = new String[]{"radio", "bike", "walkman", "amulet", "pendant", "bracelet"};

        for(int i=0;i<names.length;i++) {
            marketManager.registerDonor(
                    new Donor(names[i], new String[]{items[i], "headphones"})
            );
        }


        marketManager.registerRecipient(new Recipient("David"));


        marketManager.openMarket();
    }
}
