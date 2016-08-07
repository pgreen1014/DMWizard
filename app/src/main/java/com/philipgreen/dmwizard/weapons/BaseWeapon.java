package com.philipgreen.dmwizard.weapons;

import com.philipgreen.dmwizard.dice.Dice;

/**
 * Created by pgreen on 8/7/16.
 */
public abstract class BaseWeapon {
    private int mDamageDie; // Die used for rolling damage
    private int mDieNumber; // Number of damage die used
    private WeaponType[] mWeaponTypes;

    public BaseWeapon(int damageDie, int dieNumber, WeaponType[] weaponTypes) {
        this.mDamageDie = damageDie;
        this.mDieNumber = dieNumber;
        this.mWeaponTypes = weaponTypes;
    }

    public int damageRoll() {
        return Dice.rollDice(mDamageDie, mDieNumber);
    }
}
