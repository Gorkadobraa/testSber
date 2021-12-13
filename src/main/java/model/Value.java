package model;

import javax.persistence.Entity;
import java.util.Arrays;

public class Value {
    private boolean threadA;
    private boolean threadB;
    private String[] queue;

    public boolean isThreadA() {
        return threadA;
    }

    public void setThreadA(boolean threadA) {
        this.threadA = threadA;
    }

    public boolean isThreadB() {
        return threadB;
    }

    public void setThreadB(boolean threadB) {
        this.threadB = threadB;
    }

    public String[] getQueue() {
        return queue;
    }

    public void setQueue(String[] queue) {
        this.queue = queue;
    }

    public Value(boolean threadA, boolean threadB, String[] queue) {
        this.threadA = threadA;
        this.threadB = threadB;
        this.queue = queue;
    }

    public Value() {
    }

    @Override
    public String toString() {
        return "Value{" +
                "threadA=" + threadA +
                ", threadB=" + threadB +
                ", queue=" + Arrays.toString(queue) +
                '}';
    }
}
