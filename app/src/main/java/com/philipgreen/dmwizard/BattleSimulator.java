package com.philipgreen.dmwizard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.Skills;
import com.philipgreen.dmwizard.playerClasses.Barbarian;
import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;
import com.philipgreen.dmwizard.races.BaseRaceClass;
import com.philipgreen.dmwizard.races.Dwarf;
import com.philipgreen.dmwizard.weapons.Dagger;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

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
                Skills[] playerSkills = new Skills[] {Skills.ANIMAL_HANDLING, Skills.SURVIVAL};
                BasePlayerClass playerClass = new Barbarian(1, playerSkills);
                BaseRaceClass playerRace = new Dwarf();

                PlayerCharacter player = new PlayerCharacter(playerClass, playerRace, 17, 14, 14, 10, 13, 9);
                Log.i(TAG, player.toString());

                BaseWeapon dagger = new Dagger();
                int damageRoll = player.throwWeaponAttack(dagger, BaseStats.DEXTERITY);
                Log.i("BattleSimulator", "dagger damage roll: " + Integer.toString(damageRoll));


            }
        });
    }
}
