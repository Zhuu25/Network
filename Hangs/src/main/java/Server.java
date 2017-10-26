/*
Maneekan Yanvisit  5810405258
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    protected static DatabaseConnection db = new DatabaseConnection();
    protected static ServerSocket serverSocket;

    public static void main(String[] args){
        try {
            serverSocket = new ServerSocket(2238);
            System.out.println("Server is waiting for connection");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            String request = "";
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Internet server is connecting...");

                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                try {
                    request = (String) input.readObject();
                    if ((request.equals(null) == false) || (request.equals("") == false)){
                        System.out.println("111");
                    }
                    else{
                        System.out.println("555");
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                String vocab = db.getWord(request);
                output.writeBytes(vocab);
                input.close();
                output.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
