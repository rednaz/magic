package com.example.zanderdraftcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import controls.PlayerHelper;
import objects.Draft;
import objects.Player;

public class CreateGameResults extends Activity {
    private objects.Draft draft;
    private Player p1;
    private Player p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game_results);

        TextView p1Name = (TextView) findViewById(R.id.playerOne);
        TextView p2Name = (TextView) findViewById(R.id.playerTwo);

        Bundle lastBundle = this.getIntent().getExtras();
        int position = lastBundle.getInt("position");

        draft = (Draft) lastBundle.getSerializable("GameData");
        p1 = draft.getLeftPlayers().get(position);
        p2 = draft.getRightPlayers().get(position);

        p1Name.setText(p1.GetName());
        p2Name.setText(p2.GetName());
    }

    public void GenerateResults(View view) {
        int p1Wins = 0;
        int p2Wins = 0;
        int ties = 0;
        String temp = null;
        temp = ((EditText) findViewById(R.id.p1Wins)).getText().toString().trim();
        if (temp.length() > 0)
            p1Wins = Integer.valueOf(temp);
        temp = ((EditText) findViewById(R.id.p2Wins)).getText().toString().trim();
        if (temp.length() > 0)
            p2Wins = Integer.valueOf(temp);
        temp = ((EditText) findViewById(R.id.totalTies)).getText().toString().trim();
        if (temp.length() > 0)
            ties = Integer.valueOf(temp);

        PlayerHelper.CreateResult(p1, p2, p1Wins, p2Wins, ties);

        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setClass(this, DraftRounds.class);

        bundle.putSerializable("GameData", draft);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_game_results, menu);
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
