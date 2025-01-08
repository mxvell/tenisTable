package service;

import entities.MatchScore;
import entities.OngoingMatch;
import entities.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.Mac;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private final Map<String, OngoingMatch>matches = new HashMap<>();

    public String createMatch(Player player1, Player player2) {
        String uuid = UUID.randomUUID().toString();
        matches.put(uuid, new OngoingMatch(player1, player2));
        return uuid;
    }

    public OngoingMatch getMatch(String uuid) {
        return matches.get(uuid);
    }

    public void removeMatch(String uuid) {
        matches.remove(uuid);
    }
}
