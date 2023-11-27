import javax.xml.crypto.Data;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) throws Exception{
        DatagramSocket ds = new DatagramSocket(9999);

        byte[] b1 = new byte[1024];
        DatagramPacket dp1 = new DatagramPacket(b1, b1.length);
        ds.receive(dp1);

        String str = new String(dp1.getData(), 0, dp1.getLength());
        int num = Integer.parseInt(str.trim());
        int result = num*num;

        byte[] b2 = String.valueOf(result).getBytes();
        InetAddress ip = InetAddress.getLocalHost();
        DatagramPacket dp2 = new DatagramPacket(b2, b2.length, ip, dp1.getPort());
        ds.send(dp2);



    }
}
