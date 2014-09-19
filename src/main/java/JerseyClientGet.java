
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import javax.ws.rs.core.MediaType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Eugen.Popa
 */
public class JerseyClientGet {

    public static void main(String[] args) {
        try {

        //    ClientConfig clientConfig = new DefaultClientConfig();
        //    clientConfig.getFeatures().put(
        //            JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

        //    Client client = Client.create(clientConfig);
            Client client = Client.create();
            client.addFilter(new HTTPBasicAuthFilter("rest", "rest123"));

            WebResource webResource = client
                    .resource("http://localhost:8080/TestRest/rest/json/metallica/get");

            ClientResponse response = webResource.accept("application/json").type(MediaType.APPLICATION_JSON)
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            } else {
                System.out.println(response.getStatus());
            }

            Track output = response.getEntity(Track.class);

            System.out.println("Output from Server .... \n");
            System.out.println(output.toString());

            // delete
            WebResource webResourceDel = client
                    .resource("http://localhost:8080/TestRest/rest/json/metallica/delete");
            ClientResponse responseDel = webResourceDel
                    .delete(ClientResponse.class);

            if (responseDel.getStatus() != 204) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + responseDel.getStatus());
            } else {
                System.out.println("Deleted " + responseDel.getStatus());
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
