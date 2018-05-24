package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by User on 2018-05-24.
 */

public class KServer {
    static boolean mRunning = true;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(9712);
            System.out.println("Server Start!");

            while (mRunning) {
                Socket socket = server.accept();
                System.out.println("Connected!");
                
                socket.close();
            }
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
