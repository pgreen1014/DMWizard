package com.philipgreen.dmwizard.battle;

import com.philipgreen.dmwizard.PlayerCharacter;
import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

/**
 * Created by pgreen on 8/23/16.
 */
public class AttackBuilder {
    private static final String TAG = "AttackBuilder";

    private BaseWeapon mAttackingWeapon;
    private PlayerCharacter mPlayerBeingAttacked;
    private PlayerCharacter mPlayerMakingAttack;
    private BaseStats mAttackModifierStat;
    private int mPlayerDistance;
    private boolean mIsTwoHandedAttack = false;
    private boolean mIsOffHandWeaponAttack = false;
    private AttackType mAttackType;

    protected enum AttackType {
        MELEE, RANGED, THROWN
    }

    private AttackBuilder() {

    }

    public void setAttackingWeapon(BaseWeapon weapon) {
        mAttackingWeapon = weapon;
    }

    public void setAttackTarget(PlayerCharacter playerBeingAttacked) {
        mPlayerBeingAttacked = playerBeingAttacked;
    }

    public void setPlayerMakingAttack(PlayerCharacter playerMakingAttack) {
        mPlayerMakingAttack = playerMakingAttack;
    }

    // Only necessary for weapons with WeaponProperties.Finesse
    public void setAttackModifierStat(BaseStats attackModifier) throws IllegalArgumentException {
        if (attackModifier == BaseStats.STRENGTH || attackModifier == BaseStats.DEXTERITY) {
            mAttackModifierStat = attackModifier;
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

    public void setOffHandWeaponAttack() {
        mIsOffHandWeaponAttack = true;
    }

    public void setMeleeAttack() {
        mAttackType = AttackType.MELEE;
    }

    public void setRangedAttack() {
        mAttackType = AttackType.RANGED;
    }

    public void setThrownAttack() {
        mAttackType = AttackType.RANGED;
    }

    // Checks to make sure built attack follows DnD rules
    public Attack build() throws NullPointerException, IllegalArgumentException {
        if (mAttackingWeapon == null) {
            throw new NullPointerException("Must set attacking weapon");
        }
        if (mPlayerBeingAttacked == null) {
            throw new NullPointerException("Must set player being attacked");
        }
        if (mPlayerMakingAttack == null) {
            throw new NullPointerException("Must set player making attack");
        }

        if (mAttackType == AttackType.MELEE) {
            if (mAttackingWeapon.containsWeaponProperty(WeaponProperties.RANGE)) {
                throw new IllegalArgumentException("Cannot make melee attack with ranged weapon");
            }
            if (mAttackModifierStat != BaseStats.STRENGTH && !mAttackingWeapon.containsWeaponProperty(WeaponProperties.FINESSE)) {
                throw new IllegalArgumentException("Cannot use Dexterity modifier with weapon: " + mAttackingWeapon.toString());
            }
            if (isTwoHandedAttack() &&
                    (!mAttackingWeapon.containsWeaponProperty(WeaponProperties.TWO_HANDED) ||
                            !mAttackingWeapon.containsWeaponProperty(WeaponProperties.VERSATILE))) {
                throw new IllegalArgumentException("Cannot use Two Handed attack with weapon " + mAttackingWeapon.toString());
            }
        }

        if (mAttackType == AttackType.RANGED) {
            if (!mAttackingWeapon.containsWeaponProperty(WeaponProperties.RANGE)) {
                throw new IllegalArgumentException("Cannot make ranged attack with melee weapon");
            }
            if (mAttackModifierStat != BaseStats.DEXTERITY && !mAttackingWeapon.containsWeaponProperty(WeaponProperties.FINESSE)) {
                throw new IllegalArgumentException("Cannot use Strength modifier with weapon " + mAttackingWeapon.toString());
            }
        }

        if (mAttackType == AttackType.THROWN && !mAttackingWeapon.containsWeaponProperty(WeaponProperties.THROWN)) {
            throw new IllegalArgumentException("Cannot make thrown weapon attack with: " + mAttackingWeapon.toString());
        }

        return new Attack(this);
    }

    ///////////////
    //  GETTERS  //
    ///////////////


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

    public AttackType getAttackType() {
        return mAttackType;
    }

    ///////////////////////////////
    // ATTACK VALIDATION METHODS //
    ///////////////////////////////
    //TODO clean up .build() by created modularized methods to check for valid build
}
