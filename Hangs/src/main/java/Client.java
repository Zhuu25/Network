/*
Maneekan Yanvisit  5810405258
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client{

    public static void main(String argv[]) {
        try {
            new Client();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client() throws IOException {
        connecting();
    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ModelGUI.fxml"));
        primaryStage.setTitle("Network Hangman!!");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void connecting() throws IOException {
        String vocab;
        Socket clientSocket = new Socket("localhost", 2238);

        //out to server to request vocab
        DataOutputStream request = new DataOutputStream(clientSocket.getOutputStream());
//        request.writeBytes("Request");

        //in from server that return vocab
        BufferedReader buffer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        vocab = buffer.readLine();
        buffer.close();
        clientSocket.close();
        MainGUI main = new MainGUI();

    }
}
