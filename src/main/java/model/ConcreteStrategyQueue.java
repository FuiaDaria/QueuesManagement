package model;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t){
        Server shortest = servers.get(0);
        for(Server s: servers){
            if(s.getTasks().size() < shortest.getTasks().size()){
                shortest = s;
            }
        }
        shortest.getTasks().add(t);
        shortest.getWaitingPeriod().set(shortest.getWaitingPeriod().get() + t.getServiceTime());
    }
}
