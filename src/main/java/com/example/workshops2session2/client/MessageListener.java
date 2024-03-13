package com.example.workshops2session2.client;

import java.io.IOException;
import java.net.*;
import java.nio.channels.AsynchronousCloseException;

public class MessageListener implements Runnable
{
  private final ClientImpl client;
  private final MulticastSocket multicastSocket;
  private final InetSocketAddress socketAddress;
  private final NetworkInterface networkInterface;

  public MessageListener(ClientImpl client, String groupAddress, int port) throws IOException {
    this.client = client;
    multicastSocket = new MulticastSocket(port);
    InetAddress group = InetAddress.getByName(groupAddress);
    socketAddress = new InetSocketAddress(group, port);
    networkInterface = NetworkInterface.getByInetAddress(group);
  }

  private void listen() throws IOException {
    multicastSocket.joinGroup(socketAddress, networkInterface);
    try {
      byte[] content = new byte[32768];
      while (true) {
        DatagramPacket packet = new DatagramPacket(content, content.length);
        multicastSocket.receive(packet);
        String message = new String(packet.getData(), 0, packet.getLength());
        client.receiveBroadcast(message);
      }
    } catch (SocketException e) {
      if (!((e.getCause()) instanceof AsynchronousCloseException)) throw e;
    }
  }

  public void close() throws IOException {
    multicastSocket.leaveGroup(socketAddress, networkInterface);
    multicastSocket.close();
  }

  @Override public void run()
  {
    try {
      listen();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }
}
