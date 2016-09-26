package com.philipgreen.dmwizard.battle;

import com.philipgreen.dmwizard.PlayerCharacter;
import com.philipgreen.dmwizard.battle.damageRolls.DamageRollBehavior;
import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.utils.SafeWeaponCaster;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;
import com.philipgreen.dmwizard.weapons.abstractWeapons.RangedWeapon;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Versatile;

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
        mAttack = attack;
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
        int damage = 0;

        if (mAttackType == AttackBuilder.AttackType.MELEE) {
            damage += executeMeleeAttack();
        } else if (mAttackType == AttackBuilder.AttackType.RANGED) {
            damage += executeRangedAttack();
        } else if (mAttackType == AttackBuilder.AttackType.THROWN) {
            damage += executeThrowAttack();
        }

        mDefender.takeDamage(damage);
        
        clearAttack();
    }

    private int executeMeleeAttack() {
        // Melee attack rolls should use strength modifier
        // roll for attack to hit
        int damage = 0;

        if (rollAttack()) {
            damage = mAttack.rollDamage();
        }

        return damage;
    }

    private int executeRangedAttack() {
        // Use ammunition
        RangedWeapon weapon = SafeWeaponCaster.castToRangedWeapon(mAttackingWeapon);
        weapon.useAmmunition();

        int damage = 0;

        if (rollAttack()) {
            damage += rollDamage();
        }
        return damage;
    }

    private int executeThrowAttack() {
        int damage = 0;

        if (rollAttack()) {
            damage += rollDamage();
        }
        return damage;
    }

    /**
     * Makes an attack roll to see if attack will hit the defender
     * @return true if attack is a hit; else return false;
     */
    private boolean rollAttack() {
        int attackRoll = mAttacker.attackRoll() + mAttacker.getAbilityModifier(mAttackModifierStat);
        return attackRoll >= mDefender.getArmorClass();
    }

    private int rollDamage() {
        return mAttack.rollDamage() + mAttacker.getAbilityModifier(mAttackModifierStat);
    }

}
