package com.philipgreen.dmwizard.battle;

import android.util.Log;

import com.philipgreen.dmwizard.PlayerCharacter;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.weapons.abstractWeapons.MeleeWeapon;
import com.philipgreen.dmwizard.weapons.abstractWeapons.RangedWeapon;

/**
 * Created by pgreen on 8/25/16.
 *
 * This class is responsible for taking an attack object and executing the various kinds of attacks
 */
public class BattleManager {
    private static final String TAG = "BattleManager";
    private boolean mAttackSuccessful;
    private PlayerCharacter mAttacker;
    private PlayerCharacter mDefender;

    public BattleManager() {}

    public void executeAttack(Attack attack) {
        mAttacker = attack.getPlayerMakingAttack();
        mDefender = attack.getPlayerBeingAttacked();
        AttackBuilder.AttackType attackType = attack.getAttackType();

        int damage = 0;

        if (attackType == AttackBuilder.AttackType.MELEE) {
            damage += executeMeleeAttack(attack);
        } else if (attackType == AttackBuilder.AttackType.RANGED) {
            damage += executeRangedAttack(attack);
        } else if (attackType == AttackBuilder.AttackType.THROWN) {
            damage += executeThrowAttack(attack);
        }

        // Log results for testing TODO remove logs when tests are successful
        Log.i(TAG, "Defender has " + Integer.toString(mDefender.getHitPoints()) + " hit points");

        if (mAttackSuccessful) {
            Log.i(TAG, "Attack hits for: " + Integer.toString(damage) + " damage");
        } else {
            Log.i(TAG, "Attack misses");
        }

        mDefender.takeDamage(damage);

        Log.i(TAG, "Defender now has " + Integer.toString(mDefender.getHitPoints()) + " hit points, ouch!");
    }

    private int executeMeleeAttack(Attack attack) {
        // Melee attack rolls should use strength modifier
        rollAttack(mAttacker.getStrengthModifier());
        int damage = 0;

        if (mAttackSuccessful) {
            // if weapon is versatile and is being used to make two-handed attack
            if (attack.isTwoHandedAttack() && attack.getAttackingWeapon().hasWeaponProperty(WeaponProperties.VERSATILE)) {
                // TODO rewrite for new weapon interface implementation
                /*
                MeleeWeapon versatileWeapon = (MeleeWeapon) attack.getAttackingWeapon();
                damage += versatileWeapon.rollTwoHandedDamage() + mAttacker.getAbilityModifier(attack.getAttackModifierStat());
                */
            } else {
                damage += rollDamage(attack);
            }
        }

        return damage;
    }

    private int executeRangedAttack(Attack attack) {
        // Use ammunition
        RangedWeapon weapon = (RangedWeapon) attack.getAttackingWeapon();
        weapon.useAmmunition();
        // Ranged attack rolls should use dexterity modifier
        rollAttack(mAttacker.getDexterityModifier());
        int damage = 0;

        if (mAttackSuccessful) {
            damage += rollDamage(attack);
        }
        return damage;
    }

    private int executeThrowAttack(Attack attack) {
        int damage = 0;
        // if attacking weapon is ranged
        if (attack.getAttackingWeapon().hasWeaponProperty(WeaponProperties.RANGE)) {
            rollAttack(mAttacker.getDexterityModifier());
            if (mAttackSuccessful) {
                damage += rollDamage(attack);
            }
        // Else attacking weapon is melee
        } else {
            rollAttack(mAttacker.getStrengthModifier());
            if (mAttackSuccessful) {
                damage += rollDamage(attack);
            }
        }
        return damage;
    }

    private void rollAttack(int attackModifier) {
        int attackRoll = mAttacker.attackRoll() + attackModifier;
        mAttackSuccessful = attackRoll >= mDefender.getArmorClass();
    }

    private int rollDamage(Attack attack) {
        return attack.getAttackingWeapon().damageRoll() + mAttacker.getAbilityModifier(attack.getAttackModifierStat());
    }

}
