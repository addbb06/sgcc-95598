import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Main {

    public static void main(String[] args) {

        List<Map<Integer, String>> list = new ArrayList<>();

        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream("C:\\Users\\bingo\\Desktop\\user.txt"), "UTF-8");

            BufferedReader reader = new BufferedReader(isr);

            String msg = null;

            while ((msg = reader.readLine()) != null) {

                Map<Integer, String> map = new HashMap<>();

                String name = msg.substring(0, msg.indexOf(" "));

                Integer age = Integer.valueOf(msg.substring(msg.indexOf(" ") + 1));

                map.put(age, name);

                list.add(map);
            }

        } catch (Exception e) {

        }



            new ServerListener(list).start();

    }
}
