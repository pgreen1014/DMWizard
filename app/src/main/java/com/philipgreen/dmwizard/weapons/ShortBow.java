package com.philipgreen.dmwizard.weapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.utils.Dice;
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
    public int damageRoll() {
        return Dice.rollDie(6);
    }

    @Override
    protected int normalRange() {
        return 80;
    }

    @Override
    protected int maxRange() {
        return 320;
    }

    @Override
    public int initCost() {
        return 25;
    }

    @Override
    public WeaponDamageType initWeaponDamageType() {
        return WeaponDamageType.PIERCING;
    }

    @Override
    public int initWeight() {
        return 2;
    }
}
