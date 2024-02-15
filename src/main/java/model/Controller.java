package model;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

public class Controller {
    SimulationFrame view;

    public Controller(SimulationFrame view) {
        this.view = view;
        this.view.addAddButtonActionListener(new AddButtonActionListener());

    }

    private class AddButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int timeLimit = view.getlimit();
            int mxa = view.getMxa();
            int ma = view.getMa();
            int clients = view.getClients();
            int queues = view.getQueues();
            int ms = view.getMs();
            int mxs = view.getMxs();
            create();
            if(!validate(clients, queues, ma, mxa, ms, mxs, timeLimit)){
                for (int j = 0; j < queues; j++) {
                    view.getjTextFields()[j].setVisible(true);
                    view.getjTextFields()[j].setText("invalid input");
                }
                System.exit(0);
            }
            SimulationManager simulation = new SimulationManager(view, timeLimit, ma, mxa, mxs, ms, queues, clients);
            Thread t = new Thread(simulation);
            t.start();

        }

        public boolean validate(int clients, int queues, int mina, int maxa, int mins, int maxs, int lim){
            boolean flag = true;
            if(clients<1 || queues<1 || mina>=maxa || mins>=maxs || mina<1 || maxa<1 || mins<1 || maxs<1 || lim<1 || lim<=maxa){
                flag = false;
            }
            return flag;
        }
        private void create() {
            for(int i=0; i< view.getQueues(); i++){

                view.getjTextFields()[i].setVisible(true);
            }
        }
    }
}
