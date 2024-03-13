package com.example.workshops2session2.client;

import com.example.workshops2session2.Model.State;
import com.example.workshops2session2.Model.StateInterfaceAdapter;
import com.example.workshops2session2.Model.Task;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientImpl implements Client
{
  private final Socket socket;
  private final PrintWriter output;
  private final BufferedReader input;
  private final MessageListener listener;
  private final PropertyChangeSupport support;
  private final Gson gson;

  private final static String GET = "GET";
  private final static String ADD = "ADD";
  private final static String START = "START";
  private final static String FINISH = "FINISH";
  private final static String EXIT = "EXIT";


  public ClientImpl(String host, int port, String groupAddress, int groupPort) throws
      IOException
  {
    this.socket = new Socket(host, port);
    this.output = new PrintWriter(socket.getOutputStream());
    this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    this.support = new PropertyChangeSupport(this);
    this.gson = new GsonBuilder().registerTypeAdapter(State.class, new StateInterfaceAdapter()).create();

    this.listener = new MessageListener(this, groupAddress, groupPort);
    Thread thread = new Thread(listener);
    thread.start();
  }

  @Override public ArrayList<Task> getTasks() throws IOException
  {
    output.println(GET);
    output.flush();
    ArrayList<Task> tasks = gson.fromJson(input.readLine(), new TypeToken<ArrayList<Task>>() {}.getType());
    return tasks;
  }

  @Override public void startTask(Task task)
  {
    output.println(START);
    output.println(gson.toJson(task));
    output.flush();
  }

  @Override public void finishTask(Task task)
  {
    output.println(FINISH);
    output.println(gson.toJson(task));
    output.flush();
  }

  @Override public void addTask(Task task)
  {
    output.println(ADD);
    output.println(gson.toJson(task));
    output.flush();
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    this.support.addPropertyChangeListener(listener);
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    this.support.removePropertyChangeListener(listener);
  }

  public void receiveBroadcast(String message) {
    ArrayList<Task> update = gson.fromJson(message, new TypeToken<ArrayList<Task>>() {}.getType());
    support.firePropertyChange("List", null, update);
  }

  @Override public void close() throws IOException
  {
    listener.close();
    output.println(EXIT);
    output.flush();
    socket.close();
  }
}
