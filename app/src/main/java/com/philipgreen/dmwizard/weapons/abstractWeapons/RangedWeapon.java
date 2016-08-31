package com.philipgreen.dmwizard.weapons.abstractWeapons;

import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

/**
 * Created by pgreen on 8/7/16.
 */
public abstract class RangedWeapon extends BaseWeapon {
    private int mAmmunition;

    public RangedWeapon() {
        super();
        addWeaponProperty(WeaponProperties.RANGE);
    }

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
