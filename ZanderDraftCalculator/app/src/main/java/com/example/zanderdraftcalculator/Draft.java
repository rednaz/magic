package com.example.zanderdraftcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import controls.GameCalculations;
import controls.PlayerHelper;
import objects.Player;

public class Draft extends Activity {
    private ListView playerView;
    private ListView pointsView;
    private ListView omwView;
    private ListView rankings;

    private Button draftButton;

    private ArrayList<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        players = new ArrayList<Player>();

        players.add(new Player("Zander"));
        players.add(new Player("Chico"));
        players.add(new Player("Sean"));
        players.add(new Player("Kendra"));
        players.add(new Player("Branden"));
        players.add(new Player("Dave"));
        players.add(new Player("Taylor"));
        players.add(new Player("Jonathan"));

        PlayerHelper.CreateResult(players.get(0), players.get(5), 2, 0, 0);
        PlayerHelper.CreateResult(players.get(2), players.get(4), 2, 0, 0);
        PlayerHelper.CreateResult(players.get(1), players.get(7), 0, 2, 0);
        PlayerHelper.CreateResult(players.get(6), players.get(3), 2, 1, 0);

        PlayerHelper.CreateResult(players.get(0), players.get(2), 0, 2, 0);
        PlayerHelper.CreateResult(players.get(7), players.get(6), 1, 2, 0);
        PlayerHelper.CreateResult(players.get(1), players.get(3), 0, 2, 0);
        PlayerHelper.CreateResult(players.get(4), players.get(5), 2, 1, 0);

        PlayerHelper.CreateResult(players.get(2), players.get(6), 2, 0, 0);
        PlayerHelper.CreateResult(players.get(0), players.get(7), 1, 2, 0);
        PlayerHelper.CreateResult(players.get(4), players.get(3), 2, 1, 0);
        PlayerHelper.CreateResult(players.get(1), players.get(5), 2, 0, 0);

        Collections.sort(players, GameCalculations.PlayerRanksComparator);
        Collections.shuffle(players, new Random(System.nanoTime()));

        CreateLayout();
    }

    public void CreateLayout() {
        final ArrayList<String> playerNamesList = PlayerHelper.getNameList(players);
        final ArrayList<Integer> playerWinsList = PlayerHelper.getWinsList(players);
        final ArrayList<String> playerOMWList = PlayerHelper.getOMWList(players);
        final ArrayList<Integer> playerRanks = PlayerHelper.getRanks(players.size());

        playerView = (ListView) findViewById(R.id.players);
        pointsView = (ListView) findViewById(R.id.points);
        omwView = (ListView) findViewById(R.id.omw);
        rankings = (ListView) findViewById(R.id.rank);

        final ArrayAdapter<Integer> playerRanksAdapter = new ArrayAdapter<Integer>(
                this,
                android.R.layout.simple_list_item_1,
                playerRanks);

        final ArrayAdapter<String> playerNamesAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                playerNamesList);
        final ArrayAdapter<Integer> playerPointsAdapter = new ArrayAdapter<Integer>(
                this,
                android.R.layout.simple_list_item_1,
                playerWinsList);
        final ArrayAdapter<String> playerOMWAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                playerOMWList);

        playerView.setAdapter(playerNamesAdapter);
        pointsView.setAdapter(playerPointsAdapter);
        omwView.setAdapter(playerOMWAdapter);
        rankings.setAdapter(playerRanksAdapter);
    }

    public void Start(View view) {
        Intent intent = new Intent(this, DraftStart.class);

        startActivity(intent);
    }
}
