package com.dwis.calc.resources;

import io.dropwizard.testing.junit.ResourceTestRule;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Dominik on 02.04.16.
 */
public class HelloResourceTest {
    @Rule
    public ResourceTestRule resource = ResourceTestRule.builder()
            .addResource(new HelloResource("Hello ", "world")).build();

    @Test
    public void testGetGreeting() {

        String expected = "{\"id\":1,\"content\":\"Hello \"}";
        //Obtain client from @Rule.
        Client client = resource.client();
        //Get WebTarget from client using URI of root resource.
        WebTarget helloTarget = client.target("http://localhost:8080/hello");
        //To invoke response we use Invocation.Builder
        //and specify the media type of representation asked from resource.
        Invocation.Builder builder = helloTarget.request(MediaType.APPLICATION_JSON);
        //Obtain response.
        Response response = builder.get();

        //Do assertions.
        assertEquals(Response.Status.OK, response.getStatusInfo());
        String actual = response.readEntity(String.class);
        assertEquals(expected, actual);

    }


}
