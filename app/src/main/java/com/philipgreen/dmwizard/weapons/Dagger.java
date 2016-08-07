package com.philipgreen.dmwizard.weapons;

/**
 * Created by pgreen on 8/7/16.
 */
public class Dagger extends BaseWeapon {

    public Dagger(int damageDie, int dieNumber) {
        super(damageDie, dieNumber, new WeaponType[] {WeaponType.MELEE, WeaponType.THROWN});
    }
}
