package com.philipgreen.dmwizard.weapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.dice.Dice;
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
    public int damageRoll() {
        return Dice.rollDice(1, 4);
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
