package com.cricket.assignment;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Iterator;

public class CricketMatchAnalyzer {
    // Method to analyze JSON data and compute results
    public void analyzeCricketData(String jsonResponse) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonResponse);

        // Print the response as a readable JSON tree
        System.out.println("Parsed JSON Response:");
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode));

        int highestScore = 0;
        String highestScoringTeam = "";
        int matchesAbove300 = 0;

        // Extract 'data' field and check if it's an array
        JsonNode matches = rootNode.get("data");
        if (matches == null || !matches.isArray()) {
            throw new Exception("'data' field is missing or not an array.");
        }

        // Iterate over matches
        Iterator<JsonNode> iterator = matches.iterator();
        while (iterator.hasNext()) {
            JsonNode match = iterator.next();
            String t1 = match.has("t1") ? match.get("t1").asText() : "Unknown Team";
            String t2 = match.has("t2") ? match.get("t2").asText() : "Unknown Team";

            int t1Score = ScoreParser.parseScore(match.has("t1s") ? match.get("t1s") : null);
            int t2Score = ScoreParser.parseScore(match.has("t2s") ? match.get("t2s") : null);

            System.out.println("Team 1: " + t1 + ", Score: " + t1Score);
            System.out.println("Team 2: " + t2 + ", Score: " + t2Score);

            // Update highest score
            if (t1Score > highestScore) {
                highestScore = t1Score;
                highestScoringTeam = t1;
            }
            if (t2Score > highestScore) {
                highestScore = t2Score;
                highestScoringTeam = t2;
            }

            // Check if total score exceeds 300
            if (t1Score + t2Score > 300) {
                matchesAbove300++;
            }
        }

        // Print results
        System.out.println("Highest Score: " + highestScore + " by " + highestScoringTeam);
        System.out.println("Number of matches with total 300+ score: " + matchesAbove300);
    }
}
