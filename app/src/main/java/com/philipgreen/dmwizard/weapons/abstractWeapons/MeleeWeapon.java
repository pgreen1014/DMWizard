package com.philipgreen.dmwizard.weapons.abstractWeapons;

import android.util.Log;

import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.dice.Dice;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

/**
 * Created by pgreen on 8/7/16.
 */
public abstract class MeleeWeapon extends BaseWeapon {
    private static final String TAG = "MeleeWeapon";
    private int mVersatileDamageDie;
    private int mVersatileDieNumber;

    public MeleeWeapon() {
        super();

        if (this.containsWeaponProperty(WeaponProperties.VERSATILE)) {
            mVersatileDamageDie = initVersatileDamageDie();
            mVersatileDieNumber = initVersatileDieNumber();
        }
    }

    public abstract int initVersatileDamageDie();
    public abstract int initVersatileDieNumber();

    /**
     * Makes of two handed weapon attack if weapon is of they type WeaponProperties.VERSATILE
     * @return two handed weapon damage if weapon is versatile; uses BaseWeapon.damageRoll() if not versatile
     */
    public int rollTwoHandedDamage() {
        if (!this.containsWeaponProperty(WeaponProperties.VERSATILE)) {
            Log.e(TAG, "Unable to use method rollTwoHandedDamage. Weapon is not of the type WeaponProperties.VERSATILE " + this.toString());
            return damageRoll();
        }
        return Dice.rollDice(mVersatileDamageDie, mVersatileDieNumber);
    }
}
