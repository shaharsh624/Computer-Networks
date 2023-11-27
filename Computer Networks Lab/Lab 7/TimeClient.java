import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class TimeClient {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("127.0.0.1", 12345);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String response = in.readLine();
        System.out.println("Server time: " + response);

        socket.close();

    }
}
