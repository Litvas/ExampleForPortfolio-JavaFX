package stockprog;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.log4j.Logger;
import stockprog.entity.Stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.SplittableRandom;


public class RequestHandlerImpl implements RequestHandler {

    private static final Logger LOG = Logger.getLogger(RequestHandlerImpl.class);

    public String sendRequest(URL url) {
        String result = null;
        String lineFromResponce;
        StringBuilder builder = new StringBuilder();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while ((lineFromResponce = bufferedReader.readLine()) != null) {
                builder.append(lineFromResponce);
            }
            result = builder.toString();
        } catch (IOException ioExeption) {
            LOG.error("FAILED! Request hasn`t sent! " + ioExeption.getMessage());

        }
        return result;
    }

    public Map<String, Object> parseRequest(String responce) {
        responce = prepareString(responce);
        Map<String, Object> result = new HashMap<String, Object>();
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = jsonParser.parse(responce).getAsJsonArray();
        JsonObject jsonObject = jsonArray.get(0).getAsJsonObject();

        result.put("id", jsonObject.get("id").toString());
        result.put("companyCode", jsonObject.get("t").toString());
        result.put("highInitialOpeningPrice", jsonObject.get("pcls_fix").getAsDouble());
        result.put("lowInitialOpeningPrice", jsonObject.get("l").getAsDouble());
        return result;
    }

    private String prepareString(String responce) {
        String temp = responce.replace("// ", "");
        responce = temp;
        return responce;
    }
}
