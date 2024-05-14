import Player.Player;
import Rooms.Room;

import java.io.Serializable;
import java.util.List;

public class GameData implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Room> rooms;
    private Player player;
    private int turns;

    public GameData(List<Room> rooms, Player player, int turns) {
        this.rooms = rooms;
        this.player = player;
        this.turns = turns;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Player getPlayer() {
        return player;
    }

    public int getTurns() {
        return turns;
    }

}