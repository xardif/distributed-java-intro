package com.uam.akka.exercise3.actor;

import akka.actor.Props;
import akka.actor.UntypedActor;
import com.uam.akka.exercise3.message.Input;
import com.uam.akka.exercise3.message.Output;
import com.uam.akka.exercise3.util.Interval;

import java.util.HashSet;
import java.util.Set;

public class Master extends UntypedActor {

	private final int numberOfWorkers;
    private int workersWhoDone = 0;
    private Set<Long> primes;

	public Master(int numberOfWorkers) {
		this.numberOfWorkers = numberOfWorkers;
        this.primes = new HashSet<Long>();
        for(int i=0; i<numberOfWorkers; i++){
            getContext().actorOf(Props.create(Worker.class));
        }
	}

	@Override
	public void onReceive(Object o) throws Exception {
        if(o instanceof Output){
            workersWhoDone += 1;
            primes.addAll(((Output)o).getPrimes());

            if(numberOfWorkers == workersWhoDone){
                System.out.println(primes.size());
                getContext().system().shutdown();
            }
        }
        if(o instanceof Input){
            Input oo = (Input)o;
            for(Interval interval : oo.getInterval().divide(numberOfWorkers)){
                getContext().children().iterator().next().tell(new Input(interval), getSelf());
            }
        }
	}
}
