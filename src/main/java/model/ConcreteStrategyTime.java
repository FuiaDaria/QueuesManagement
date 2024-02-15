package model;
import java.util.List;

public class ConcreteStrategyTime implements Strategy{
    @Override
    public void addTask(List<Server> servers, Task t){
        Server shortest = servers.get(0);
        for(Server s : servers){
            if(s.getWaitingPeriod().get() < shortest.getWaitingPeriod().get()){
                shortest = s;
            }
        }
        shortest.getTasks().add(t);
        shortest.getWaitingPeriod().set(shortest.getWaitingPeriod().get() + t.getServiceTime());
    }
}
