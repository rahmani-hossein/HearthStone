package logic;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DeckReader {
    String address;
    ObjectMapper objectMapper;

    public DeckReader(String name) {
        address = String.format("resources/deck/%s.json", name);
        objectMapper =new ObjectMapper();
        
    }
}
