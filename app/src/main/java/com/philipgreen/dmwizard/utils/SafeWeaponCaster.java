package com.philipgreen.dmwizard.utils;

import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;
import com.philipgreen.dmwizard.weapons.abstractWeapons.MeleeWeapon;
import com.philipgreen.dmwizard.weapons.abstractWeapons.RangedWeapon;

/**
 * Created by pgreen on 9/25/16.
 *
 * Class is used for safe casting of instances of BaseWeapon.
 */

public class SafeWeaponCaster {

    public static MeleeWeapon castToMelee(BaseWeapon baseWeapon) {
        if(!(baseWeapon instanceof MeleeWeapon)) {
            throw new ClassCastException("weapon is not instance of MeleeWeapon");
        }
        return (MeleeWeapon) baseWeapon;
    }

    public static RangedWeapon castToRangedWeapon(BaseWeapon baseWeapon) {
        if(!(baseWeapon instanceof RangedWeapon)) {
            throw new ClassCastException("weapon is not instance of RangedWeapon");
        }
        return (RangedWeapon) baseWeapon;
    }
}
