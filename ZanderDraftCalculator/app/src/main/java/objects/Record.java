package objects;

public class Record {
    private Player player1;
    private int p1Wins;

    private Player player2;
    private int p2Wins;

    private Player winner;
    private int ties;

    public Record(Player p1, int p1wins, Player p2, int p2wins, Player winnner, int ties) {
        this.player1 = p1;
        this.player2 = p2;

        this.p1Wins = p1wins;
        this.p2Wins = p2wins;

        this.winner = winner;
        this.ties = ties;
    }

    public Player GetP1() {
        return this.player1;
    }

    public int GetPoints() {
        return 0;
    }

    public Player GetP2() {
        return this.player2;
    }

    public int getP1Wins() {
        return this.p1Wins;
    }

    public int getP2Wins() {
        return this.p2Wins;
    }

    public Player GetWinner() {
        return this.winner;
    }

    public int GetTies() {
        return this.ties;
    }
}
