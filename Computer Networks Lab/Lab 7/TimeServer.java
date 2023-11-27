import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer {
    public static void main(String[] args) throws Exception {

        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Time Server is running and listening on port 12345...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(new Date().toString());

            clientSocket.close();
            System.out.println("Client disconnected.");
        }

    }
}
