package com.example.zanderdraftcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import controls.PlayerHelper;
import objects.Draft;
import objects.Player;

public class DraftStart extends Activity {
    private ListView playerView;
    private ListView pointsView;
    private ListView omwView;
    private ListView rankings;

    private Button draftButton;

    private objects.Draft draft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft_start);

        ArrayList<Player> players = new ArrayList<Player>();

        players.add(new Player("Zander"));
        players.add(new Player("Chico"));
        players.add(new Player("Sean"));
        players.add(new Player("Kendra"));
        players.add(new Player("Branden"));
        players.add(new Player("Dave"));
        players.add(new Player("Taylor"));
        players.add(new Player("Jonathan"));

        draft = new Draft(players);

        CreateLayout();
    }

    public void CreateLayout() {
        final ArrayList<String> playerNamesList = PlayerHelper.getNameList(draft.getPlayers());

        playerView = (ListView) findViewById(R.id.players);

        final ArrayAdapter<String> playerNamesAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                playerNamesList);

        playerView.setAdapter(playerNamesAdapter);
    }

    public void BeginDraft(View view) {
        Intent intent = new Intent(this, DraftRounds.class);
        Bundle bundle = new Bundle();

        draft.nextRound();

        bundle.putSerializable("GameData", draft);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.draft_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
