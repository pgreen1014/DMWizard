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

import java.util.ArrayList;

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
                Skills[] skills = new Skills[] {Skills.ATHLETICS, Skills.INTIMIDATION};
                BasePlayerClass barb = new Barbarian(skills);
                PlayerCharacter character = new PlayerCharacter(barb, 17, 15, 15, 9, 12, 10);

                ArrayList<Skills> proficientSkills = character.getProficientSkills();

                for(Skills skill: proficientSkills) {
                    Log.i(TAG, skill.toString());
                }
            }
        });
    }
}
