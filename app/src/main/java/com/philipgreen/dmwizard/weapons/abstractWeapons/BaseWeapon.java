package com.philipgreen.dmwizard.weapons.abstractWeapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.dice.Dice;

/**
 * Created by pgreen on 8/7/16.
 */
public abstract class BaseWeapon {
    private int mDamageDie = initDamageDie(); // Die used for rolling damage
    private int mDieNumber = initDieNumber(); // Number of damage die used
    private int mCost = initCost();
    private WeaponDamageType mWeaponDamageType = initWeaponDamageType();
    private int mWeight = initWeight();
    private WeaponProperties[] mWeaponProperties = initWeaponProperties();

    public BaseWeapon() {
    }

    // ABSTRACT METHODS
    public abstract int initDamageDie();
    public abstract int initDieNumber();
    public abstract int initCost();
    public abstract WeaponDamageType initWeaponDamageType();
    public abstract int initWeight();
    public abstract WeaponProperties[] initWeaponProperties();

    public int damageRoll() {
        return Dice.rollDice(mDamageDie, mDieNumber);
    }
}
