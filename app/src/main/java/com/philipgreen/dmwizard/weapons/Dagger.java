package com.philipgreen.dmwizard.weapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.weapons.abstractWeapons.MeleeWeapon;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.BaseWeaponProperty;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.ThrowableProperty;

/**
 * Created by pgreen on 8/7/16.
 */
public class Dagger extends MeleeWeapon {
    private static final BaseWeaponProperty[] WEAPON_PROPERTIES = {new ThrowableProperty(20, 60)};

    public Dagger() {
        super(WEAPON_PROPERTIES);
    }

    @Override
    public int initDamageDie() {
        return 4;
    }

    @Override
    public int initDieNumber() {
        return 1;
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
        return 1;
    }

    @Override
    public WeaponProperties[] initWeaponProperties() {
        // Leave out Thrown property because this is represented by being extending abstract class
        return new WeaponProperties[] {WeaponProperties.FINESSE, WeaponProperties.LIGHT, WeaponProperties.THROWN};
    }

}
