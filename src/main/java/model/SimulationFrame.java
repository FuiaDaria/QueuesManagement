package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
    public class SimulationFrame extends JFrame {
        protected JTextField clients;
        protected JTextField queues;
        protected JTextField limit;
        protected JTextField ma;
        protected JTextField ms;
        protected JTextField mxa;
        protected JTextField mxs;
        protected JTextField avgt;
        protected JTextField avgs;
        protected JTextField peakh;
        protected JTextField time;
        protected JButton add;
        protected JTextField waitingClients;
        protected JTextField[] jTextFields = new JTextField[20];

        public SimulationFrame() {
            this.setBounds(100, 100, 800, 600);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.getContentPane().setBackground(Color.pink);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.getContentPane().setLayout(null);
            JLabel timeLabel = new JLabel("timer:");
            timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            timeLabel.setBounds(100, 250, 50, 20);
            this.getContentPane().add(timeLabel);
            timeLabel.setVisible(true);
            time = new JTextField();
            time.setBounds(150, 250, 80, 20);
            time.setBackground(Color.pink);
            this.getContentPane().add(time);
            time.setVisible(true);
            JLabel titleLabel = new JLabel("MANAGEMENT");
            titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
            titleLabel.setBounds(340, 30, 300, 40);
            this.getContentPane().add(titleLabel);
            titleLabel.setVisible(true);
            JLabel firstLabel = new JLabel("Nr of clients:");
            firstLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            firstLabel.setBounds(60, 100, 160, 40);
            this.getContentPane().add(firstLabel);
            firstLabel.setVisible(true);
            JLabel secondLabel = new JLabel("Nr of queues:");
            secondLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            secondLabel.setBounds(290, 100, 160, 40);
            this.getContentPane().add(secondLabel);
            secondLabel.setVisible(true);
            JLabel anotherLabel = new JLabel("Time limit:");
            anotherLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            anotherLabel.setBounds(520, 100, 160, 40);
            this.getContentPane().add(anotherLabel);
            anotherLabel.setVisible(true);
            clients = new JTextField();
            clients.setBounds(160, 110, 100, 25);
            this.getContentPane().add(clients);
            clients.setColumns(20);
            clients.setVisible(true);
            queues = new JTextField();
            queues.setBounds(390, 110, 100, 25);
            this.getContentPane().add(queues);
            queues.setColumns(20);
            limit = new JTextField();
            limit.setBounds(610, 110, 100, 25);
            this.getContentPane().add(limit);
            limit.setColumns(20);
            JLabel maLabel = new JLabel("min arrival:");
            maLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            maLabel.setBounds(175, 150, 160, 40);
            this.getContentPane().add(maLabel);
            maLabel.setVisible(true);
            ma = new JTextField();
            ma.setBounds(275, 160, 100, 25);
            this.getContentPane().add(ma);
            ma.setColumns(20);
            ma.setVisible(true);
            JLabel mxaLabel = new JLabel("max arrival:");
            mxaLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            mxaLabel.setBounds(400, 150, 160, 40);
            this.getContentPane().add(mxaLabel);
            mxaLabel.setVisible(true);
            mxa = new JTextField();
            mxa.setBounds(500, 160, 100, 25);
            this.getContentPane().add(mxa);
            mxa.setColumns(20);
            mxa.setVisible(true);
            JLabel msLabel = new JLabel("min service:");
            msLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            msLabel.setBounds(175, 190, 160, 40);
            this.getContentPane().add(msLabel);
            msLabel.setVisible(true);
            ms = new JTextField();
            ms.setBounds(275, 200, 100, 25);
            this.getContentPane().add(ms);
            ms.setColumns(20);
            ms.setVisible(true);
            JLabel mxsLabel = new JLabel("max service:");
            mxsLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
            mxsLabel.setBounds(400, 190, 160, 40);
            this.getContentPane().add(mxsLabel);
            mxsLabel.setVisible(true);
            mxs = new JTextField();
            mxs.setBounds(500, 200, 100, 25);
            this.getContentPane().add(mxs);
            mxs.setColumns(20);
            mxs.setVisible(true);
            add = new JButton("GENERATE");
            add.setFont(new Font("Tahoma", Font.PLAIN, 12));
            add.setBounds(630, 175, 100, 25);
            add.setBackground(Color.MAGENTA);
            this.getContentPane().add(add);
            add.setVisible(true);
            JLabel avg = new JLabel("avg waiting time:");
            avg.setFont(new Font("Tahoma", Font.PLAIN, 15));
            avg.setBounds(100, 280, 150, 25);
            this.getContentPane().add(avg);
            avg.setVisible(true);
            avgt = new JTextField();
            avgt.setBounds(100, 320, 100, 25);
            avgt.setBackground(Color.pink);
            this.getContentPane().add(avgt);
            avgt.setColumns(20);
            avgt.setVisible(false);
            JLabel avg1 = new JLabel("avg service time:");
            avg1.setFont(new Font("Tahoma", Font.PLAIN, 15));
            avg1.setBounds(100, 350, 150, 25);
            this.getContentPane().add(avg1);
            avg1.setVisible(true);
            avgs = new JTextField();
            avgs.setBounds(100, 380, 100, 25);
            avgs.setBackground(Color.pink);
            this.getContentPane().add(avgs);
            avgs.setColumns(20);
            avgs.setVisible(false);
            JLabel avg2 = new JLabel("peak hour:");
            avg2.setFont(new Font("Tahoma", Font.PLAIN, 15));
            avg2.setBounds(100, 410, 150, 30);
            this.getContentPane().add(avg2);
            avg2.setVisible(true);
            peakh = new JTextField();
            peakh.setBounds(100, 440, 100, 25);
            peakh.setBackground(Color.pink);
            this.getContentPane().add(peakh);
            peakh.setColumns(20);
            peakh.setVisible(false);
            JLabel wclients = new JLabel("Waiting clients:");
            wclients.setFont(new Font("Tahoma", Font.PLAIN, 15));
            wclients.setBounds(250, 230, 150, 30);
            this.getContentPane().add(wclients);
            wclients.setVisible(true);
            waitingClients = new JTextField(20);
            waitingClients.setBounds(250,260,450,20);
            waitingClients.setBackground(Color.pink);
            waitingClients.setVisible(true);
            this.getContentPane().add(waitingClients);
            for(int i=0; i < 20; i++){
                jTextFields[i] = new JTextField();
                jTextFields[i].setBounds(250, 240+20*(i+2), 450, 20);
                jTextFields[i].setBackground(Color.pink);
                jTextFields[i].setVisible(false);
                this.getContentPane().add(jTextFields[i]);
            }
            this.setVisible(true);
        }

        public void addAddButtonActionListener(ActionListener a) {
            add.addActionListener(a);
        }
        public int getClients() {
            return Integer.parseInt(clients.getText());
        }

        public int getQueues() {
            return Integer.parseInt(queues.getText());
        }

        public int getlimit(){
            return Integer.parseInt(limit.getText()); }
        public void setTime(int text){
            time.setText(Integer.toString(text)); }

        public int getMa() {
            return Integer.parseInt(ma.getText());
        }
        public int getMxa() {
            return Integer.parseInt(mxa.getText());
        }
        public int getMs() {
            return Integer.parseInt(ms.getText());
        }
        public int getMxs() {
            return Integer.parseInt(mxs.getText());
        }
        public void setAvgt(float text){
            avgt.setText(Float.toString(text));
        }
        public void setAvgs(float text){
            avgs.setText(Float.toString(text));
        }
        public void setPeakh(int text){
            peakh.setText(Integer.toString(text));
        }
        public void reveal(boolean flag){
            avgt.setVisible(true);
            avgs.setVisible(true);
            peakh.setVisible(true);
        }

        public JTextField[] getjTextFields() {
            return jTextFields;
        }

        public void setjTextFields(JTextField[] jTextFields) {
            this.jTextFields = jTextFields;
        }

        public JTextField getWaitingClients() {
            return waitingClients;
        }

        public void setWaitingClients(JTextField waitingClients) {
            this.waitingClients = waitingClients;
        }
    }

