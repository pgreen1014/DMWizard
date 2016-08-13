package com.philipgreen.dmwizard.weapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.weapons.abstractWeapons.RangedWeapon;

/**
 * Created by pgreen on 8/13/16.
 */
public class ShortBow extends RangedWeapon {
    private final static String TAG = "ShortBow";

    @Override
    public int initDamageDie() {
        return 0;
    }

    @Override
    public int initDieNumber() {
        return 0;
    }

    @Override
    public int initCost() {
        return 0;
    }

    @Override
    public WeaponDamageType initWeaponDamageType() {
        return null;
    }

    @Override
    public int initWeight() {
        return 0;
    }

    @Override
    public WeaponProperties[] initWeaponProperties() {
        return new WeaponProperties[0];
    }
}
