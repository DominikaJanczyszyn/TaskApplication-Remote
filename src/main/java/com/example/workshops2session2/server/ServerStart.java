package com.example.workshops2session2.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStart
{
  public static void main(String[] args) throws IOException
  {
    ServerSocket serverSocket = new ServerSocket(8080);
    Broadcaster broadcaster = new Broadcaster("230.0.0.0", 8888);
    while (true) {
      System.out.println("Server ready for input.");
      Socket socket = serverSocket.accept();
      Communicator communicator = new Communicator(socket, broadcaster);
      Thread communicatorThread = new Thread(communicator);
      communicatorThread.start();
    }
  }
}
