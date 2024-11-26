package com;

import com.service.UserService;
import com.service.JourneyService;
import com.model.User;
import com.model.Route;
import com.model.Order;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.print.attribute.standard.Destination;

public class Main {
    private static List<User> users = new ArrayList<>();
    private static List<Route> routes = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static Map<String, Integer> userInvalidLoginAttempt = new HashMap<>();
    private static UserService userService = new UserService(users, userInvalidLoginAttempt);
    private static JourneyService journeyService = new JourneyService(routes, orders);
    static int count=0;
    public static void main(String[] args) {
        // Pre-populated the services with some data
    	initializeRoutes();

        if (displayCompanyLogo()) {
            showMenuOptions();
        } else {
            System.out.println("Failed to load company logo. Exiting...");
        }
    }

    private static boolean displayCompanyLogo() {
        System.out.println();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Niharika\\Desktop\\PROJECTS_VINAY\\VINAYTravels (1)\\VINAYTravels\\src\\com\\logo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            return true; // Logo loaded successfully
        } catch(IOException e) {
            System.err.println("Error reading company logo file: " + e.getMessage());
            return false; // Logo loading failed
        }
    }
    private static void initializeRoutes() {

        routes.add(new Route(1, "Nellore", "Hyderabad", LocalDate.parse("2024-02-20", DateTimeFormatter.ISO_LOCAL_DATE), 1000, 40));
        routes.add(new Route(2, "Hyderabad", "Nellore", LocalDate.parse("2024-02-19", DateTimeFormatter.ISO_LOCAL_DATE), 1000, 20));
        routes.add(new Route(3, "Hyderabad", "Tirupati", LocalDate.parse("2024-02-18", DateTimeFormatter.ISO_LOCAL_DATE), 1600, 30));
        routes.add(new Route(4, "Hyderabad", "Tirupati", LocalDate.parse("2024-02-17", DateTimeFormatter.ISO_LOCAL_DATE), 1600, 40));
        routes.add(new Route(5, "Hyderabad", "Warangal", LocalDate.parse("2024-02-16", DateTimeFormatter.ISO_LOCAL_DATE), 2500, 20));
        routes.add(new Route(6, "Hyderabad", "Warangal", LocalDate.parse("2024-02-15", DateTimeFormatter.ISO_LOCAL_DATE), 2500, 50));
        routes.add(new Route(7, "Nizamabad", "Kerala", LocalDate.parse("2024-02-14", DateTimeFormatter.ISO_LOCAL_DATE), 1000, 60));
        routes.add(new Route(8, "Karimnagar", "Hyderabad", LocalDate.parse("2024-02-13", DateTimeFormatter.ISO_LOCAL_DATE), 1500, 20));
        routes.add(new Route(9, "Warangal", "Hyderabad", LocalDate.parse("2024-02-12", DateTimeFormatter.ISO_LOCAL_DATE), 2500, 30));
        routes.add(new Route(10, "Warangal", "Vizag", LocalDate.parse("2024-02-11", DateTimeFormatter.ISO_LOCAL_DATE), 1000, 40));
        routes.add(new Route(11, "Warangal", "Nellore", LocalDate.parse("2024-02-10", DateTimeFormatter.ISO_LOCAL_DATE), 1000, 45));
        routes.add(new Route(12, "Tirupati", "Warangal", LocalDate.parse("2024-02-08", DateTimeFormatter.ISO_LOCAL_DATE), 1000, 30));
        routes.add(new Route(13, "Tirupati", "Hyderabad", LocalDate.parse("2024-02-07", DateTimeFormatter.ISO_LOCAL_DATE), 1500, 50));
        routes.add(new Route(14, "Vizag", "Hyderabad", LocalDate.parse("2024-02-06", DateTimeFormatter.ISO_LOCAL_DATE), 1000,50));
        routes.add(new Route(15, "Vizag", "Tirupati", LocalDate.parse("2024-02-09", DateTimeFormatter.ISO_LOCAL_DATE), 1000, 35));
        // ... add more routes as needed
    }
    private static void DisplayAllRoutes(){
        for(int i=0;i<routes.size();i++){
            System.out.println(routes.get(i));
        }
        System.out.println("\nSelect any route from the above routes");
    }


    private static void showMenuOptions() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean running = true;

        while (running) {
            System.out.println("\nMenu Options:");
            System.out.println("1. New Admin User Registration");
            System.out.println("2. Login");
            System.out.println("3. Plan journey");
            System.out.println("4. Reschedule booking date");
            System.out.println("5. Display all routes");
            System.out.println("6. Exiting..");

            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());
            System.out.println();
            switch (choice) {
            case 1:
                userService.registerNewAdmin();
                count++;
                break;
            case 2:
                userService.login();
                count++;
                break;
            case 3:
                if(count>=2)
                    journeyService.planJourney();
                else{
                    System.out.println("If Already registered!! Please Login");
                    System.out.println("If you are a New User Registerter First\n");
                }
                break;
            case 4:
                if(count>=2)
                    journeyService.reScheduleJourney();
                else{
                    System.out.println("If Already registered Login");
                    System.out.println("If you are a New User Registerter First\n");
                }
                break;
            case 5:
                DisplayAllRoutes();
                break;
            case 6:
                System.out.println("Exiting...");
                running = false;
                break;
            default:
                System.out.println("Invalid choice. Please enter a correct option.");
                break;
        }
    }
    scanner.close();  // Close the scanner when we're done with it
}
}
