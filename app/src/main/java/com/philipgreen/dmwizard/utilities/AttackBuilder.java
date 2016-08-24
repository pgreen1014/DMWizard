package com.philipgreen.dmwizard.utilities;

import android.util.Log;

import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

/**
 * Created by pgreen on 8/23/16.
 */
public class AttackBuilder {
    private static final String TAG = "AttackBuilder";

    private BaseWeapon mMainHandAttackingWeapon;
    private BaseWeapon mOffHandAttackingWeapon;
    private BasePlayerClass mPlayerBeingAttacked;
    private BasePlayerClass mPlayerMakingAttack;
    private BaseStats mAttackModifier;
    private int mPlayerDistance;

    private boolean mMeleeAttack = false;
    private boolean mRangedAttack = false;
    private boolean mThrownAttack = false;

    public AttackBuilder() {

    }

    public void setMainHandAttackingWeapon(BaseWeapon weapon) {
        mMainHandAttackingWeapon = weapon;
    }

    public void setOffHandAttackingWeapon(BaseWeapon weapon) {
        mOffHandAttackingWeapon = weapon;
    }

    public void setAttackTarget(BasePlayerClass playerBeingAttacked) {
        mPlayerBeingAttacked = playerBeingAttacked;
    }

    public void setPlayerMakingAttack(BasePlayerClass playerMakingAttack) {
        mPlayerMakingAttack = playerMakingAttack;
    }

    public void setAttackModifier(BaseStats attackModifier) {
        mAttackModifier = attackModifier;
    }

    public void setPlayerDistance(int playerDistance) {
        mPlayerDistance = playerDistance;
    }

    public void setMeleeAttack() {
        if (weaponContainsProperty(mMainHandAttackingWeapon, WeaponProperties.RANGE)) {
            Log.e(TAG, "Cannot make a melee attack with ranged weapon");
            return;
        }

        mMeleeAttack = true;
    }

    public void setRangedAttack() {
        if (!weaponContainsProperty(mMainHandAttackingWeapon, WeaponProperties.RANGE)) {
            Log.e(TAG, "Cannot make a ranged attack with melee weapon");
            return;
        }

        mRangedAttack = true;
    }

    public void setThrownAttack() {
        if (!weaponContainsProperty(mMainHandAttackingWeapon, WeaponProperties.THROWN)) {
            Log.e(TAG, "Weapon does not have the Thrown property");
            return;
        }

        mThrownAttack = true;
    }


    private boolean weaponContainsProperty(BaseWeapon weapon, WeaponProperties property) {
        return weapon.getWeaponProperties().contains(property);
    }
}
