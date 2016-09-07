package com.philipgreen.dmwizard.weapons.abstractWeapons;

import com.philipgreen.dmwizard.data.WeaponProperties;

/**
 * Created by pgreen on 8/7/16.
 */
public abstract class RangedWeapon extends BaseWeapon {
    private int mAmmunition;
    private int mMinRangeInFeet = minRangeInFeet();
    private int mMaxRangeInFeet = maxRangeInFeet();

    public RangedWeapon() {
        super();
        // add RANGE PROPERTY in case property not added in class
        addWeaponProperty(WeaponProperties.RANGE);
    }

    protected abstract int minRangeInFeet();
    protected abstract int maxRangeInFeet();

    public int getAmmunition() {
        return mAmmunition;
    }

    public void setAmmunition(int ammunition) {
        mAmmunition = ammunition;
    }

    public void addAmmunition(int ammunitionAmountBeingAdded) {
        mAmmunition += mAmmunition;
    }

    public void useAmmunition() {
        mAmmunition -= 1;
    }
}
