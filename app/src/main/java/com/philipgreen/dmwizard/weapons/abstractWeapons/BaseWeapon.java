package com.philipgreen.dmwizard.weapons.abstractWeapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.dice.Dice;

import java.util.HashSet;

/**
 * Created by pgreen on 8/7/16.
 */
public abstract class BaseWeapon {
    private int mDamageDie = initDamageDie(); // Die used for rolling damage
    private int mDieNumber = initDieNumber(); // Number of damage die used
    private int mMinRange = initMinRange();
    private int mMaxRange = initMaxRange();
    private int mCost = initCost();
    private int mWeight = initWeight();
    private WeaponDamageType mWeaponDamageType = initWeaponDamageType();
    private HashSet<WeaponProperties> mWeaponProperties = new HashSet<>();

    public BaseWeapon() {
        setWeaponProperties();
    }

    // ABSTRACT METHODS
    public abstract int initDamageDie();
    public abstract int initDieNumber();
    public abstract int initMinRange();
    public abstract int initMaxRange();
    public abstract int initCost();
    public abstract WeaponDamageType initWeaponDamageType();
    public abstract int initWeight();
    public abstract WeaponProperties[] initWeaponProperties();

    private void setWeaponProperties() {
        for(WeaponProperties property: initWeaponProperties()) {
            mWeaponProperties.add(property);
        }
    }

    public int damageRoll() {
        return Dice.rollDice(mDamageDie, mDieNumber);
    }

    protected void addWeaponProperty(WeaponProperties property) {
        mWeaponProperties.add(property);
    }

    public boolean containsWeaponProperty(WeaponProperties property) {
        return getWeaponProperties().contains(property);
    }

    //#################
    //     GETTERS
    //#################

    public HashSet<WeaponProperties> getWeaponProperties() {
        return mWeaponProperties;
    }

    public int getMinRange() {
        return mMinRange;
    }

    public int getMaxRange() {
        return mMaxRange;
    }
}
