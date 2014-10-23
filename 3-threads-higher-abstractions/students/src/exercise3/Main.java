package exercise3;

import common.html.GazetaHtmlDocument;
import common.html.HtmlDocument;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {
        HtmlDocument rootDocument = new GazetaHtmlDocument("http://wiadomosci.gazeta.pl/");
        Set<String> links = rootDocument.getLinks();
        String wordToFound = "sikorski";

        // TODO: Create ExecutorService
        ExecutorService executorService = Executors.newCachedThreadPool();

        // TODO: Create list of results of type List<Future<Integer>>
        List<Future<Integer>> list = new ArrayList<Future<Integer>>();

        for (String link : links) {
            // TODO: Create new WordCounter and submit it to executorService
            WordCounter wordCounter = new WordCounter(link, wordToFound);
            Future<Integer> futureObject = executorService.submit(wordCounter);
            // TODO: Store Future object in list of results
            list.add(futureObject);
        }

        // TODO: shutdown executor
        executorService.shutdown();

        int numberOfWords = 0;
        // TODO: Iterate over list of results and for each Future invoke get() method
        for(Future<Integer> futureObject : list){
            // TODO: add value returned from get() method to numberOfWords variable
            numberOfWords += futureObject.get();
        }
        System.out.printf("Number of words '%s': %d", wordToFound, numberOfWords);
    }
}
