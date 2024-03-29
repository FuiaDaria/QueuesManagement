package model;

public class Task implements Comparable<Task>{
    private int id;
    private int arrivalTime;
    private int serviceTime;

    public Task(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public String toString() {
        return "(" +
                this.id +
                " , " + this.arrivalTime +
                " , " + this.serviceTime +
                ')';
    }

    @Override
    public int compareTo(Task obj) {
        if (this.arrivalTime > obj.arrivalTime) {
            return 1;
        }

        if (this.arrivalTime < obj.arrivalTime) {
            return -1;
        }

        if (this.arrivalTime == obj.arrivalTime) {
            return 0;
        }
        return 0;
    }
}
