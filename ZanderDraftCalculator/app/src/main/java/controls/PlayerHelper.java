package controls;

import java.util.ArrayList;

import objects.GameOutcome;
import objects.GameResults;
import objects.Player;

public class PlayerHelper {
    public static ArrayList<String> getNameList(ArrayList<Player> players) {
        ArrayList<String> names = new ArrayList<String>();

        for (Player p : players)
            names.add(p.GetName());

        return names;
    }

    public static ArrayList<Integer> getWinsList(ArrayList<Player> players) {
        ArrayList<Integer> wins = new ArrayList<Integer>();

        for (Player p : players)
            wins.add(GameCalculations.MatchPoints(p));

        return wins;
    }

    public static ArrayList<String> getOMWList(ArrayList<Player> players) {
        ArrayList<String> omw = new ArrayList<String>();

        for (Player p : players)
            omw.add(String.format("%.2f", GameCalculations.OpponentsMatchWinPercentage(p)));

        return omw;
    }

    public static ArrayList<Integer> getRanks(int size) {
        ArrayList<Integer> ranks = new ArrayList<Integer>();

        for (int i = 1; i <= size; i++)
            ranks.add(i);

        return ranks;
    }

    public static void CreateResult(Player p1, Player p2, int p1Wins, int p2Wins, int ties) {
        GameOutcome p1Outcome = null;
        GameOutcome p2Outcome = null;

        if (p1Wins > p2Wins) {
            p1Outcome = GameOutcome.WON;
            p2Outcome = GameOutcome.LOST;
        } else if (p1Wins < p2Wins) {
            p1Outcome = GameOutcome.LOST;
            p2Outcome = GameOutcome.WON;
        } else {
            p1Outcome = GameOutcome.TIED;
            p2Outcome = GameOutcome.TIED;
        }

        p1.matchResults(new GameResults(p1Wins, p2Wins, ties, p1Outcome), p2);
        p2.matchResults(new GameResults(p2Wins, p1Wins, ties, p2Outcome), p1);
    }
}
