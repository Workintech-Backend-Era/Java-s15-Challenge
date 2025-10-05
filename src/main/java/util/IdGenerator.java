package util;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {

    private static final AtomicInteger counter = new AtomicInteger(1000);

    public static int nextId(){
        return counter.getAndIncrement();
    }

}
