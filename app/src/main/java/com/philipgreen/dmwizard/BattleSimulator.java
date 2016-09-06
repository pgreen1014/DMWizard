package com.philipgreen.dmwizard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.philipgreen.dmwizard.battle.Attack;
import com.philipgreen.dmwizard.battle.AttackBuilder;
import com.philipgreen.dmwizard.battle.BattleManager;
import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.Skills;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.playerClasses.Barbarian;
import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;
import com.philipgreen.dmwizard.races.BaseRaceClass;
import com.philipgreen.dmwizard.races.Dwarf;
import com.philipgreen.dmwizard.weapons.Dagger;
import com.philipgreen.dmwizard.weapons.ShortBow;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Throwable;

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

                BaseWeapon dagger = new Dagger();

                int minRange = dagger.getMinRange();
                int maxRange = dagger.getMaxRange();
                int minThrow = dagger.getMinThrownRage();
                int maxThrow = dagger.getMaxThrownRange();

                Log.i(TAG, "minRange: " + Integer.toString(minRange));
                Log.i(TAG, "maxRange: " + Integer.toString(maxRange));
                Log.i(TAG, "minThrowRange: " + Integer.toString(minThrow));
                Log.i(TAG, "maxThrowRange: " + Integer.toString(maxThrow));

            }
        });
    }
}
