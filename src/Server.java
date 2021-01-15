import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    Socket socket;
    int limit;
    int counter;

    public Server() {
        counter = 0;
        limit = 2;
    }
    public void runServer() {
        ArrayList<ConnectionHandler> clientHandlers = new ArrayList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(8182);

            while(counter<limit) {
                System.out.println("Acceping");
                Socket clientSocket = serverSocket.accept();
                clientHandlers.add(new ConnectionHandler(clientSocket));
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Ready to run " + clientHandlers.size());
        for (ConnectionHandler c: clientHandlers ) {
            System.out.println("running ...");
            c.start();
        }
    }
}
