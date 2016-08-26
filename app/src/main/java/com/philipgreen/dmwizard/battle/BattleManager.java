package com.philipgreen.dmwizard.battle;

import com.philipgreen.dmwizard.playerClasses.BasePlayerClass;

/**
 * Created by pgreen on 8/25/16.
 */
public class BattleManager {

    public BattleManager() {}

    public void exectuteAttack(AttackBuilder attack) {
        BasePlayerClass attacker = attack.getPlayerMakingAttack();
        BasePlayerClass defender = attack.getPlayerBeingAttacked();

        if (attack.getAttackType() == AttackBuilder.AttackType.MELEE) {
            executeMeleeAttack();
        }

    }

    private void executeMeleeAttack() {

    }
}
