import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("127.0.0.1", 12345);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String message = "Hello, server!";
        out.println(message);

        String response = in.readLine();
        System.out.println("Received from server: " + response);

        socket.close();

    }
}
