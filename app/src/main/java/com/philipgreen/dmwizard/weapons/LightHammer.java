package com.philipgreen.dmwizard.weapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.data.WeaponProperties;

/**
 * Created by pgreen on 8/10/16.
 */
public class LightHammer extends ThrownWeapon {
    @Override
    public int initMinThrowRange() {
        return 0;
    }

    @Override
    public int initMaxThrowRange() {
        return 0;
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
