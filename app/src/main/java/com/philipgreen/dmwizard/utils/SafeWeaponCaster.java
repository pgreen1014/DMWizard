package com.philipgreen.dmwizard.utils;

import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;
import com.philipgreen.dmwizard.weapons.abstractWeapons.MeleeWeapon;
import com.philipgreen.dmwizard.weapons.abstractWeapons.RangedWeapon;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Throwable;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Versatile;

/**
 * Created by pgreen on 9/25/16.
 *
 * Class is used for safe casting of instances of BaseWeapon.
 */

public class SafeWeaponCaster {
    private static final String EXCEPTION_MESSAGE = "weapon is not instance of ";

    public static MeleeWeapon castToMeleeWeapon(BaseWeapon baseWeapon) {
        if(!(baseWeapon instanceof MeleeWeapon)) {
            throw new ClassCastException(EXCEPTION_MESSAGE + "MeleeWeapon");
        }
        return (MeleeWeapon) baseWeapon;
    }

    public static RangedWeapon castToRangedWeapon(BaseWeapon baseWeapon) {
        if(!(baseWeapon instanceof RangedWeapon)) {
            throw new ClassCastException(EXCEPTION_MESSAGE + "RangedWeapon");
        }
        return (RangedWeapon) baseWeapon;
    }

    public static Throwable castToThrowable(BaseWeapon baseWeapon) {
        if(!(baseWeapon instanceof Throwable)) {
            throw new ClassCastException(EXCEPTION_MESSAGE + "Throwable");
        }
        return (Throwable) baseWeapon;
    }

    public static Versatile castToVersatile(BaseWeapon baseWeapon) {
        if(!(baseWeapon instanceof Versatile)) {
            throw new ClassCastException(EXCEPTION_MESSAGE + "Versatile");
        }
        return (Versatile) baseWeapon;
    }
}
