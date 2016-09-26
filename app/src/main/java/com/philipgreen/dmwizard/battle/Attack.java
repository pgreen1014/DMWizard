package com.philipgreen.dmwizard.battle;

import com.philipgreen.dmwizard.PlayerCharacter;
import com.philipgreen.dmwizard.battle.damageRolls.DamageRollBehavior;
import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

/**
 * Created by pgreen on 8/26/16.
 *
 * The attack class represents an attack made against another player and should only
 * be created using AttackBuilder.
 */

public class Attack {
    private BaseWeapon mAttackingWeapon;
    private PlayerCharacter mDefender;
    private PlayerCharacter mAttacker;
    private BaseStats mAttackModifierStat;
    private int mPlayerDistance;
    private boolean mTwoHandedAttack;
    private boolean mOffHandWeaponAttack;
    private AttackBuilder.AttackType mAttackType;
    private DamageRollBehavior mDamageRollBehavior;
    private boolean mAdvantage;
    private boolean mDisadvantage;

    Attack(AttackBuilder attackBuild) {
        mAttackingWeapon = attackBuild.getAttackingWeapon();
        mDefender = attackBuild.getDefender();
        mAttacker = attackBuild.getAttacker();
        mAttackModifierStat = attackBuild.getAttackModifierStat();
        mPlayerDistance = attackBuild.getPlayerDistance();
        mTwoHandedAttack = attackBuild.isTwoHandedAttack();
        mOffHandWeaponAttack = attackBuild.isOffHandWeaponAttack();
        mAttackType = attackBuild.getAttackType();
        mDamageRollBehavior = attackBuild.getDamageRollBehavior();
        mAdvantage = attackBuild.isAdvantage();
        mDisadvantage = attackBuild.isDisadvantage();
    }

    BaseWeapon getAttackingWeapon() {
        return mAttackingWeapon;
    }

    PlayerCharacter getDefender() {
        return mDefender;
    }

    PlayerCharacter getAttacker() {
        return mAttacker;
    }

    BaseStats getAttackModifierStat() {
        return mAttackModifierStat;
    }

    public int getPlayerDistance() {
        return mPlayerDistance;
    }

    public boolean isTwoHandedAttack() {
        return mTwoHandedAttack;
    }

    public boolean isOffHandWeaponAttack() {
        return mOffHandWeaponAttack;
    }

    AttackBuilder.AttackType getAttackType() {
        return mAttackType;
    }

    boolean isAdvantage() {
        return mAdvantage;
    }

    boolean isDisadvantage() {
        return mDisadvantage;
    }

    public String toString() {
        return "Attacking weapon: " + mAttackingWeapon.toString() + "\n"
                + " Attack Modifier: " + mAttackModifierStat.toString() + "\n"
                + " Player Distance: " + Integer.toString(mPlayerDistance) + "\n"
                + " Is two handed attack: " + mTwoHandedAttack + "\n"
                + " Is off hand weapon: " + mOffHandWeaponAttack + "\n"
                + " Attack Type: " + mAttackType.toString() + "\n"
                + " Attacking Player: " + "\n" + mAttacker.toString() + "\n\n"
                + " Defending Player: " + "\n" + mDefender.toString();
    }

    int rollDamage() {
        return mDamageRollBehavior.rollDamage();
    }
}
