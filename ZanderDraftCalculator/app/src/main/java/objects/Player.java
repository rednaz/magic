package objects;

import java.io.Serializable;
import java.util.ArrayList;

public class Player implements Serializable {
    private String name;
    private ArrayList<Record> myRecord;
    private ArrayList<GameResults> currResults;
    private ArrayList<Player> opponents;

    public Player(String name) {
        this.name = name;

        this.myRecord = new ArrayList<Record>();
        this.currResults = new ArrayList<GameResults>();
        this.opponents = new ArrayList<Player>();
    }

    public String GetName() {
        return this.name;
    }

    public void matchResults(GameResults result, Player opponent) {
        this.currResults.add(result);
        this.opponents.add(opponent);
    }

    public ArrayList<GameResults> GetCurrentResult() {
        return this.currResults;
    }

    public ArrayList<Player> GetOpponents() {
        return this.opponents;
    }
}