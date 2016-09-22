package com.philipgreen.dmwizard.battle;

import android.util.Log;

import com.philipgreen.dmwizard.PlayerCharacter;
import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;
import com.philipgreen.dmwizard.weapons.abstractWeapons.RangedWeapon;

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
    private boolean mAttackSuccessful;

    private BaseWeapon mAttackingWeapon;
    private PlayerCharacter mDefender;
    private PlayerCharacter mAttacker;
    private BaseStats mAttackModifierStat;
    private int mPlayerDistance;
    private boolean mTwoHandedAttack;
    private boolean mOffHandWeaponAttack;
    private AttackBuilder.AttackType mAttackType;

    public BattleManager(Attack attack) {
        setAttack(attack);
    }

    public void setAttack(Attack attack) {
        mAttackingWeapon = attack.getAttackingWeapon();
        mDefender = attack.getDefender();
        mAttacker = attack.getAttacker();
        mAttackModifierStat = attack.getAttackModifierStat();
        mPlayerDistance = attack.getPlayerDistance();
        mTwoHandedAttack = attack.isTwoHandedAttack();
        mOffHandWeaponAttack = attack.isOffHandWeaponAttack();
        mAttackType = attack.getAttackType();
    }

    private void clearAttack() {
        mAttackingWeapon = null;
        mDefender = null;
        mAttacker = null;
        mAttackModifierStat = null;
        mPlayerDistance = 0;
        mTwoHandedAttack = false;
        mOffHandWeaponAttack = false;
        mAttackType = null;
    }

    public void executeAttack(Attack attack) {
        mAttacker = attack.getAttacker();
        mDefender = attack.getDefender();
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
        clearAttack();
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
