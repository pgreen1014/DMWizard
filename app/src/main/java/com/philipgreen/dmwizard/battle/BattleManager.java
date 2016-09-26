package com.philipgreen.dmwizard.battle;

import com.philipgreen.dmwizard.PlayerCharacter;
import com.philipgreen.dmwizard.data.BaseStats;

/**
 * Created by pgreen on 8/25/16.
 *
 * This class is responsible for taking an attack object and executing the various kinds of attacks. Attack is initially set
 * in the constructor. In order to handle a new attack, a new BattleManager object can be made or simply call .setAttack(Attack arg)
 * to pass a new attack object. After an attack has been executed .clearAttack() is called so that already executed attack is not
 * accidentally executed again. A new attack will need to be passed in with .setAttack(Attack arg).
 */

public class BattleManager {
    private static final String TAG = "BattleManager";
    private Attack mAttack;

    private PlayerCharacter mDefender;
    private PlayerCharacter mAttacker;
    private BaseStats mAttackModifierStat;

    public BattleManager(Attack attack) {
        setAttack(attack);
    }

    public void setAttack(Attack attack) {
        mAttack = attack;
        mDefender = attack.getDefender();
        mAttacker = attack.getAttacker();
        mAttackModifierStat = attack.getAttackModifierStat();
    }

    private void clearAttack() {
        mDefender = null;
        mAttacker = null;
        mAttackModifierStat = null;
    }

    public void executeAttack() {
        int damage = 0;

        if (rollAttack()) {
            damage = mAttack.rollDamage();
        }

        mDefender.takeDamage(damage);
        
        clearAttack();
    }

    /**
     * Makes an attack roll to see if attack will hit the defender
     * @return true if attack is a hit; else return false;
     */
    private boolean rollAttack() {
        if (mAttack.isDisadvantage()) {
            return rollDisadvantage() >= mDefender.getArmorClass();
        } else if(mAttack.isAdvantage()) {
            return rollAdvantage() >= mDefender.getArmorClass();
        } else {
            int attackRoll = mAttacker.attackRoll() + mAttacker.getAbilityModifier(mAttackModifierStat);
            return attackRoll >= mDefender.getArmorClass();
        }
    }

    private int rollDisadvantage() {
        int attackRoll1 = mAttacker.attackRoll() + mAttacker.getAbilityModifier(mAttackModifierStat);
        int attackRoll2 = mAttacker.attackRoll() + mAttacker.getAbilityModifier(mAttackModifierStat);

        if (attackRoll1 < attackRoll2) {
            return attackRoll1;
        } else {
            return attackRoll2;
        }
    }

    private int rollAdvantage() {
        int attackRoll1 = mAttacker.attackRoll() + mAttacker.getAbilityModifier(mAttackModifierStat);
        int attackRoll2 = mAttacker.attackRoll() + mAttacker.getAbilityModifier(mAttackModifierStat);

        if (attackRoll1 > attackRoll2) {
            return attackRoll1;
        } else {
            return attackRoll2;
        }
    }

    private int rollDamage() {
        return mAttack.rollDamage() + mAttacker.getAbilityModifier(mAttackModifierStat);
    }

}
