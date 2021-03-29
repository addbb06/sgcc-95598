import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServerListener extends Thread {
    private List<Map<Integer, String>> user;

    public ServerListener(List<Map<Integer, String>> user){
        this.user = user;
    }

    @Override
    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(1521);
            while (true) {        //一有客户连接就起一个线程
                Socket socket = serverSocket.accept();
                JOptionPane.showMessageDialog(null, "客户连接1521端口");
                new ChatSocket(socket).start();
                new Thread(new Tasks(socket,user)).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
