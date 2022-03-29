package Model;

public class Player {
    private String name;
    private PlayerType type;


    public Player(String name, PlayerType type) {
        this.type = type;
        this.name = name;
    }

    public PlayerType getType() {
        return type;
    }

    public void setType(PlayerType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
