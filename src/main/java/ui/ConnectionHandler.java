package ui;

import domain.Pizza;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class ConnectionHandler extends Thread{
    Socket socket;
    BufferedReader fromClient;
    PrintWriter toClient;
    String protokolIO;
    //domain.Pizza[] pizzas;
    ArrayList<Pizza> pizzas;

    public ConnectionHandler(Socket socket, ArrayList<Pizza> pizzas) throws IOException {
        this.protokolIO = "";
        this.socket = socket;
        this.pizzas = pizzas;
        fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
    }

    @Override
    public void run() {
        try {
            while (!((protokolIO = fromClient.readLine()).equalsIgnoreCase("bye"))) {
                boolean picked = false;
                Pizza tmpPizza = null;
                Iterator<Pizza> pizzaIterator = pizzas.iterator();
                //for(int i=0;i<pizzas.size();i++) {
                while(pizzaIterator.hasNext()) {
                    Pizza p = pizzaIterator.next();
                    if (!picked) {
                        toClient.println(p.getName() + " no. " + p.getNo() + "," + p.getCounter());

                        try {
                            int val = Integer.valueOf(protokolIO);
                            if (p.getNo() == val) {
                                System.out.println("took pizza count " + p.getCounter() + " no " + p.getNo() + ", "+p.getName());
                                pizzaIterator.remove();
                                picked = true;
                            }
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                            System.out.println("Wron ...");
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Got " + protokolIO);
    }

    public void handleUserInput() {

    }
}
