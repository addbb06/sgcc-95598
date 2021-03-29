import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;

public class ChatSocket extends Thread {
    Socket socket;

    public ChatSocket(Socket socket) {

        this.socket = socket;
    }

    public void out(String out) {
        try {
            socket.getOutputStream().write(out.getBytes("UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
       /*
       * 先写后读
       * */

        try {
            Writer writer=new OutputStreamWriter(socket.getOutputStream());
            writer.write("input：\n ");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}