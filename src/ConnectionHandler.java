import java.io.*;
import java.net.Socket;
import java.sql.Connection;

public class ConnectionHandler extends Thread{
    Socket socket;
    BufferedReader fromClient;
    PrintWriter toClient;
    String protokolIO;

    public ConnectionHandler(Socket socket) throws IOException {
        this.protokolIO = "";
        this.socket = socket;
        fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        while(true) {
            try {
                if (!((protokolIO = fromClient.readLine())!=null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Got " + protokolIO);
        }
   }
}
