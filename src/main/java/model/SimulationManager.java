package model;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.io.*;
import java.util.*;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import static java.lang.Thread.sleep;

public class SimulationManager implements Runnable{
    SimulationFrame view;
    public int timeLimit;
    public int minArrivalTime;
    public int maxArrivalTime;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int numberOfServers;
    public int numberOfClients;
    public float waitTime;
    public float servTime;
    public int peakHour;
    public SelectionPolicy sel = SelectionPolicy.SHORTEST_TIME;
    private List<Task> generated;
    private Scheduler schedule;
    private String outputFile = "output.txt";
    public SimulationManager(SimulationFrame view, int timeLimit, int minArrivalTime, int maxArrivalTime, int maxProcessingTime, int minProcessingTime, int numberOfServers, int numberOfClients) {
        this.timeLimit = timeLimit;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.maxProcessingTime = maxProcessingTime;
        this.minProcessingTime = minProcessingTime;
        this.numberOfServers = numberOfServers;
        this.numberOfClients = numberOfClients;
        this.view = view;
        schedule = new Scheduler(numberOfServers, numberOfClients);
        generated = new ArrayList<>();
        generateNRandomTasks(numberOfClients, minArrivalTime, maxArrivalTime, minProcessingTime, maxProcessingTime);
    }
    private void generateNRandomTasks(int numberOfClients, int minArrivalTime, int maxArrivalTime, int minProcessingTime, int maxProcessingTime){
        Random rand = new Random();
        int avgwait = 0;
        int avgserv = 0;
        for (int i = 0; i < numberOfClients; i++) {
            int arrivalTime = rand.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            int serviceTime = rand.nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime;
            Task t = new Task(i+1, arrivalTime, serviceTime);
            generated.add(t);
            avgwait += t.getServiceTime();
            avgserv += t.getArrivalTime();
        }
        waitTime = avgwait / numberOfClients;
        servTime = avgserv / numberOfClients;
        Collections.sort(generated);
    }

    @Override
    public void run(){
        int peak = 0;
        boolean finish = true;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(outputFile, false));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int currentTime = 0;
        while(currentTime <= timeLimit && finish) {
            int max = 0;
            view.setTime(currentTime);
            int i = 0;
            List<Integer> toRemove = new ArrayList<>();
            //System.out.println(generated);
            for (Task t : generated) {
                if (currentTime == t.getArrivalTime()) {
                    toRemove.add(i);
                    schedule.dispatchTask(t);
                    i++;
                }
            }
            for (i = toRemove.size() - 1; i >= 0; i--) {
                generated.remove(generated.get(toRemove.get(i)));
            }
            writeData(new File(outputFile), currentTime, this.generated, this.schedule, false);
            StringBuilder waitingClients = new StringBuilder();
            for (Task t : generated) {
                waitingClients.append(t.toString());
                waitingClients.append(",");
            }
            view.getWaitingClients().setText(waitingClients.toString());
            //System.out.println(waitingClients);
            for (int j = 0; j < numberOfServers; j++) {
                if (schedule.getServers().size() != 0) {
                    Server server = schedule.getServers().get(j);
                    String serverStatus = "Queue " + j + ": " + server.toString();
                    //System.out.println(serverStatus);
                    view.getjTextFields()[j].setText(serverStatus);
                    max += server.getTasks().size();
                }
            }
            if (max > peak) {
                peak = max;
                peakHour = currentTime;
            }
            int c = 0;
            currentTime++;
            for (i = 0; i < numberOfServers; i++) {
                if (schedule.getServers().get(i).getTasks().size() == 0) {
                    c++;
                }
            }
            if (generated.isEmpty() && c == numberOfServers) {
                finish = false;
                for(i=0;i<numberOfServers;i++){
                    schedule.getServers().get(i).setFlag(false);
                }
            }
            try {
                sleep(1000);
            } catch (InterruptedException e1) {
                throw new RuntimeException("An error encountered", e1);
            }
        }

        writeData(new File(outputFile), this.timeLimit, this.generated, this.schedule, true);
        view.setAvgt(waitTime);
        view.setAvgs(servTime);
        view.setPeakh(peakHour);
        view.reveal(true);
    }
    public void writeData(File file, int currentTime, List<Task> tasks, Scheduler scheduler, boolean printAdditional) {
        numberOfClients = view.getClients();
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            if(!printAdditional) {
                bufferedWriter.write("Time: " + currentTime);
                bufferedWriter.newLine();

                bufferedWriter.write("Waiting clients: ");
                for (Task task : tasks) {
                    bufferedWriter.write(task.toString());
                }
                bufferedWriter.newLine();

                int iterator = 0;
                for (Server server : scheduler.getServers()) {
                    bufferedWriter.write("Queue " + iterator + ": " + server.toString());
                    iterator++;
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.newLine();

            if (printAdditional) {
                bufferedWriter.write("Average waiting time: " + waitTime);
                bufferedWriter.newLine();
                bufferedWriter.write("Average service time: " + servTime);
                bufferedWriter.newLine();
                bufferedWriter.write("Peak hour: " + peakHour);
            }

            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
