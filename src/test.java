import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class test {
    public static void main(String[] args) throws IOException  {
        DatagramSocket socket = null;
        try {
            socket=new DatagramSocket(2020);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        byte[] data=new byte[2048];
        DatagramPacket packet=new DatagramPacket(data, 50);
        System.out.println("receiving");
        socket.receive(packet);
        System.out.println("receive "+new String(data));
    }
}