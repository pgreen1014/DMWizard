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
                Skills skills[] = {Skills.ANIMAL_HANDLING, Skills.ATHLETICS};
                BasePlayerClass barbarian = new Barbarian(1, skills);
                BaseRaceClass dwarf = new Dwarf();
                PlayerCharacter character = new PlayerCharacter(barbarian, dwarf, 18, 16, 18, 13, 14, 17);

                PlayerCharacter character2 = new PlayerCharacter(barbarian, dwarf, 18, 16, 18, 13, 14, 17);

                BaseWeapon dagger = new Dagger();

                AttackBuilder attackBuilder = new AttackBuilder();
                Attack attack = attackBuilder
                        .setMeleeAttack()
                        .setAttackingWeapon(dagger)
                        .setPlayerMakingAttack(character)
                        .setAttackTarget(character2)
                        .setAttackModifierStat(BaseStats.DEXTERITY)
                        .build();

                BattleManager bm = new BattleManager();
                bm.executeAttack(attack);
            }
        });
    }
}
