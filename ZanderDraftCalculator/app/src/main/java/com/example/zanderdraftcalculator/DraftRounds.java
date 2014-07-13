package com.example.zanderdraftcalculator;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import controls.PlayerHelper;
import objects.Draft;

public class DraftRounds extends FragmentActivity implements AdapterView.OnItemClickListener {
    private objects.Draft draft;
    private ListView playerView;
    private ListView pointsView;
    private ListView omwView;
    private ListView rankings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft_rounds);

        Bundle lastBundle = this.getIntent().getExtras();
        if (lastBundle != null)
            draft = (Draft) lastBundle.getSerializable("GameData");

        CreateLayout();
    }

    public void CreateLayout() {
        ListView leftPlayer = (ListView) findViewById(R.id.leftPlayer);
        ListView rightPlayer = (ListView) findViewById(R.id.rightPlayer);

        final ArrayList<String> leftPlayerList = PlayerHelper.getNameList(draft.getLeftPlayers());
        final ArrayList<String> rightPlayerList = PlayerHelper.getNameList(draft.getRightPlayers());

        final ArrayAdapter<String> leftPlayerAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                leftPlayerList);
        final ArrayAdapter<String> rightPlayerAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                rightPlayerList);


        leftPlayer.setAdapter(leftPlayerAdapter);
        rightPlayer.setAdapter(rightPlayerAdapter);

        leftPlayer.setOnItemClickListener(this);
    }

    public void NextRound(View view) {
        draft.nextRound();
        CreateLayout();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.draft_rounds, menu);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(this, CreateGameResults.class);

        bundle.putSerializable("GameData", draft);
        bundle.putInt("position", position);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
