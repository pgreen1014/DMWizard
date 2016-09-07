package com.philipgreen.dmwizard.weapons.abstractWeapons;

import android.util.Log;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.dice.Dice;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Throwable;

import java.util.HashSet;

/**
 * Created by pgreen on 8/7/16.
 */
public abstract class BaseWeapon {
    private static final String TAG = "BaseWeapon";

    private int mDamageDie; // Die used for rolling damage
    private int mDieNumber; // Number of damage die used
    private int mMinRange;
    private int mMaxRange;
    private int mCost;
    private int mWeight;
    private WeaponDamageType mWeaponDamageType;

    private HashSet<WeaponProperties> mWeaponProperties = new HashSet<>();
    private Throwable mThrowableProperty;

    public BaseWeapon() {
        setWeaponProperties();
        initializeMemberVariables();
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

    private void initializeMemberVariables() {
        mDamageDie = initDamageDie();
        mDieNumber = initDieNumber();
        mMinRange = initMinRange();
        mMaxRange = initMaxRange();
        mCost = initCost();
        mWeight = initWeight();
        mWeaponDamageType = initWeaponDamageType();
    }

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

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //                                WEAPON PROPERTY METHODS                                     //
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public int getMaxThrownRange() {
        if (mThrowableProperty == null) {
            Log.e(TAG, "weapon is not throwable: " + this.toString());
            return 0;
        }
        return mThrowableProperty.maxThrownRange();
    }

    public int getMinThrownRage() {
        if (mThrowableProperty == null) {
            Log.e(TAG, "weapon is not throwable: " + this.toString());
            return 0;
        }
        return mThrowableProperty.minThrownRange();
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
