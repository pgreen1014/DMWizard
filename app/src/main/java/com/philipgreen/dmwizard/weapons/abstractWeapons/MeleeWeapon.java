package com.philipgreen.dmwizard.weapons.abstractWeapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.data.WeaponProperties;

/**
 * Created by pgreen on 8/7/16.
 */
public class MeleeWeapon extends BaseWeapon {
    private static final String TAG = "MeleeWeapon";

    protected MeleeWeapon() {
        super();
    }

    @Override
    public int initDamageDie() {
        return 0;
    }

    @Override
    public int initDieNumber() {
        return 0;
    }

    @Override
    public int initMinRange() {
        return 0;
    }

    @Override
    public int initMaxRange() {
        if (this.containsWeaponProperty(WeaponProperties.REACH)) {
            return 10;
        } else {
            return 5;
        }
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
