package platforms.Report;

import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class TestExecutionReportWriter {

    public static void ElasticSend(String[] args) throws IOException {

        JsonObject json = new JsonObject();
        json.addProperty("project", "Your Project");
        json.addProperty("scenario", args[0]);
        json.addProperty("status", args[1]);
        json.addProperty("platform", args[5]);
        json.addProperty("tags", args[3]);
        json.addProperty("tool", args[2]);
        json.addProperty("feature", args[4]);

        RestClient restClient = kibanaConnection();
        HttpEntity entity = kibanaAddRequestData(restClient, json);
    }

    public static HttpEntity kibanaAddRequestData(RestClient restClient, JsonObject json){
        try {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date());
            json.addProperty("@timestamp", timeStamp);
            HttpEntity entity = new NStringEntity(
                    json.toString(), ContentType.APPLICATION_JSON);
            restClient.performRequest(
                    "POST",
                    "/test01/Results",
                    Collections.<String, String>emptyMap(),
                    entity);
            restClient.close();
            return entity;

        }catch(Exception e){
            return null;
        }
    }

    public static RestClient kibanaConnection(){
        try {
            RestClient restClient = RestClient.builder(
                    new HttpHost("x.node.test.x.x.co.za", 9200, "http"),
                    new HttpHost("x.node.test.x.x.co.za", 9205, "http")).build();
            return restClient;
        } catch(Exception e){
            //logger
            return null;
        }
    }

    public static void kibanaSend(RestClient restClient, HttpEntity entity){
        try {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX").format(new Date());
            restClient.performRequest(
                    "PUT",
                    "/peter/peter/" + timeStamp,
                    Collections.<String, String>emptyMap(),
                    entity);
            restClient.close();
        }catch (Exception e){
            //logger
        }
    }
}
