package com.philipgreen.dmwizard.battle;

import com.philipgreen.dmwizard.PlayerCharacter;
import com.philipgreen.dmwizard.battle.damageRolls.DamageRollBehavior;
import com.philipgreen.dmwizard.battle.damageRolls.DamageRollRegular;
import com.philipgreen.dmwizard.battle.damageRolls.DamageRollVersatile;
import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.utils.SafeWeaponCaster;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Versatile;

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

    Attack(AttackBuilder attackBuild) {
        mAttackingWeapon = attackBuild.getAttackingWeapon();
        mDefender = attackBuild.getDefender();
        mAttacker = attackBuild.getAttacker();
        mAttackModifierStat = attackBuild.getAttackModifierStat();
        mPlayerDistance = attackBuild.getPlayerDistance();
        mTwoHandedAttack = attackBuild.isTwoHandedAttack();
        mOffHandWeaponAttack = attackBuild.isOffHandWeaponAttack();
        mAttackType = attackBuild.getAttackType();
    }

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

    public AttackBuilder.AttackType getAttackType() {
        return mAttackType;
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

    private void setDamageRollBehavior() {
        if (mTwoHandedAttack && (mAttackingWeapon instanceof Versatile)) {
            mDamageRollBehavior = new DamageRollVersatile(SafeWeaponCaster.castToVersatile(mAttackingWeapon));
        } else {
            mDamageRollBehavior = new DamageRollRegular(mAttackingWeapon);
        }
    }
}
