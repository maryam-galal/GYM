package com.example.mainpage;

import java.util.ArrayList;
import java.util.Collections;

public class Coach extends Person {

    private int working_hours;
    private int Startinghour;
    private int Endinghour;
    protected ArrayList<Customer> customerArrayList = new ArrayList<>();

    private static final int MAX_CUSTOMERS = 10;
    protected int numberOfCustomers = 0;

    public int getNumberOfCustomers() {
        return numberOfCustomers;
    }

    public void setNumberOfCustomers(int numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    public void setWorking_hours(int working_hours) {
        this.working_hours = working_hours;
    }
    public int getWorking_hours(){
        return working_hours;
}

    public int getStartinghour() {
        return Startinghour;
    }

    public void setStartinghour(int startinghour) {
        Startinghour = startinghour;
    }

    public int getEndinghour() {
        return Endinghour;
    }

    public void setEndinghour(int endinghour) {
        Endinghour = endinghour;
    }

    private static int coachCounter = 0;
       public Coach() {
           if (MainApplication.coachArrayList.size() > 0) {
               // Increment the counter based on the size of the ArrayList
               coachCounter = MainApplication.coachArrayList.size() + 1;
           } else {
               // Initialize the counter to 1 if the ArrayList is empty
               coachCounter = 1;
           }

           id = "A1" + coachCounter;
       }

//    public boolean canAcceptCustomer() {
//        if((numberOfCustomers < MAX_CUSTOMERS) && (working_hours < 10)) {
//            working_hours++;
//            numberOfCustomers++;
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
public void assignCoachToCustomer(Customer customer) {
    Collections.shuffle(MainApplication.coachArrayList);
    for (Coach coach : MainApplication.coachArrayList) {
         if(customerArrayList.size() < 11){
            System.out.println(customer.getId());
            System.out.println(coach.getId());
            coach.customerArrayList.add(customer);
            customer.setAssignedCoach(coach);
            Subscription.setCoach_id(coach.getId());
            break;
        }
    }
}

    public void setWorking_hours(String working_hours) {
          this.working_hours= Integer.parseInt(working_hours);
    }
}
