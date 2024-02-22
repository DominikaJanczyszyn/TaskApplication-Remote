package com.example.workshops2session2.Threads;

import com.example.workshops2session2.Model.Model;
import com.example.workshops2session2.Model.Person;
import com.example.workshops2session2.Model.Task;

import java.util.ArrayList;
import java.util.Random;

public class Anna implements Runnable{

    public Person person;
    public Model model;
    public ArrayList<Task> tasks;

    public Anna(Model modelManager){
        this.model = modelManager;
        this.person = new Person("Anna Kowalska");
        this.tasks = new ArrayList<>();

    }
    @Override
    public void run() {
        while(true){
            Random randomTime = new Random();
            Random randomTask = new Random();
            Random randomAction = new Random();

            Task task = new Task("task"+tasks.size(), "description"+tasks.size(), person);
            model.addTask(task);
            tasks.add(task);
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
                    System.out.println("Anna : added " +task.getTitle() );
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            else if(numberAction == 1){
                try{
                    model.startTask(tasks.get(numberTask));
                    System.out.println("Anna : started " +task.getTitle() );
                }catch (RuntimeException e){
                    System.out.println("Anna : " +e.getMessage());
                }
            }
            else if(numberAction == 2){
                try {
                    model.finishTask(tasks.get(numberTask));
                    System.out.println("Anna : finished " +task.getTitle() );
                } catch (RuntimeException e) {
                    System.out.println("Anna : "+ e.getMessage());
                }
            }

        }
    }
}
