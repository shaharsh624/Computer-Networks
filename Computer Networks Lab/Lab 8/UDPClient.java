import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        int port = 9999;
        DatagramSocket ds = new DatagramSocket();

        // Preprocessing Data
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        System.out.print("Enter number: ");

        int num = sc.nextInt();
        byte[] b1 = String.valueOf(num).getBytes();

        // Sending Data to Server
        InetAddress ip = InetAddress.getLocalHost();
        DatagramPacket dp1 = new DatagramPacket(b1, b1.length, ip, port);
        ds.send(dp1);

        // Receiving Data from Server
        byte[] b2 = new byte[1024];
        DatagramPacket dp2 = new DatagramPacket(b2, b2.length);
        ds.receive(dp2);

        String str = new String(dp2.getData(), 0, dp2.getLength());
        System.out.printf("Square of %d : %s", num, str);

    }
}
