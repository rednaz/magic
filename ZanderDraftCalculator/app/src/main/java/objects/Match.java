package objects;

/**
 * Created by Zander on 7/14/2014.
 */
public class Match {
    private GameOutcome outcome;

    private int wins;
    private int losses;
    private int ties;

    private Player opponent;

    public Match (int wins, int losses, int ties, Player opponent) {
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;

        this.opponent = opponent;

        if (opponent == null) {
            this.outcome = GameOutcome.BYE;
            return;
        }

        if (wins > losses)
            this.outcome = GameOutcome.WON;
        else if (wins < losses)
            this.outcome = GameOutcome.LOST;
        else
            this.outcome = GameOutcome.TIED;
    }

    public GameOutcome GetOutcome () {
        return this.GetOutcome();
    }

    public int GetWins () {
        return this.wins;
    }
    public int GetLosses () {
        return this.losses;
    }
    public int GetTies () {
        return this.ties;
    }

    public Player GetOpponent () {
        return this.opponent;
    }
}
