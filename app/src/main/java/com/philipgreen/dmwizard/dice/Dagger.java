package com.philipgreen.dmwizard.dice;

import com.philipgreen.dmwizard.weapons.BaseWeapon;
import com.philipgreen.dmwizard.weapons.WeaponType;

/**
 * Created by pgreen on 8/7/16.
 */
public class Dagger extends BaseWeapon {
    public Dagger(int damageDie, int dieNumber) {
        super(damageDie, dieNumber, new WeaponType[] {WeaponType.MELEE, WeaponType.THROWN});
    }
}
