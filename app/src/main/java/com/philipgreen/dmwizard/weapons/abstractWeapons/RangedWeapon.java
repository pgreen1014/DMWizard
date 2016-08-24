package com.philipgreen.dmwizard.weapons.abstractWeapons;

import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

/**
 * Created by pgreen on 8/7/16.
 */
public abstract class RangedWeapon extends BaseWeapon {

    public RangedWeapon() {
        super();
        addWeaponProperty(WeaponProperties.RANGE);
    }
}
