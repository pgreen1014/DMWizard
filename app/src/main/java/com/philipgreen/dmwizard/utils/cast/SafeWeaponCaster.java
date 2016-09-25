package com.philipgreen.dmwizard.utils.cast;

import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;
import com.philipgreen.dmwizard.weapons.abstractWeapons.MeleeWeapon;

/**
 * Created by pgreen on 9/25/16.
 *
 * Class is used for safe casting of instances of BaseWeapon.
 */

public class SafeWeaponCaster {

    public static MeleeWeapon castToMelee(BaseWeapon baseWeapon) {
        if(!(baseWeapon instanceof MeleeWeapon)) {
            throw new IllegalArgumentException("weapon is not instance of MeleeWeapon");
        }
        return (MeleeWeapon) baseWeapon;
    }
}
