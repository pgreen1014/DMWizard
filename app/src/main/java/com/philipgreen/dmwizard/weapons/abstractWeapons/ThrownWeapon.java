package com.philipgreen.dmwizard.weapons.abstractWeapons;

import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

/**
 * Created by pgreen on 8/7/16.
 */
public abstract class ThrownWeapon extends MeleeWeapon {
    // Range in feet
    // TODO add converter from feet to spaces 5:1 ratio
    private int mMinThrowRange = initMinThrowRange();
    private int mMaxThrowRange = initMaxThrowRange();

    public ThrownWeapon() {
        super();
    }

    // ABSTRACT METHODS
    public abstract int initMinThrowRange();
    public abstract int initMaxThrowRange();
}
