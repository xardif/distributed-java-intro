package pl.edu.amu.dji.jms.lab4.service.message;

import javax.jms.Destination;


public class InitPointOfSales {
    private int id;
    private Destination replyTo;

    public InitPointOfSales(int id, Destination replyTo) {
        this.id = id;
        this.replyTo = replyTo;
    }

    public int getId() {
        return id;
    }

    public Destination getReplyTo() {
        return replyTo;
    }

}
