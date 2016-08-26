package com.philipgreen.dmwizard.battle;

import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

/**
 * Created by pgreen on 8/26/16.
 *
 * Class to represent an attack. Is constructed by  using AttackBuilder
 */
public class Attack {
    private BaseWeapon mAttackingWeapon;
    private BasePlayerClass mPlayerBeingAttacked;
    private BasePlayerClass mPlayerMakingAttack;
    private BaseStats mAttackModifier;
    private int mPlayerDistance;
    private boolean mIsTwoHandedAttack;
    private boolean mIsOffHandWeaponAttack;
    private AttackBuilder.AttackType mAttackType;

    protected Attack(AttackBuilder attackBuild) {
        mAttackingWeapon = attackBuild.getAttackingWeapon();
        mPlayerBeingAttacked = attackBuild.getPlayerBeingAttacked();
        mPlayerMakingAttack = attackBuild.getPlayerMakingAttack();
        mAttackModifier = attackBuild.getAttackModifier();
        mPlayerDistance = attackBuild.getPlayerDistance();
        mIsTwoHandedAttack = attackBuild.isTwoHandedAttack();
        mIsOffHandWeaponAttack = attackBuild.isOffHandWeaponAttack();
        mAttackType = attackBuild.getAttackType();
    }

    public BaseWeapon getAttackingWeapon() {
        return mAttackingWeapon;
    }

    public BasePlayerClass getPlayerBeingAttacked() {
        return mPlayerBeingAttacked;
    }

    public BasePlayerClass getPlayerMakingAttack() {
        return mPlayerMakingAttack;
    }

    public BaseStats getAttackModifier() {
        return mAttackModifier;
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
}
