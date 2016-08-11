package com.philipgreen.dmwizard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.philipgreen.dmwizard.data.Skills;
import com.philipgreen.dmwizard.dice.Dice;
import com.philipgreen.dmwizard.playerClasses.Barbarian;
import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;

public class BattleSimulator extends AppCompatActivity {
    private static final String TAG = "BattleSimulator";
    Button mBtnCreateCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_simulator);

        mBtnCreateCharacter = (Button) findViewById(R.id.character_create_button);

        mBtnCreateCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerCharacter c = new PlayerCharacter(12, 14, 15, 12, 18, 17);

                Log.i(TAG, Integer.toString(c.getStrenthModifier()));
                Log.i(TAG, Integer.toString(c.getDexterityModifier()));
                Log.i(TAG, Integer.toString(c.getConstitutionModifier()));
                Log.i(TAG, Integer.toString(c.getIntelligenceModifier()));
                Log.i(TAG, Integer.toString(c.getWisdomModifier()));
                Log.i(TAG, Integer.toString(c.getCharismaModifier()));

                Log.i(TAG, "-----------------------------------------");

                PlayerCharacter b = new PlayerCharacter(18, 13, 16, 17, 10, 8);
                Log.i(TAG, Integer.toString(b.getStrenthModifier()));
                Log.i(TAG, Integer.toString(b.getDexterityModifier()));
                Log.i(TAG, Integer.toString(b.getConstitutionModifier()));
                Log.i(TAG, Integer.toString(b.getIntelligenceModifier()));
                Log.i(TAG, Integer.toString(b.getWisdomModifier()));
                Log.i(TAG, Integer.toString(b.getCharismaModifier()));
            }
        });
    }
}
