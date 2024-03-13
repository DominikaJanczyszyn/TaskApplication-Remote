package com.example.workshops2session2.server;

import com.example.workshops2session2.Model.State;
import com.example.workshops2session2.Model.StateInterfaceAdapter;
import com.example.workshops2session2.Model.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Communicator implements Runnable
{
  private final Socket socket;
  private final Broadcaster broadcaster;
  private final Gson gson;
  private final SharedArrayList sharedArrayList;

  private final static String GET = "GET";
  private final static String ADD = "ADD";
  private final static String START = "START";
  private final static String FINISH = "FINISH";
  private final static String EXIT = "EXIT";

  public Communicator(Socket socket, Broadcaster broadcaster)
  {
    this.socket = socket;
    this.broadcaster = broadcaster;
    this.gson = new GsonBuilder().registerTypeAdapter(State.class, new StateInterfaceAdapter()).create();
    this.sharedArrayList = SharedArrayList.getInstance();
  }

  private synchronized void communicate() throws IOException {
    try {
      InputStream inputStream = socket.getInputStream();
      BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));
      OutputStream outputStream = socket.getOutputStream();
      PrintWriter output = new PrintWriter(outputStream);

      loop: while (true) {
        String jsonRequest = input.readLine();
        switch (jsonRequest) {
          case GET: {
            output.println(gson.toJson(sharedArrayList.getTasks()));
            output.flush();
            System.out.println(socket.getLocalAddress() + ": Tasks arrayList request.");
            break;
          }
          case ADD: {
            String message = input.readLine();
            Task task = gson.fromJson(message, Task.class);
            sharedArrayList.getTasks().add(task);
            System.out.println(socket.getLocalAddress() + ": Adding task request.");
            broadcaster.broadcast(gson.toJson(sharedArrayList.getTasks()));
            break;
          }
          case START: {
            String message = input.readLine();
            Task task = gson.fromJson(message, Task.class);
            innerLoop:for (Task value : sharedArrayList.getTasks())
            {
              if (task.equals(value))
              {
                value.startTask();
                System.out.println(socket.getLocalAddress() + ": Starting task request.");
                break innerLoop;
              }
            }
            broadcaster.broadcast(gson.toJson(sharedArrayList.getTasks()));
            break;
          }
          case FINISH: {
            String message = input.readLine();
            Task task = gson.fromJson(message, Task.class);
            innerLoop:for (Task value : sharedArrayList.getTasks())
            {
              if (task.equals(value))
              {
                value.finishTask();
                System.out.println(socket.getLocalAddress() + ": Finishing task request.");
                break innerLoop;
              }
            }
            broadcaster.broadcast(gson.toJson(sharedArrayList.getTasks()));
            break;
          }
          case EXIT: {
            System.out.println(socket.getLocalAddress() + ": Exiting request.");
            break loop;
          }
        }
      }
    }
    finally {
      socket.close();
    }
  }

  @Override public void run()
  {
    try
    {
      communicate();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}
