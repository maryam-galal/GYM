package com.example.mainpage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MainApplication extends Application {
    private static List<Gym> gyms;
    protected static ArrayList<Customer> customerArrayList = new ArrayList<>();
    protected static ArrayList<Coach> coachArrayList = new ArrayList<>();
    protected static ArrayList<InBody> InBodyList = new ArrayList<>();
    protected static ArrayList<Membership_Plan> membershipPlanArrayList = new ArrayList<>();

    protected static ArrayList<String[]> userList = new ArrayList<>();
    protected static ArrayList<String[]> InBody_Data = new ArrayList<>();
    protected static ArrayList<String []> Subscription_Data = new ArrayList<>();

    private static Stage primarystage;

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    private static List<Gym> readGymsFromFile(String filePath) {
        List<Gym> gyms = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String name = bufferedReader.readLine();
            String address = bufferedReader.readLine();
            String phoneNumber = bufferedReader.readLine();

            if (name != null && address != null && phoneNumber != null) {
                Gym gym = new Gym(name.trim(), address.trim(), phoneNumber.trim());
                gyms.add(gym);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return gyms;
    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void start(Stage stage) throws IOException {
        primarystage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);


        // load all data to userList
        Files.Load_ArrayList("Registration.csv");
        Files.Load_coach_customer();
        Files.Load_ArrayList("InBody.csv");
        Files.Load_InBody();
        Files.Load_ArrayList("Subscription.csv");
        Files.Load_Subscription();



        // Set the application icon
        stage.getIcons().add(new Image("file:D:\\Projects\\2nd Year\\OOP\\GYM\\src\\main\\resources\\com\\example\\mainpage\\Gym Icon.png"));
        stage.setTitle("Fitness Gym");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public void changeScene(String fxml) throws IOException {
        // System.out.println("Changing scene to: " + fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxml));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primarystage.setScene(scene);
        primarystage.setResizable(false);
        primarystage.show();

    }
    @Override
    public void init() {
        String filePath = "D:\\Projects\\2nd Year\\OOP\\GYM\\src\\main\\resources\\com\\example\\mainpage\\Gyminfo_class.txt";
        gyms = readGymsFromFile(filePath);
    }

    public static List<Gym> getGyms() {
        return gyms;
    }

    public static ArrayList<String[]> return_userList(){
        return userList;
    }
    public static ArrayList<Customer> return_customerList(){
        return customerArrayList;
    }
    public static ArrayList<Coach> return_coachList(){
        return coachArrayList;
    }



    public static void main(String[] args) {
        launch(args);
    }
    public static void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);

        try {
            // Load your icon image
            Image icon = new Image("file:C:\\Users\\Mariam\\IdeaProjects\\mainpage\\src\\main\\resources\\com\\example\\mainpage\\error_icon.png");

            // Create an ImageView with the icon
            ImageView imageView = new ImageView(icon);
            imageView.setFitWidth(48); // Set the width of the icon
            imageView.setFitHeight(48); // Set the height of the icon

            // Set the graphic of the Alert's DialogPane to the ImageView
            alert.getDialogPane().setGraphic(imageView);

            // Set the application icon in the title bar of the alert
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(icon);
        } catch (IllegalArgumentException e) {
            // Handle the exception (e.g., log the error)
            e.printStackTrace();
        }

        // Show the alert
        alert.showAndWait();
    }
}