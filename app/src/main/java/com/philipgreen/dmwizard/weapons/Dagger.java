package com.philipgreen.dmwizard.weapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.weapons.abstractWeapons.MeleeWeapon;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Finesse;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Light;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Throwable;

/**
 * Created by pgreen on 8/7/16.
 */
public class Dagger extends MeleeWeapon implements Throwable, Light, Finesse {

    public Dagger() {
        super();
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

    @Override
    public int maxThrownRange() {
        return 60;
    }

    @Override
    public int minThrownRange() {
        return 20;
    }

    @Override
    public int throwAttack() {
        return this.damageRoll();
    }
}
