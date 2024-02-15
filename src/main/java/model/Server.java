package model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Server implements Runnable{
    private BlockingQueue<Task> tasks;
    private AtomicInteger waitingPeriod;
    private boolean flag = true;
    public Server(){
        this.waitingPeriod = new AtomicInteger(0);
        this.tasks = new LinkedBlockingQueue<Task>();
    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void addTask(Task t){
        boolean empty = false;
        if(this.tasks.size()==0){
            empty = true;
        }
        this.tasks.add(t);
        this.waitingPeriod.set(this.waitingPeriod.get() + t.getServiceTime());
        if(empty){
            Thread initializeIt = new Thread(this);
            initializeIt.start();
        }

    }

    @Override
    public void run(){
        while(!this.tasks.isEmpty() && getFlag()){
            if(!this.tasks.isEmpty()){
                try{
                    while(this.tasks.element().getServiceTime()!=0){
                        Thread.sleep(1000);
                        this.tasks.element().setServiceTime(this.tasks.element().getServiceTime()-1);
                        this.waitingPeriod.set(this.waitingPeriod.get()-1);
                    }
                    Task first = this.tasks.take();
                    this.waitingPeriod.set(this.waitingPeriod.get()- first.getServiceTime());
                }catch(InterruptedException e1){
                    throw new RuntimeException("An error encountered", e1);
                }
            }
        }
    }

    @Override
    public String toString() {
        if(this.getTasks().size()==0){
            return "closed";
        }
        String result = "";
        for(Task t : this.getTasks()){
            result = result + t.toString() + " ";
        }
        return result;
    }
}
