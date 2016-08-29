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

    public AttackBuilder() {

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

    public AttackBuilder setAttackingWeapon(BaseWeapon weapon) {
        this.mAttackingWeapon = weapon;
        return this;
    }

    public AttackBuilder setAttackTarget(PlayerCharacter playerBeingAttacked) {
        this.mPlayerBeingAttacked = playerBeingAttacked;
        return this;
    }

    public AttackBuilder setPlayerMakingAttack(PlayerCharacter playerMakingAttack) {
        mPlayerMakingAttack = playerMakingAttack;
        return this;
    }

    // Only necessary for weapons with WeaponProperties.Finesse
    public AttackBuilder setAttackModifierStat(BaseStats attackModifier) throws IllegalArgumentException {
        if (attackModifier == BaseStats.STRENGTH || attackModifier == BaseStats.DEXTERITY) {
            this.mAttackModifierStat = attackModifier;
        } else {
            throw new IllegalArgumentException("Attack Modifier can only be of the type BaseStats.Strength or BaseStats.Dexterity");
        }
        return this;
    }

    public AttackBuilder setPlayerDistance(int playerDistance) {
        mPlayerDistance = playerDistance;
        return this;
    }

    public AttackBuilder setTwoHandedAttack() {
        mIsTwoHandedAttack = true;
        return this;
    }

    public AttackBuilder setOffHandWeaponAttack() {
        mIsOffHandWeaponAttack = true;
        return this;
    }

    public AttackBuilder setMeleeAttack() {
        mAttackType = AttackType.MELEE;
        return this;
    }

    public AttackBuilder setRangedAttack() {
        mAttackType = AttackType.RANGED;
        return this;
    }

    public AttackBuilder setThrownAttack() {
        mAttackType = AttackType.RANGED;
        return this;
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
            if (mAttackingWeapon.containsWeaponProperty(WeaponProperties.TWO_HANDED) && mIsOffHandWeaponAttack) {
                throw new IllegalArgumentException( mAttackingWeapon.toString() + " weapon must be used with two hands");
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
