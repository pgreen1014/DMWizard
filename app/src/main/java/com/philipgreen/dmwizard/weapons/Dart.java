package com.philipgreen.dmwizard.weapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Finesse;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Throwable;

/**
 * Created by pgreen on 9/8/16.
 */
public class Dart extends BaseWeapon implements Throwable, Finesse{

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
        // TODO 5cp
        return 0;
    }

    @Override
    public WeaponDamageType initWeaponDamageType() {
        return WeaponDamageType.PIERCING;
    }

    @Override
    public int initWeight() {
        // TODO 1/4 lb
        return 0;
    }

    @Override
    public int maxThrownRange() {
        return 20;
    }

    @Override
    public int minThrownRange() {
        return 60;
    }

    @Override
    public int throwAttack() {
        return damageRoll();
    }
}
