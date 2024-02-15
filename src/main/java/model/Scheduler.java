package model;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy = new ConcreteStrategyTime();
    public Scheduler(int maxNoServers, int maxTasksPerServer){
        this.servers = new ArrayList<Server>();
        this.maxNoServers = maxNoServers;
        this.maxTasksPerServer = maxTasksPerServer;
        for(int i=0; i<maxNoServers; i++){
            Server s = new Server();
            this.servers.add(s);
            Thread thread1 = new Thread(s);
            thread1.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_TIME){
            this.strategy = new ConcreteStrategyTime();
        }
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            this.strategy = new ConcreteStrategyQueue();
        }
    }
    public synchronized void dispatchTask(Task task) {
        int minTime = Integer.MAX_VALUE;
        int indexOfMinQueue = 0;
        if(servers.size()!=0) {
            for (int i = 0; i < servers.size(); i++) {
                Server s = servers.get(i);
                if (s.getWaitingPeriod().get() < minTime) {
                    minTime = s.getWaitingPeriod().get();
                    indexOfMinQueue = i;
                }
            }
            //System.out.println(servers.get(indexOfMinQueue));
            servers.get(indexOfMinQueue).addTask(task);
            //System.out.println(servers.get(indexOfMinQueue));
        }
    }


    public List<Server> getServers() {
        return servers;
    }

}
