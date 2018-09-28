//20160745 JaeEunCho
package Server;

import android.util.Log;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by User on 2018-05-31.
 */

public class ServerActivity {
    private static String PhoneNum, DateText1, DateText2, TimeText1, TimeText2, IntervalText = null;

    public static void main(String[] arg) {
        try {
            ServerSocket server = new ServerSocket(9999);
            System.out.println("Server Started...");

            boolean mRunning = true;
            while (mRunning) {
                Socket socket = server.accept();
                System.out.println("Connected...");

                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                int what = dis.readInt();
                // 어느 Activity에서 서버를 가동시키는지 확인
                // 1=Who 2=When 3=Access 4=Service
                if (what == 1) {
                    System.out.println("Activity is [ WhoActivity ]");
                }
                else if (what == 2) {
                    System.out.println("Activity is [ WhenActivity ]");
                }
                else if (what == 3) {
                    System.out.println("Activity is [ AccessActivity ]");
                }
                else if (what == 4) {
                    System.out.println("Activity is [ Service ]");
                }

                if (what == 1) {
                    PhoneNum = dis.readUTF();
                }
                else if (what == 2) {
                    DateText1 = dis.readUTF();
                    DateText2 = dis.readUTF();
                    TimeText1 = dis.readUTF();
                    TimeText2 = dis.readUTF();
                    IntervalText = dis.readUTF();
                }
                else if (what == 3) {
                    dos.writeUTF(PhoneNum);
                    dos.writeUTF(DateText1);
                    dos.writeUTF(DateText2);
                    dos.writeUTF(TimeText1);
                    dos.writeUTF(TimeText2);
                    dos.writeUTF(IntervalText);
                }
                else if (what == 4) {
                    dos.writeUTF(PhoneNum);
                    dos.writeUTF(DateText1);
                    dos.writeUTF(DateText2);
                    dos.writeUTF(TimeText1);
                    dos.writeUTF(TimeText2);
                    dos.writeUTF(IntervalText);
                }
                else {
                    System.out.println("No Activity");
                }

                socket.close();
            }
            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
