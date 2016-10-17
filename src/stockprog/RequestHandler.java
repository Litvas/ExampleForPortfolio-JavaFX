package stockprog;

import java.net.URL;
import java.util.Map;

/**
 * Created by AS on 07.10.16.
 */
public interface RequestHandler {

    /*This method sends Get-request to server and return responce as String*/
    String sendRequest(URL url);

    /*This method parses String to Collection for testing*/
    Map<String,Object> parseRequest(String responce);

}
