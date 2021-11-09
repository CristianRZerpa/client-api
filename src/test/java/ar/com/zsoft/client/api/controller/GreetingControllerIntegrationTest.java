package ar.com.zsoft.client.api.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class GreetingControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingTest() throws JSONException {
        String response = this.restTemplate.getForObject("/greeting", String.class);
        JSONAssert.assertEquals("{id:1, content:\"Hello, World!\"}",
                response, false);
    }

    @Test
    public void greetingWithNameTest() throws JSONException {
        String response = this.restTemplate.getForObject("/greeting?name=Cristian", String.class);
        JSONAssert.assertEquals("{id:2, content:\"Hello, Cristian!\"}",
                response, false);
    }

}