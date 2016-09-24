package com.philipgreen.dmwizard.weapons.abstractWeapons;

import com.philipgreen.dmwizard.weapons.propertyInterfaces.Reach;

/**
 * Created by pgreen on 8/7/16.
 */
public abstract class MeleeWeapon extends BaseWeapon {
    private static final String TAG = "MeleeWeapon";
    private int mRange;

    protected MeleeWeapon() {
        super();
        initMaxRange();
    }

    public void initMaxRange() {
        if (this instanceof Reach) {
            mRange = 10;
        } else {
            mRange = 5;
        }
    }

    public int getRange() {
        return mRange;
    }


}
