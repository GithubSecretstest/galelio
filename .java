import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;

public class HTTPBasicAuthExample {
    public static void main(String[] args) {
        try {
            // URL to make the HTTP request
            URL url = new URL("http://example.com/api/resource");

            // Open a connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Set up Basic Authentication
            String username = "admin";
            String password = "tiara token";
            String authString = username + ":" + password;
            // Encode the authentication string
            String encodedAuthString = Base64.getEncoder().encodeToString(authString.getBytes());
            // Set the Authorization header
            connection.setRequestProperty("Authorization", "Basic " + encodedAuthString);

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response
            System.out.println("Response: " + response.toString());

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
