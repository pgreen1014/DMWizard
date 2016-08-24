package com.philipgreen.dmwizard.utilities;

import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;
import com.philipgreen.dmwizard.weapons.abstractWeapons.BaseWeapon;

/**
 * Created by pgreen on 8/23/16.
 */
public class AttackBuilder {
    private BaseWeapon mMainHandAttackingWeapon;
    private BaseWeapon mOffHandAttackingWeapon;
    private BasePlayerClass mPlayerBeingAttacked;
    private BasePlayerClass mPlayerMakingAttack;
    private BaseStats mAttackModifier;
    private int mPlayerDistance;

    public AttackBuilder() {

    }

    public void setMainHandAttackingWeapon(BaseWeapon weapon) {
        mMainHandAttackingWeapon = weapon;
    }

    public void setOffHandAttackingWeapon(BaseWeapon weapon) {
        mOffHandAttackingWeapon = weapon;
    }

    public void setAttackTarget(BasePlayerClass playerBeingAttacked) {
        mPlayerBeingAttacked = playerBeingAttacked;
    }

    public void setPlayerMakingAttack(BasePlayerClass playerMakingAttack) {
        mPlayerMakingAttack = playerMakingAttack;
    }

    public void setAttackModifier(BaseStats attackModifier) {
        mAttackModifier = attackModifier;
    }

    public void setPlayerDistance(int playerDistance) {
        mPlayerDistance = playerDistance;
    }
}
