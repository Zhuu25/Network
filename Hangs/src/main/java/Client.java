/*
Maneekan Yanvisit  5810405258
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Client extends Application{

    protected static int score = 0;
    protected static String type = "";
    protected static String vocab;
    protected static String user = "";
    public static Stage stage;

    @FXML
    protected TextField name;

    public static void main(String argv[]) {
        launch(argv);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        primaryStage.setTitle("Hangman!!");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void connecting(String type) throws IOException {
        Socket clientSocket = new Socket("localhost", 2238);

        ObjectOutputStream p = new ObjectOutputStream(clientSocket.getOutputStream());
        p.writeObject(new String(type));

        //in from server that return vocab
        BufferedReader buffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        vocab = buffer.readLine();
        if ((vocab.equals(null) == false) || (vocab.equals("") == false)){
            System.out.println("111");
        }
        else{
            System.out.println("666");
        }
        buffer.close();
        clientSocket.close();
    }

    @FXML
    public void startGame(ActionEvent event) {
        user = name.getText();
        Button button = (Button) event.getSource();
        if (button.getId().equals("ani")){
            type = "Animal";
        }
        if (button.getId().equals("fruit")){
            type = "Fruit";
        }
        if (button.getId().equals("sport")){
            type = "Sport";
        }
        if (button.getId().equals("network")){
            type = "Network";
        }
        if (button.getId().equals("city")){
            type = "City";
        }
        if (button.getId().equals("country")){
            type = "Country";
        }
        stage = (Stage) button.getScene().getWindow();
        try {
            connecting(type);
            Parent loadMain = FXMLLoader.load(getClass().getResource("ModelPage.fxml"));
            stage.setScene(new Scene(loadMain));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setScore(int sc){
        score = sc;
    }

}
