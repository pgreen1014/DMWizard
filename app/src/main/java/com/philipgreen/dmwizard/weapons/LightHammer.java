package com.philipgreen.dmwizard.weapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.utils.Dice;
import com.philipgreen.dmwizard.weapons.abstractWeapons.MeleeWeapon;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Light;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Throwable;

/**
 * Created by pgreen on 10/29/16.
 */

public class LightHammer extends MeleeWeapon implements Light, Throwable{
    @Override
    public int initCost() {
        return 2;
    }

    @Override
    public WeaponDamageType initWeaponDamageType() {
        return WeaponDamageType.BLUDGEONING;
    }

    @Override
    public int initWeight() {
        return 2;
    }

    @Override
    public int damageRoll() {
        return Dice.rollDie(4);
    }

    @Override
    public int maxThrownRange() {
        return 20;
    }

    @Override
    public int normalThrownRange() {
        return 60;
    }

    @Override
    public int throwAttack() {
        return this.damageRoll();
    }
}
