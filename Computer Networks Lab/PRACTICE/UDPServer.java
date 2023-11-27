import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) throws Exception{
        int port = 9999;
        DatagramSocket ds = new DatagramSocket(port);

        // Getting Data from client
        byte[] b1 = new byte[1024];
        DatagramPacket dp1 = new DatagramPacket(b1, b1.length);
        ds.receive(dp1);

        // Performing square operation on data received
        String str = new String(dp1.getData(), 0, dp1.getLength());
        int num = Integer.parseInt(str.trim());
        int result = num*num;

        // Sending data back to client
        byte[] b2 = String.valueOf(result).getBytes();
        InetAddress ip = InetAddress.getLocalHost();
        DatagramPacket dp2 = new DatagramPacket(b2, b2.length, ip, dp1.getPort());
        ds.send(dp2);

        System.out.println("Server is running on port " + port);

    }
}
