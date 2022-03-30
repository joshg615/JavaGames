package Model;

public class Player {
    private String name;
    private PlayerType type;

    //Constructor for player that takes a name and Player type
    public Player(String name, PlayerType type) {
        this.type = type;
        this.name = name;
    }
    //getter for the playertype
    public PlayerType getType() {
        return type;
    }
    //setter for the playertype
    public void setType(PlayerType type) {
        this.type = type;
    }
    //getter for the name
    public String getName() {
        return name;
    }
    //setter for name
    public void setName(String name) {
        this.name = name;
    }
}
