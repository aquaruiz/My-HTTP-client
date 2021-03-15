package me.working;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet("https://hc.apache.org/httpcomponents-client-4.5.x/quickstart.html");
        CloseableHttpResponse response = httpclient.execute(httpGet);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        HttpPost httpPost = new HttpPost("https://hc.apache.org/httpcomponents-client-4.5.x/quickstart.html");
        List<NameValuePair> nvPairs = new ArrayList<>();
        nvPairs.add(new BasicNameValuePair("user", "you"));
        nvPairs.add(new BasicNameValuePair("pass", "1234"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvPairs));

        CloseableHttpResponse otherResponse = httpclient.execute(httpPost);

        try {
            System.out.println(otherResponse.getStatusLine());
            HttpEntity otherEntity = otherResponse.getEntity();
            EntityUtils.consume(otherEntity);
        } finally {
            otherResponse.close();
        }

        // using fluent hc
        Request.Get("http://www.google.com")
                .execute().returnContent().asString();

        // get body
        CloseableHttpResponse yetAnotherResponse = httpclient.execute(new HttpGet("http://www.google.com"));
        String bodyAsString = EntityUtils.toString(yetAnotherResponse.getEntity());

        System.out.println();
    }
}
