package com.philipgreen.dmwizard.weapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.utils.Dice;
import com.philipgreen.dmwizard.weapons.abstractWeapons.MeleeWeapon;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Heavy;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Reach;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.TwoHanded;

/**
 * Created by pgreen on 10/28/16.
 */

public class Glaive extends MeleeWeapon implements Reach, Heavy, TwoHanded {
    @Override
    public int initCost() {
        return 20;
    }

    @Override
    public WeaponDamageType initWeaponDamageType() {
        return WeaponDamageType.SLASHING;
    }

    @Override
    public int initWeight() {
        return 6;
    }

    @Override
    public int damageRoll() {
        return Dice.rollDie(10);
    }
}
