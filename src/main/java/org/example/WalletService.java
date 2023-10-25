package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class WalletService {
    private Map<String, Player> players;

    public WalletService() {
        players = new ConcurrentHashMap<>();
    }

    public void registerPlayer(String username, String password) {
        if (players.containsKey(username)) {
            System.out.println("Username already exists.");
        } else {
            Player player = new Player(username, password);
            players.put(username, player);
        }
    }

    public Player login(String username, String password) {
        Player player = players.get(username);
        if (player != null && player.getPassword().equals(password)) {
            return player;
        } else {
            return null;
        }
    }

    public void auditAction(Player player, String action) {
        System.out.println("User " + player.getUsername() + " performed " + action);
    }
}
