package pw.roccodev.bukkit.practice.arena;

import org.bukkit.entity.Player;
import pw.roccodev.bukkit.practice.arena.proto.TeamPrototype;

import java.util.List;

public class ArenaTeam {

    private TeamPrototype proto;

    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public TeamPrototype getPrototype() {
        return proto;
    }
}