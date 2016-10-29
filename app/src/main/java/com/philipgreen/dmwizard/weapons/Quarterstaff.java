package com.philipgreen.dmwizard.weapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.utils.Dice;
import com.philipgreen.dmwizard.weapons.abstractWeapons.MeleeWeapon;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Versatile;

/**
 * Created by pgreen on 10/29/16.
 */

public class Quarterstaff extends MeleeWeapon implements Versatile{
    @Override
    public int initCost() {
        // 2sp
        return 0;
    }

    @Override
    public WeaponDamageType initWeaponDamageType() {
        return WeaponDamageType.BLUDGEONING;
    }

    @Override
    public int initWeight() {
        return 4;
    }

    @Override
    public int damageRoll() {
        return Dice.rollDie(6);
    }

    @Override
    public int versatileDamageRoll() {
        return Dice.rollDie(8);
    }
}
