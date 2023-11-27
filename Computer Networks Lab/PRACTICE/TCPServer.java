import java.io.*;
import java.net.*;
import java.util.*;

public class TCPServer
{
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        Socket s1;
        ServerSocket ss = new ServerSocket(3000);
        System.out.println("Server is running...");
        s1 = ss.accept();
        System.out.println("Connection Established.");
        while (true) {
            //Read from Client
            DataInputStream dis = new DataInputStream(s1.getInputStream());
            String message = dis.readUTF();
            if (message.equalsIgnoreCase("exit")) {
                System.out.println("Client wants to Exit");
                break;
            }
            System.out.println(message);

            //Send to Client
            OutputStream os = s1.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            System.out.print("Enter a message to send Client: ");
            String str = in.nextLine();
            dos.writeUTF(str);
        }
    }
}