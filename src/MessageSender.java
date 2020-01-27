import java.net.*;
import java.util.Enumeration;

class MessageSender {

  public static void main(String[] args) {
    String message = "Hello there!";
    sendMessage(43594, message.getBytes());
  }

  static void sendMessage(int receiverPort, byte[] buffer) {
    try {
      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
      while (interfaces.hasMoreElements())
      {
        NetworkInterface networkInterface = interfaces.nextElement();
        if (networkInterface.isLoopback())
          continue;
        for (InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses())
        {
          InetAddress receiverHost = interfaceAddress.getBroadcast();
          if (receiverHost == null)
            continue;

          DatagramSocket  mySocket = new DatagramSocket();
          DatagramPacket datagram =
                  new DatagramPacket(buffer, buffer.length,
                          receiverHost, receiverPort);
          mySocket.send(datagram);

          mySocket.close( );
        }
      }
    }
    catch (Exception ex) {
      ex.printStackTrace( );
    }
  }
}