package com.uam.akka.exercise3.actor;

import akka.actor.UntypedActor;
import com.uam.akka.exercise3.message.Input;
import com.uam.akka.exercise3.message.Output;
import com.uam.akka.exercise3.util.PrimeChecker;

import java.util.HashSet;
import java.util.Set;

public class Worker extends UntypedActor {

	@Override
	public void onReceive(Object o) throws Exception {
        if(o instanceof Input){
            Input oo = (Input) o;
            Set<Long> primes = new HashSet<Long>();
            for(long i=oo.getInterval().from(); i<=oo.getInterval().to(); i++){
                if(PrimeChecker.isPrime(i)){
                    primes.add(i);
                }
            }
            getSender().tell(new Output(primes), getSelf());
        }
	}
}
