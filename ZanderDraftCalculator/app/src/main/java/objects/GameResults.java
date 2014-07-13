package objects;

import java.io.Serializable;

public class GameResults implements Serializable {
    private int wins;
    private int losses;
    private int ties;

    GameOutcome outcome;

    public GameResults() {
        this(2, 0, 0, GameOutcome.BYE);
    }

    public GameResults(int wins, int losses, int ties, GameOutcome outcome) {
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;

        this.outcome = outcome;
    }

    public int GetWins() {
        return this.wins;
    }

    public int GetLosses() {
        return this.losses;
    }

    public int GetTies() {
        return this.ties;
    }

    public GameOutcome GetOutcome() {
        return this.outcome;
    }

    public int GamePoints() {
        return (GetWins() * 3) + GetTies();
    }

    public int GamesPlayed() {
        return GetWins() + GetLosses() + GetTies();
    }
}