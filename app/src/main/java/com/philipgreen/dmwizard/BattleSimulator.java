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
                Skills[] skillProfs = {Skills.ATHLETICS, Skills.SURVIVAL};
                BasePlayerClass character = new Barbarian(17, 14, 12, 10, 12, 12, 1, skillProfs);
                Log.i(TAG, "character: " + character.toString());
                Log.i(TAG, "saving throw " + Integer.toString(character.getCharismaSavingThrow()));

                for (int i=0; i < 100; i++) {
                    int roll = character.rollChaSavingThrow();
                    Log.i(TAG, Integer.toString(roll));
                }
            }
        });
    }
}
