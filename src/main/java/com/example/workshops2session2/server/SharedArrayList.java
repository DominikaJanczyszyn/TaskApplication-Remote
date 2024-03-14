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

  public ArrayList<Task> getTasks()
  {
    return tasks;
  }
}
