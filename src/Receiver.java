import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Receiver {

  public static void main(String[] args) {
    listen(43594);
  }

  public static void listen(int port) {
    final int MAX_LEN = 22;
    // This is the assumed maximum byte length of the datagram to be received.
    try {
      DatagramSocket mySocket = new DatagramSocket(port);
      // instantiates a datagram socket for receiving the data
      byte[] buffer = new byte[MAX_LEN];
      DatagramPacket datagram = new DatagramPacket(buffer, MAX_LEN);
      mySocket.receive(datagram);
      String s  = new String(buffer);
      System.out.println(s);
      mySocket.close();

    } catch (SocketException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}