package com.philipgreen.dmwizard.utilities;

import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

/**
 * Created by pgreen on 8/23/16.
 */
public class AttackBuilder {
    private BaseWeapon mAttackingWeapon;
    private BasePlayerClass mAttackTarget;

    public AttackBuilder() {

    }

    public void setWeapon(BaseWeapon weapon) {
        mAttackingWeapon = weapon;
    }

    public void setAttackTarget(BasePlayerClass attackTarget) {
        mAttackTarget = attackTarget;
    }
}
