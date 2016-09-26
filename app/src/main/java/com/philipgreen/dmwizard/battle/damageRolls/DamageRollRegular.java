package com.philipgreen.dmwizard.battle.damageRolls;

import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

/**
 * Created by pgreen on 9/25/16.
 *
 * DamageRollBehavior class that constructs a basic damage roll implementation.
 */

public class DamageRollRegular implements DamageRollBehavior {
    private BaseWeapon mBaseWeapon;

    public DamageRollRegular(BaseWeapon weapon) {
        this.mBaseWeapon = weapon;
    }

    @Override
    public int damageRoll() {
        return mBaseWeapon.damageRoll();
    }
}
