package com.example.zanderdraftcalculator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;

import controls.GameCalculations;
import objects.GameOutcome;
import objects.GameResults;
import objects.Player;


public class Drafting {

    public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        ArrayList<Player> players = new ArrayList<Player>();

        players.add(new Player("Zander"));
        players.add(new Player("Chico"));
        players.add(new Player("Sean"));
        players.add(new Player("Kendra"));
        players.add(new Player("Branden"));
        players.add(new Player("Dave"));
        players.add(new Player("Taylor"));
        players.add(new Player("Jonathan"));

        CreateResult(players.get(0), players.get(5), 2, 0, 0);
        CreateResult(players.get(2), players.get(4), 2, 0, 0);
        CreateResult(players.get(1), players.get(7), 0, 2, 0);
        CreateResult(players.get(6), players.get(3), 2, 1, 0);

        CreateResult(players.get(0), players.get(2), 0, 2, 0);
        CreateResult(players.get(7), players.get(6), 1, 2, 0);
        CreateResult(players.get(1), players.get(3), 0, 2, 0);
        CreateResult(players.get(4), players.get(5), 2, 1, 0);

        CreateResult(players.get(2), players.get(6), 2, 0, 0);
        CreateResult(players.get(0), players.get(7), 1, 2, 0);
        CreateResult(players.get(4), players.get(3), 2, 1, 0);
        CreateResult(players.get(1), players.get(5), 2, 0, 0);

        System.out.println("Player\t\tMatch Wins\t\tOMW\t\tGWP\t\tOGWP");

        Collections.sort(players, GameCalculations.PlayerRanksComparator);

        for (Player p : players) {
            System.out.println(p.GetName() + "\t\t" +
                    GameCalculations.MatchWinPercentage(p) + "\t" +
                    GameCalculations.OpponentsMatchWinPercentage(p) + "\t" +
                    GameCalculations.GameWinPercentage(p) + "\t" +
                    GameCalculations.OpponentsGameWinPercentage(p));
        }
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
