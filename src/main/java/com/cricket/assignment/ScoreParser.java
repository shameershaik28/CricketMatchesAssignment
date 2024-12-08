package com.cricket.assignment;

import com.fasterxml.jackson.databind.JsonNode;

public class ScoreParser {
    // Helper method to parse score strings
    public static int parseScore(JsonNode scoreNode) {
        try {
            if (scoreNode != null && !scoreNode.asText().isEmpty()) {
                // Handle cases where score is in format "123/4"
                String scoreText = scoreNode.asText().split("/")[0]; // Extract runs from "123/4"
                return Integer.parseInt(scoreText); // Parse the score
            } else {
                System.err.println("Missing or empty score");
            }
        } catch (Exception e) {
            System.err.println("Invalid score format: " + scoreNode);
        }
        return 0; // Default to 0 if the score is invalid or missing
    }
}
