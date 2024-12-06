import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CricketMatchesAssignment {

    public static void main(String[] args) {
        try {
            // Fetch the cricket matches data from the API
            String response = fetchMatchesData();

            // Print the raw JSON response
            System.out.println("API Response: ");
            System.out.println(response);

        } catch (Exception e) {
            // Handle any errors
            e.printStackTrace();
        }
    }
    private static String fetchMatchesData() throws Exception {
        String apiUrl = "https://api.cuvora.com/car/partner/cricket-data";
        String apiKey = "test-creds@2320";

        // Set up the connection to the API
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("apiKey", apiKey);

        // Read the API response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Return the response as a string
        return response.toString();
    }

}
