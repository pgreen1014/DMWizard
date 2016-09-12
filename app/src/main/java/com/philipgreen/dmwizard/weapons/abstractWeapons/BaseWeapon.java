package com.philipgreen.dmwizard.weapons.abstractWeapons;

import android.util.Log;

import com.philipgreen.dmwizard.data.WeaponDamageType;
import com.philipgreen.dmwizard.data.WeaponProperties;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Ammunition;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Finesse;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Light;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Loading;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Range;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Reach;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Special;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Throwable;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.TwoHanded;
import com.philipgreen.dmwizard.weapons.propertyInterfaces.Versatile;

/**
 * Created by pgreen on 8/7/16.
 */
public abstract class BaseWeapon {
    private static final String TAG = "BaseWeapon";

    private int mDamageDie; // Die used for rolling damage
    private int mDieNumber; // Number of damage die used
    private int mCost;
    private int mWeight;
    private WeaponDamageType mWeaponDamageType;

    public BaseWeapon() {
        mCost = initCost();
        mWeight = initWeight();
        mWeaponDamageType = initWeaponDamageType();
    }

    // ABSTRACT METHODS
    public abstract int initCost();
    public abstract WeaponDamageType initWeaponDamageType();
    public abstract int initWeight();
    public abstract int damageRoll();

    public boolean hasWeaponProperty(WeaponProperties property) {
        switch (property) {
            case AMMUNITION:
                return this instanceof Ammunition;
            case FINESSE:
                return this instanceof Finesse;
            case LIGHT:
                return this instanceof Light;
            case LOADING:
                return this instanceof Loading;
            case RANGE:
                return this instanceof Range;
            case REACH:
                return this instanceof Reach;
            case SPECIAL:
                return this instanceof Special;
            case THROWN:
                return this instanceof Throwable;
            case TWO_HANDED:
                return this instanceof TwoHanded;
            case VERSATILE:
                return this instanceof Versatile;
            default:
                Log.e(TAG, "Unhandled property " + property.toString());
                return false;
        }

    }

    //#################
    //     GETTERS
    //#################

}
