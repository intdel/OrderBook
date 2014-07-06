package confighandling;

import errorhandling.HandleError;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * @author Christopher
 */
public class Confighandler {

    public static StringBuffer savefile;
    public static boolean status = false;

    public Confighandler() {
    }

    public Confighandler(InputStream in) {
        readConfig(in);
    }

    public static StringBuffer getSavefile() {
        return savefile;
    }

    public static void setSavefile(StringBuffer savefile) {
        Confighandler.savefile = savefile;
    }

    public static boolean isStatus() {
        return status;
    }

    public static void setStatus(boolean status) {
        Confighandler.status = status;
    }

    //Reads saveDir from configfile
    private void readConfig(InputStream in) {
        try {
            if (status == false) {
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String input;
                StringBuffer line;
                StringTokenizer stk;

                while ((input = br.readLine()) != null) {
                    stk = new StringTokenizer(input);
                    line = new StringBuffer(stk.nextToken("="));
                    if (line.toString().equalsIgnoreCase("savefile")) {
                        savefile = new StringBuffer(stk.nextToken(";").replace("\"", "").replace("=", ""));
                    }
                }
                status = true;
            }
        } catch (IOException e) {
            new HandleError().handleError(e);
        }
    }

}
