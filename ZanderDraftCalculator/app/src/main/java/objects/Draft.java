package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import controls.GameCalculations;

/**
 * Created by Zander on 7/6/2014.
 */
public class Draft implements Serializable {
    private ArrayList<Player> players;
    private ArrayList<Player> leftPlayers;
    private ArrayList<Player> rightPlayers;
    private int round;

    public Draft(ArrayList<Player> players) {
        this.players = players;
        this.round = 0;
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public ArrayList<Player> getLeftPlayers() {
        return this.leftPlayers;
    }

    public ArrayList<Player> getRightPlayers() {
        return this.rightPlayers;
    }

    public void nextRound() {
        this.round++;

        leftPlayers = new ArrayList<Player>();
        rightPlayers = new ArrayList<Player>();

        if (round == 1) {
            Collections.shuffle(players, new Random(System.nanoTime()));
            Iterator<Player> playersIterator = this.players.iterator();

            while (playersIterator.hasNext()) {
                leftPlayers.add(playersIterator.next());
                rightPlayers.add(playersIterator.next());
            }
        } else {
            ArrayList<Player> currPlayers = new ArrayList<Player>();
            ArrayList<ArrayList<Player>> newList = new ArrayList<ArrayList<Player>>();
            int currentMatchPoint = 1111;
            int rankSet = -1;

            Collections.sort(players, GameCalculations.PlayerMatchComparator);

            for (Player p : players) {
                int temp = GameCalculations.MatchPoints(p);
                if (temp < currentMatchPoint) {
                    rankSet++;
                    newList.add(new ArrayList<Player>());
                    currentMatchPoint = temp;
                }

                newList.get(rankSet).add(p);
            }

            Iterator<ArrayList<Player>> listIterator = newList.iterator();
            Player oddPlayer = null;
            while (listIterator.hasNext()) {
                boolean hasPlayedOpponent = true;
                ArrayList<Player> ranked = listIterator.next();

                if (oddPlayer != null) {
                    ranked.add(oddPlayer);
                    oddPlayer = null;
                }

                while (hasPlayedOpponent) {
                    hasPlayedOpponent = false;
                    Collections.shuffle(ranked, new Random(System.nanoTime()));

                    Player p1 = null;
                    Player p2 = null;

                    Iterator<Player> rankedIterator = ranked.iterator();
                    while (rankedIterator.hasNext() && rankedIterator.hasNext()) {
                        p1 = rankedIterator.next();
                        p2 = rankedIterator.next();

                        if (p1.GetOpponents().contains(p2)) {
                            hasPlayedOpponent = true;
                            break;
                        }
                    }
                }

                Iterator<Player> rankedIterator = ranked.iterator();
                while (rankedIterator.hasNext()) {
                    leftPlayers.add(rankedIterator.next());
                    rightPlayers.add(rankedIterator.next());
                }

                if (rankedIterator.hasNext() && listIterator.hasNext())
                    oddPlayer = rankedIterator.next();
            }
        }
    }
}
