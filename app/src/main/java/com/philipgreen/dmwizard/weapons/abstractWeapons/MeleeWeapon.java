package com.philipgreen.dmwizard.weapons.abstractWeapons;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Reach;

/**
 * Created by pgreen on 8/7/16.
 */
public abstract class MeleeWeapon extends BaseWeapon {
    private static final String TAG = "MeleeWeapon";
    private int mMinRange = 0;
    private int mMaxRange;

    protected MeleeWeapon() {
        super();
        initMaxRange();
    }

    public void initMaxRange() {
        if (this instanceof Reach) {
            mMaxRange = 10;
        } else {
            mMaxRange = 5;
        }
    }

    public int getMinRange() {
        return mMinRange;
    }

    public int getMaxRange() {
        return mMaxRange;
    }


}
