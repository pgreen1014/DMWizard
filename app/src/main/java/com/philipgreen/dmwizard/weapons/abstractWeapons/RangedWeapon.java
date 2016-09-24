package com.philipgreen.dmwizard.weapons.abstractWeapons;

import com.philipgreen.dmwizard.weapons.propertyInterfaces.Ammunition;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Range;

/**
 * Created by pgreen on 8/7/16.
 */
public abstract class RangedWeapon extends BaseWeapon implements Range, Ammunition{
    private int mAmmunition;
    private int mNormalRange = normalRange();
    private int mMaxRange = maxRange();

    public RangedWeapon() {
        super();
    }

    protected abstract int normalRange();
    protected abstract int maxRange();

    public int getAmmunition() {
        return mAmmunition;
    }

    public void setAmmunition(int ammunition) {
        mAmmunition = ammunition;
    }

    public void addAmmunition(int ammunitionAmountBeingAdded) {
        mAmmunition += mAmmunition;
    }

    public int getNormalRange() {
        return mNormalRange;
    }

    public int getMaxRange() {
        return mMaxRange;
    }

    public void useAmmunition() {
        mAmmunition -= 1;
        // TODO: handle when there is no ammunition
    }
}
