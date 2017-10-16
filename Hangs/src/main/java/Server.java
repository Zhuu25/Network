/*
Maneekan Yanvisit  5810405258
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    DatabaseConnection db;

    public Server() throws IOException {
        db = new DatabaseConnection();
        connecting();
    }

    public static void main(String[] args){
        try {
            new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connecting() throws IOException {
        String vocab;
        ServerSocket serverSocket = new ServerSocket(2238);

        while (true){
            Socket socket = serverSocket.accept();

            //request to server
            BufferedReader intoServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //output to client
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            vocab = db.getWord();
            outputStream.writeBytes(vocab);
            intoServer.close();
            socket.close();

        }
    }
}
