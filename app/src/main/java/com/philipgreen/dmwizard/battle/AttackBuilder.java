package com.philipgreen.dmwizard.battle;

import com.philipgreen.dmwizard.PlayerCharacter;
import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Versatile;

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

    // Checks to make sure built attack follows DnD rules then returns the Attack
    public Attack build() throws NullPointerException, IllegalArgumentException {
        validateNecessaryFieldsAreSet();

        if (mAttackType == AttackType.MELEE) {
           validateMeleeAttack();
        } else if (mAttackType == AttackType.RANGED) {
            validateRangeAttack();
        } else if (mAttackType == AttackType.THROWN) {
            validateThrownAttack();
        } else {
            throw new NullPointerException("Attack type not set");
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

    private void validateNecessaryFieldsAreSet() throws NullPointerException {
        if (mAttackingWeapon == null) {
            throw new NullPointerException("Must set attacking weapon");
        }
        if (mPlayerBeingAttacked == null) {
            throw new NullPointerException("Must set player being attacked");
        }
        if (mPlayerMakingAttack == null) {
            throw new NullPointerException("Must set player making attack");
        }
    }

    private void validateMeleeAttack() throws IllegalArgumentException{
        // Cannot use Ranged weapon as melee
        if (mAttackingWeapon.hasWeaponProperty(WeaponProperties.RANGE)) {
            throw new IllegalArgumentException("Cannot make melee attack with ranged weapon");
        }

        // if using Dexterity and is not a Finesse Weapon (Modifier can only be set to Dex or Str)
        if (mAttackModifierStat != BaseStats.STRENGTH && !mAttackingWeapon.hasWeaponProperty(WeaponProperties.FINESSE)) {
            throw new IllegalArgumentException("Cannot use Dexterity modifier with weapon: " + mAttackingWeapon.toString());
        }

        // If using weapon two handed but cannot be used two handed
        if (isTwoHandedAttack() && !canUseTwoHanded()) {
            throw new IllegalArgumentException("Cannot use Two Handed attack with weapon " + mAttackingWeapon.toString());
        }

        // If using two handed weapon with offhand
        if (mAttackingWeapon.hasWeaponProperty(WeaponProperties.TWO_HANDED) && isOffHandWeaponAttack()) {
            throw new IllegalArgumentException( mAttackingWeapon.toString() + " weapon must be used with two hands");
        }
    }

    private void validateRangeAttack() throws IllegalArgumentException {
        // Make sure attacking weapon has ranged property
        if (!mAttackingWeapon.hasWeaponProperty(WeaponProperties.RANGE)) {
            throw new IllegalArgumentException("Cannot make ranged attack with melee weapon");
        }

        // If using strength modifier and attacking weapon does not have Finesse property
        if (mAttackModifierStat != BaseStats.DEXTERITY && !mAttackingWeapon.hasWeaponProperty(WeaponProperties.FINESSE)) {
            throw new IllegalArgumentException("Cannot use Strength modifier with weapon " + mAttackingWeapon.toString());
        }
    }

    private void validateThrownAttack() throws IllegalArgumentException {
        if (!mAttackingWeapon.hasWeaponProperty(WeaponProperties.THROWN)) {
            throw new IllegalArgumentException("Cannot make thrown weapon attack with: " + mAttackingWeapon.toString());
        }
    }

    private boolean canUseTwoHanded() {
        return mAttackingWeapon.hasWeaponProperty(WeaponProperties.TWO_HANDED)
                || mAttackingWeapon.hasWeaponProperty(WeaponProperties.VERSATILE);
    }
}
