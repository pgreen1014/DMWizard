package com.philipgreen.dmwizard.battle;

import com.philipgreen.dmwizard.PlayerCharacter;
import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

/**
 * Created by pgreen on 8/26/16.
 *
 * Class to represent an attack. Is constructed by  using AttackBuilder
 */
public class Attack {
    private BaseWeapon mAttackingWeapon;
    private PlayerCharacter mPlayerBeingAttacked;
    private PlayerCharacter mPlayerMakingAttack;
    private BaseStats mAttackModifierStat;
    private int mPlayerDistance;
    private boolean mIsTwoHandedAttack;
    private boolean mIsOffHandWeaponAttack;
    private AttackBuilder.AttackType mAttackType;

    protected Attack(AttackBuilder attackBuild) {
        mAttackingWeapon = attackBuild.getAttackingWeapon();
        mPlayerBeingAttacked = attackBuild.getPlayerBeingAttacked();
        mPlayerMakingAttack = attackBuild.getPlayerMakingAttack();
        mAttackModifierStat = attackBuild.getAttackModifierStat();
        mPlayerDistance = attackBuild.getPlayerDistance();
        mIsTwoHandedAttack = attackBuild.isTwoHandedAttack();
        mIsOffHandWeaponAttack = attackBuild.isOffHandWeaponAttack();
        mAttackType = attackBuild.getAttackType();
    }

    public BaseWeapon getAttackingWeapon() {
        return mAttackingWeapon;
    }

    public PlayerCharacter getPlayerBeingAttacked() {
        return mPlayerBeingAttacked;
    }

    public PlayerCharacter getPlayerMakingAttack() {
        return mPlayerMakingAttack;
    }

    public BaseStats getAttackModifierStat() {
        return mAttackModifierStat;
    }

    public int getPlayerDistance() {
        return mPlayerDistance;
    }

    public boolean isTwoHandedAttack() {
        return mIsTwoHandedAttack;
    }

    public boolean isOffHandWeaponAttack() {
        return mIsOffHandWeaponAttack;
    }

    public AttackBuilder.AttackType getAttackType() {
        return mAttackType;
    }

    public String toString() {
        return "Attacking weapon: " + mAttackingWeapon.toString() + "\n"
                + " Attack Modifier: " + mAttackModifierStat.toString() + "\n"
                + " Player Distance: " + Integer.toString(mPlayerDistance) + "\n"
                + " Is two handed attack: " + mIsTwoHandedAttack + "\n"
                + " Is off hand weapon: " + mIsOffHandWeaponAttack + "\n"
                + " Attack Type: " + mAttackType.toString() + "\n"
                + " Attacking Player: " + "\n" + mPlayerMakingAttack.toString() + "\n\n"
                + " Defending Player: " + "\n" + mPlayerBeingAttacked.toString();
    }
}
