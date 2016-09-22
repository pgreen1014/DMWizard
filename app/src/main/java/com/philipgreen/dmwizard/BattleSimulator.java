package com.philipgreen.dmwizard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.philipgreen.dmwizard.data.WeaponProperties;
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

                BaseWeapon dagger = new Dagger();
                Log.i(TAG, "Thrown: " + dagger.hasWeaponProperty(WeaponProperties.THROWN));
                Log.i(TAG, "Versatile: " + dagger.hasWeaponProperty(WeaponProperties.VERSATILE));

            }
        });
    }
}
