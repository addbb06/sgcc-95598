import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tasks implements Runnable {

    private Socket client;
    private List<Map<Integer, String>> user;

    public Tasks(Socket client,List<Map<Integer, String>> user) {

        this.client = client;
        this.user = user;
    }

    @Override
    public void run() {
                try {
                      handlerSocket();
                   } catch (IOException e) {
                      e.printStackTrace();
                   }
          }

    private void handlerSocket() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String temp;
        int index;
        while ((temp=br.readLine()) != null) {
            System.out.println(temp);
            if ((index = temp.indexOf("over")) != -1) {//遇到over时就结束接收
                sb.append(temp.substring(0, index));
                break;
            }
            sb.append(temp);
        }
        System.out.println("from client: " + client.getPort());

        Writer writer = new OutputStreamWriter(client.getOutputStream(),"UTF-8");
        //处理接收的查询语句
        if (sb.indexOf("select") != -1 && sb.indexOf("name") != -1 && sb.indexOf("age") != -1 && sb.indexOf("user") != -1){

            /*user.forEach((k,v)-> writer.write());*/
            for (Map<Integer, String> m:user){
                for (Integer k : m.keySet())
                {
                    System.out.println(k+" "+m.get(k));
                    writer.write(k+" "+m.get(k)+" ");
                }
            }


        }









        //读完后写一句
       /* Writer writer = new OutputStreamWriter(client.getOutputStream());*/
        writer.write("Hello Client.");
        writer.write("over\n");
        writer.flush();
        writer.close();
        br.close();
        client.close();

    }
}
