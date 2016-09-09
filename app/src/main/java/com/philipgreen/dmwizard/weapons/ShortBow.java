package com.philipgreen.dmwizard.weapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.weapons.abstractWeapons.RangedWeapon;

/**
 * Created by pgreen on 8/13/16.
 */
public class ShortBow extends RangedWeapon {
    private final static String TAG = "ShortBow";

    public ShortBow() {
        super();
    }

    @Override
    public int initDamageDie() {
        return 6;
    }

    @Override
    public int initDieNumber() {
        return 1;
    }

    @Override
    public int initMinRange() {
        return 0;
    }

    @Override
    public int initMaxRange() {
        return 0;
    }

    @Override
    public int initCost() {
        return 0;
    }

    @Override
    public WeaponDamageType initWeaponDamageType() {
        return WeaponDamageType.PIERCING;
    }

    @Override
    public int initWeight() {
        return 2;
    }

    @Override
    protected int minRangeInFeet() {
        return 80;
    }

    @Override
    protected int maxRangeInFeet() {
        return 320;
    }
}
