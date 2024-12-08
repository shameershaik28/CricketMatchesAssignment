package com.cricket.assignment;

public class CricketMatchesAssignment {
    public static void main(String[] args) {
        try {
            // API URL and key
            String apiUrl = "https://api.cuvora.com/car/partner/cricket-data";
            String apiKey = "test-creds@2320";

            // Fetch API data
            CricketDataFetcher dataFetcher = new CricketDataFetcher();
            String jsonResponse = dataFetcher.fetchCricketData(apiUrl, apiKey);

            // Debugging: Print Raw Response
            System.out.println("Raw API Response:");
            System.out.println(jsonResponse);

            // Analyze the fetched data
            CricketMatchAnalyzer analyzer = new CricketMatchAnalyzer();
            analyzer.analyzeCricketData(jsonResponse);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

}
