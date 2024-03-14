package com.example.workshops2session2.server;

import com.example.workshops2session2.Model.Task;

import java.util.ArrayList;

public class SharedArrayList
{
  private ArrayList<Task> tasks;
  private static SharedArrayList instance;

  private SharedArrayList() {
    this.tasks = new ArrayList<>();
  }

  public static synchronized SharedArrayList getInstance() {
    if (instance == null) {
      instance = new SharedArrayList();
    }
    return instance;
  }

  public synchronized ArrayList<Task> getTasks()
  {
    return tasks;
  }

  public synchronized void addTask(Task task) {
    this.tasks.add(task);
  }

  public synchronized void startTask(Task task) {
    for (int i = 0; i < tasks.size(); i++)
    {
      if (tasks.get(i).equals(task)) {
        tasks.get(i).startTask();
        break;
      }
    }
  }

  public synchronized void finishTask(Task task) {
    for (int i = 0; i < tasks.size(); i++)
    {
      if (tasks.get(i).equals(task)) {
        tasks.get(i).finishTask();
        break;
      }
    }
  }
}
