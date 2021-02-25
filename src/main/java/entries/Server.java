package entries;

import domain.Pizza;
import infrastructure.Database;
import ui.ConnectionHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    Database db;
    Socket socket;
    int limit;
    int counter;
    //domain.Pizza[] pizzas;
    ArrayList<Pizza> pizzas;
    PizzaBakery pizzaBakery;
    private final int MAXPRODUCTION = 400;

    public Server() {
        counter = 0;
        limit = 2;
        db = new Database();
        //pizzas = new domain.Pizza[MAXPRODUCTION];
        pizzas = new ArrayList<>();
    }
    public void runServer() {
        ArrayList<ConnectionHandler> clientHandlers = new ArrayList<>();
        try {

            ServerSocket serverSocketBaker = new ServerSocket(8183);
            Socket bakerSocket = serverSocketBaker.accept();
            pizzaBakery = new PizzaBakery(bakerSocket,pizzas,db);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ServerSocket serverSocket = new ServerSocket(8182);

            while(counter<limit) {
                System.out.println("Accepting");
                Socket clientSocket = serverSocket.accept();
                clientHandlers.add(new ConnectionHandler(clientSocket, pizzas));
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Ready to run " + clientHandlers.size());
        pizzaBakery.start();
        for (ConnectionHandler c: clientHandlers ) {
            System.out.println("running ...");
            c.start();
        }
    }
}
