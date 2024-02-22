package com.example.workshops2session2.Threads;

import com.example.workshops2session2.Model.Model;
import com.example.workshops2session2.Model.Person;
import com.example.workshops2session2.Model.Task;

import java.util.ArrayList;
import java.util.Random;

public class Bob implements Runnable {
    public Person person;
    public Model model;
    public ArrayList<Task> tasks;

    public Bob(Model modelManager){
        this.model = modelManager;
        this.person = new Person("Bob Bobowski");
        this.tasks = new ArrayList<>();

    }
    @Override
    public void run() {
        while(true){
            Random randomTime = new Random();
            Random randomTask = new Random();
            Random randomAction = new Random();

            Task task = new Task("task"+tasks.size(), "description"+tasks.size(), person);
            tasks.add(task);
            model.addTask(task);
            Task task1 = new Task("task"+tasks.size(), "description"+tasks.size(), person);
            model.addTask(task1);
            tasks.add(task1);
            Task task2 = new Task("task"+tasks.size(), "description"+tasks.size(), person);
            model.addTask(task2);
            tasks.add(task2);

            int numberTime = randomTime.nextInt(10) * 1000;
            int numberAction = randomAction.nextInt(3);
            int numberTask = randomTask.nextInt(tasks.size());

            try {
                Thread.sleep(numberTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(numberAction == 0){
                try{
                    Task task3 = new Task("task"+tasks.size(), "description"+tasks.size(), person);
                    model.addTask(task3);
                    tasks.add(task3);
                    System.out.println("Bob : added " +task.getTitle() );
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if(numberAction == 1){
                try{
                    model.startTask(tasks.get(numberTask));
                    System.out.println("Bob : started " +task.getTitle() );
                }catch (Exception e){
                    System.out.println("Bob: " +e.getMessage());
                }
            }
            else if(numberAction == 2){
                try {
                    model.finishTask(tasks.get(numberTask));
                    System.out.println("Bob : finished " +task.getTitle() );
                } catch (Exception e) {
                    System.out.println("Bob: " +e.getMessage());
                }
            }

        }
    }
}
