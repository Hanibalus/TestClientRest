
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.http.client.ClientProtocolException;
import org.jboss.resteasy.client.ClientExecutor;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientRequestFactory;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.client.core.executors.ApacheHttpClientExecutor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Eugen.Popa
 */
public class RESTEasyClientGet {

    public static void main(String[] args) {


            String userId = "rest";
            String password = "rest123";

            Credentials credentials = new UsernamePasswordCredentials(userId, password);
            HttpClient httpClient = new HttpClient();
            httpClient.getState().setCredentials(AuthScope.ANY, credentials);
            httpClient.getParams().setAuthenticationPreemptive(true);

            ClientExecutor clientExecutor = new ApacheHttpClientExecutor(httpClient);

            try {
                URI uri = new URI("http://localhost:8080/RESTfulExample/json/product/get");
                ClientRequestFactory fac = new ClientRequestFactory(clientExecutor, uri);
                
                ClientRequest request = fac.createRequest("http://localhost:8080/RESTfulExample/json/product/get");
                //ClientRequest request = new ClientRequest(
                //        "http://localhost:8080/RESTfulExample/json/product/get");

                request.accept("application/json");

                ClientResponse<String> response = request.get(String.class);

                if (response.getStatus() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatus());
                }

                BufferedReader br = new BufferedReader(new InputStreamReader(
                        new ByteArrayInputStream(response.getEntity().getBytes())));

                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }

            } catch (ClientProtocolException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            } catch (Exception e) {

                e.printStackTrace();

            }

        }

    }
