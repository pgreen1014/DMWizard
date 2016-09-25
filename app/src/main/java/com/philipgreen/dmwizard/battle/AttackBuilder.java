package com.philipgreen.dmwizard.battle;

import com.philipgreen.dmwizard.PlayerCharacter;
import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.utils.SafeWeaponCaster;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;
import com.philipgreen.dmwizard.weapons.abstractWeapons.MeleeWeapon;
import com.philipgreen.dmwizard.weapons.abstractWeapons.RangedWeapon;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Throwable;

/**
 * Created by pgreen on 8/23/16.
 *
 * This is a builder class used to create a single Attack object that can be passed into BattleManager.
 * Attacks can be created by chaining methods and finishing by calling .build() which checks for a valid build
 * and returns the Attack object.
 *
 * The following methods must be called for .build() to build an Attack successfully:
 * .setAttacker(PlayerCharacter arg)
 * .setDefender(PlayerCharacter arg)
 * .setAttackingWeapon(BaseWeapon arg)
 * and one of the following .setMeleeAttack(), . setRangedAttack(), or setThrownAttack();
 *
 * Any additional build methods must follow the rules of DnD, for example using a Great Axe as an off-hand weapon attack
 * will fail because it must be used with two hands.
 *
 *
 *
 * Example of using AttackBuilder to construct a thrown attack with a dagger.
 *
 * AttackBuilder attackBuilder = new AttackBuilder();
 * Attack attack = attackBuilder
 *          .setAttacker(attackingPlayerCharacter)
 *          .setDefender(defendingPlayerCharacter)
 *          .setAttackingWeapon(dagger)
 *          .setThrownAttack()
 *          .build()
 */

public class AttackBuilder {
    private static final String TAG = "AttackBuilder";

    private BaseWeapon mAttackingWeapon;
    private PlayerCharacter mDefender;
    private PlayerCharacter mAttacker;
    private BaseStats mAttackModifierStat;
    private int mPlayerDistance;
    private boolean mTwoHandedAttack = false;
    private boolean mOffHandWeaponAttack = false;
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
                + " Is two handed attack: " + mTwoHandedAttack + "\n"
                + " Is off hand weapon: " + mOffHandWeaponAttack + "\n"
                + " Attack Type: " + mAttackType.toString() + "\n"
                + " Attacking Player: " + "\n" + mAttacker.toString() + "\n\n"
                + " Defending Player: " + "\n" + mDefender.toString();
    }

    public AttackBuilder setAttackingWeapon(BaseWeapon weapon) {
        this.mAttackingWeapon = weapon;
        return this;
    }

    public AttackBuilder setDefender(PlayerCharacter defender) {
        this.mDefender = defender;
        return this;
    }

    public AttackBuilder setAttacker(PlayerCharacter attacker) {
        mAttacker = attacker;
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
        mTwoHandedAttack = true;
        return this;
    }

    public AttackBuilder setOffHandWeaponAttack() {
        mOffHandWeaponAttack = true;
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
        mAttackType = AttackType.THROWN;
        return this;
    }

    /**
     * Checks to make sure built attack follows DnD rules then returns the Attack
     */
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

    public PlayerCharacter getDefender() {
        return mDefender;
    }

    public PlayerCharacter getAttacker() {
        return mAttacker;
    }

    public BaseStats getAttackModifierStat() {
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

    public AttackType getAttackType() {
        return mAttackType;
    }

    ///////////////////////////////
    // ATTACK VALIDATION METHODS //
    ///////////////////////////////

    private void validateNecessaryFieldsAreSet() throws NullPointerException {
        if (mAttackingWeapon == null) {
            throw new NullPointerException("Must set attacking weapon");
        }
        if (mDefender == null) {
            throw new NullPointerException("Must set player being attacked");
        }
        if (mAttacker == null) {
            throw new NullPointerException("Must set player making attack");
        }
        if (mAttacker == null) {
            throw new NullPointerException("Must set AttackType: Melee, Ranged, or Thrown");
        }

        // Set attack modifier if not already set
        determineAttackModifierStat();
    }

    private void validateMeleeAttack() throws IllegalArgumentException{
        // Cannot use Ranged weapon as melee
        if (!(mAttackingWeapon instanceof MeleeWeapon)) {
            throw new IllegalArgumentException("weapon making melee attack is not instance of MeleeWeapon");
        }

        MeleeWeapon meleeWeapon = SafeWeaponCaster.castToMeleeWeapon(mAttackingWeapon);
        // Check that melee weapon is in range
        if (!isAttackInRange(meleeWeapon.getRange())) {
            throw new IllegalArgumentException("Melee Attack is not within range");
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

        RangedWeapon rangedWeapon = SafeWeaponCaster.castToRangedWeapon(mAttackingWeapon);
        if (!isAttackInRange(rangedWeapon.getMaxRange())) {
            throw new IllegalArgumentException("Ranged attack is not within max range of defender");
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

        Throwable throwableWeapon = SafeWeaponCaster.castToThrowable(mAttackingWeapon);
        if (!isAttackInRange(throwableWeapon.maxThrownRange())) {
            throw new IllegalArgumentException("weapon out of range");
        }
    }

    private boolean canUseTwoHanded() {
        return mAttackingWeapon.hasWeaponProperty(WeaponProperties.TWO_HANDED)
                || mAttackingWeapon.hasWeaponProperty(WeaponProperties.VERSATILE);
    }

    /**
     * Responsible for setting the correct attack modifier stat if not set. If the attack is of the type MELEE
     * then modifier will be set to Strength. If the attack is of the type RANGE then modifier will be set to Dexterity.
     * If the attack is of the type Thrown then modifier will be set to Strength if AttackingWeapon is MeleeWeapon
     * otherwise it will be set to Dexterity.
     */
    private void determineAttackModifierStat() {
        if (mAttackModifierStat != null) {
            return;
        }

        if (mAttackType == AttackType.MELEE) {
            this.mAttackModifierStat = BaseStats.STRENGTH;

        } else if (mAttackType == AttackType.RANGED) {
            this.mAttackModifierStat = BaseStats.DEXTERITY;

        } else {
            if (mAttackingWeapon instanceof MeleeWeapon) {
                this.mAttackModifierStat = BaseStats.STRENGTH;
            } else {
                this.mAttackModifierStat = BaseStats.DEXTERITY;
            }
        }

    }

    /**
     * Used to determine whether the defending player is within range of a weapon. If attack is beyond
     * the range then the build will fail. Because range is not currently set in weapons.abstractWeapons.BaseWeapon.java,
     * mAttackingWeapon must be cast to either MeleeWeapon, RangedWeapon or Throwable in order to get the appropriate
     * range. This kind of casting is dangerous and should only be done after checking that mAttackingWeapon is an
     * instance of the class being cast to. This is bad design and needs to be refactored so that all weapon have access
     * to the appropriate range through BaseWeapon.
     *
     * For safe casting use utils.SafeWeaponCaster.java
     *
     * If checking range of a Ranged or Throwable weapon, be sure to use the Maximum Range
     *
     * @param range maximum weapon range || range
     * @return true if mPlayerDistance is between normalRange and maxRange; else return false
     */
    private boolean isAttackInRange(int range) {
        return mPlayerDistance <= range;
    }

}
