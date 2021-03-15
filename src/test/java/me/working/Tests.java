package me.working;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Tests {

    public static final String SAMPLE_URL = "http://www.google.com";

    @Test
    void givenGetRequestExecuted_whenAnalyzingTheResponse_thenCorrectStatusCode() throws IOException {

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(new HttpGet(SAMPLE_URL));
        int statusCode = response.getStatusLine().getStatusCode();
        assertEquals(HttpStatus.SC_OK, statusCode);
    }

}
