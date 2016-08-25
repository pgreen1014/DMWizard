package com.philipgreen.dmwizard.utilities;

import android.util.Log;

import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;
import com.philipgreen.dmwizard.weapons.abstractWeapons.ThrownWeapon;

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
    private boolean mIsTwoHandedAttack = false;

    private boolean mMeleeAttack = false;
    private boolean mRangedAttack = false;
    private boolean mThrownAttack = false;

    private boolean mAttackBuildCompleted = false;

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

    // Only necessary for weapons with WeaponProperties.Finesse
    public void setAttackModifier(BaseStats attackModifier) throws IllegalArgumentException {
        if (attackModifier == BaseStats.STRENGTH || attackModifier == BaseStats.DEXTERITY) {
            mAttackModifier = attackModifier;
        } else {
            throw new IllegalArgumentException("Attack Modifier can only be of the type BaseStats.Strength or BaseStats.Dexterity");
        }
    }

    public void setPlayerDistance(int playerDistance) {
        mPlayerDistance = playerDistance;
    }

    public void setTwoHandedAttack() {
        mIsTwoHandedAttack = true;
    }

    public void setMeleeAttack() {
        mMeleeAttack = true;
        // Make sure other attack fields are set to false
        mRangedAttack = false;
        mThrownAttack = false;
    }

    public void setRangedAttack() {
        mRangedAttack = true;
        // Make sure other attack fields are set to false
        mMeleeAttack = false;
        mThrownAttack = false;
    }

    public void setThrownAttack() {
        mThrownAttack = true;
        // Make sure other attack fields are set to false
        mMeleeAttack = false;
        mRangedAttack = false;
    }

    // Checks to make sure built attack follows DnD rules
    public void build() throws NullPointerException, IllegalArgumentException {
        if (mMainHandAttackingWeapon == null) {
            throw new NullPointerException("Must set attacking weapon");
        }
        if (mPlayerBeingAttacked == null) {
            throw new NullPointerException("Must set player being attacked");
        }
        if (mPlayerMakingAttack == null) {
            throw new NullPointerException("Must set player making attack");
        }

        if (mMeleeAttack) {
            if (weaponContainsProperty(mMainHandAttackingWeapon, WeaponProperties.RANGE)) {
                throw new IllegalArgumentException("Cannot make melee attack with ranged weapon");
            }
            if (mAttackModifier != BaseStats.STRENGTH && !weaponContainsProperty(mMainHandAttackingWeapon, WeaponProperties.FINESSE)) {
                throw new IllegalArgumentException("Cannot use Dexterity modifier with weapon: " + mMainHandAttackingWeapon.toString());
            }
            if (mOffHandAttackingWeapon != null && mIsTwoHandedAttack && weaponContainsProperty(mMainHandAttackingWeapon, WeaponProperties.VERSATILE)) {
                throw new IllegalArgumentException("Cannot make off hand weapon attack and two handed weapon attack with the same attack");
            }
            if (mOffHandAttackingWeapon != null && weaponContainsProperty(mMainHandAttackingWeapon, WeaponProperties.TWO_HANDED)) {
                throw new IllegalArgumentException("Cannot make off hand weapon attack with two handed weapon");
            }
        }

        if (mRangedAttack) {
            if (!weaponContainsProperty(mMainHandAttackingWeapon, WeaponProperties.RANGE)) {
                throw new IllegalArgumentException("Cannot make ranged attack with melee weapon");
            }
            if (mAttackModifier != BaseStats.DEXTERITY && !weaponContainsProperty(mMainHandAttackingWeapon, WeaponProperties.FINESSE)) {
                throw new IllegalArgumentException("Cannot use Strength modifier with weapon " + mMainHandAttackingWeapon.toString());
            }
        }

        if (mThrownAttack && !weaponContainsProperty(mMainHandAttackingWeapon, WeaponProperties.THROWN)) {
            throw new IllegalArgumentException("Cannot make thrown weapon attack with: " + mMainHandAttackingWeapon.toString());
        }


        mAttackBuildCompleted = true;
    }

    // build() must be called before executeAttack can be run
    public void executeAttack() {
        if (!mAttackBuildCompleted) {
            return;
        }
        // TODO fill out method
    }


    private boolean weaponContainsProperty(BaseWeapon weapon, WeaponProperties property) {
        return weapon.getWeaponProperties().contains(property);
    }
}
