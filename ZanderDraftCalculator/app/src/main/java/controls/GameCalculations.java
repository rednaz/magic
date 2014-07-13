package controls;

import java.util.Comparator;

import objects.GameResults;
import objects.Player;

public class GameCalculations {
    public static Comparator<Player> PlayerMatchComparator = new Comparator<Player>() {
        public int compare(Player p1, Player p2) {
            return MatchPoints(p2) - MatchPoints(p1);
        }
    };
    public static Comparator<Player> PlayerRanksComparator = new Comparator<Player>() {
        public int compare(Player p1, Player p2) {
            int calc = 0;
            if ((calc = MatchPoints(p2) - MatchPoints(p1)) != 0)
                return calc;
            if ((calc = (int) ((OpponentsMatchWinPercentage(p2) - OpponentsMatchWinPercentage(p1)) * 100)) != 0)
                return calc;
            if ((calc = (int) ((GameWinPercentage(p2) - GameWinPercentage(p1)) * 100)) != 0)
                return calc;
            return (int) ((OpponentsGameWinPercentage(p2) - OpponentsGameWinPercentage(p1)) * 100);
        }
    };

    public static int MatchPoints(Player p) {
        int matchPoints = 0;

        for (GameResults result : p.GetCurrentResult()) {
            switch (result.GetOutcome()) {
                case WON:
                case BYE:
                    matchPoints += 3;
                    break;
                case TIED:
                    matchPoints += 1;
                    break;
                case LOST:
                default:
                    break;
            }
        }

        return matchPoints;
    }

    public static double MatchWinPercentage(Player p) {
        double percentage = (double) MatchPoints(p) / (double) (p.GetCurrentResult().size() * 3);

        return percentage >= (1.0 / 3.0) ? percentage : (1.0 / 3.0);
    }

    public static double OpponentsMatchWinPercentage(Player p) {
        double opponentsPercentage = 0;

        for (Player opponent : p.GetOpponents()) {
            opponentsPercentage += MatchWinPercentage(opponent);
        }

        return (double) opponentsPercentage / (double) p.GetOpponents().size();
    }

    public static int GamePoints(Player p) {
        int gamePoints = 0;

        for (GameResults result : p.GetCurrentResult()) {
            gamePoints += result.GamePoints();
        }

        return gamePoints;
    }

    public static int GamesPlayed(Player p) {
        int gamesPlayed = 0;

        for (GameResults result : p.GetCurrentResult()) {
            gamesPlayed += result.GamesPlayed();
        }

        return gamesPlayed;
    }

    public static double GameWinPercentage(Player p) {
        return (double) GamePoints(p) / (double) (GamesPlayed(p) * 3);
    }

    public static double OpponentsGameWinPercentage(Player p) {
        double opponentPoints = 0;

        for (Player opponent : p.GetOpponents()) {
            opponentPoints += GameWinPercentage(opponent);
        }

        return (double) opponentPoints / (double) p.GetOpponents().size();
    }
}
