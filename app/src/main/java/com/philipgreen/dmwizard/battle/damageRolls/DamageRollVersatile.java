package com.philipgreen.dmwizard.battle.damageRolls;

import com.philipgreen.dmwizard.weapons.propertyInterfaces.Versatile;

/**
 * Created by pgreen on 9/25/16.
 */

public class DamageRollVersatile implements DamageRollBehavior {
    private Versatile mWeapon;

    public DamageRollVersatile(Versatile weapon) {
        this.mWeapon = weapon;
    }

    @Override
    public int damageRoll() {
        return mWeapon.versatileDamageRoll();
    }
}
