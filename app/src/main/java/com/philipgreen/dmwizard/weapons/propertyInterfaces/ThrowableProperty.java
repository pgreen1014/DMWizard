package com.philipgreen.dmwizard.weapons.propertyInterfaces;

import com.philipgreen.dmwizard.data.WeaponProperties;

/**
 * Created by pgreen on 9/4/16.
 */
public class ThrowableProperty implements Throwable {

    private static WeaponProperties mType = WeaponProperties.THROWN;

    private int mMaxThrownRange;
    private int mMinThrownRange;

    public ThrowableProperty(int minRange, int maxRange) {
        mMinThrownRange = minRange;
        mMaxThrownRange = maxRange;
    }

    @Override
    public int maxThrownRange() {
        return mMaxThrownRange;
    }

    @Override
    public int minThrownRange() {
        return mMinThrownRange;
    }

    public WeaponProperties getType() {
        return mType;
    }
}
