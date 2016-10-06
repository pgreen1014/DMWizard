package com.philipgreen.dmwizard;

import com.philipgreen.dmwizard.battle.Attack;
import com.philipgreen.dmwizard.battle.AttackBuilder;
import com.philipgreen.dmwizard.battle.BattleManager;
import com.philipgreen.dmwizard.battle.damageRolls.DamageRollBehavior;
import com.philipgreen.dmwizard.battle.damageRolls.DamageRollRegular;
import com.philipgreen.dmwizard.data.Skills;
import com.philipgreen.dmwizard.playerClasses.Barbarian;
import com.philipgreen.dmwizard.races.Dwarf;
import com.philipgreen.dmwizard.weapons.Dagger;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Created by pgreen on 10/5/16.
 */

public class BattleTest {
    private PlayerCharacter mAttackingCharacter;
    private PlayerCharacter mDefendingCharacter;
    private Attack mDaggerAttack;

    @Before
    public void initialize() {
        Barbarian barb = new Barbarian(1, new Skills[]{Skills.ATHLETICS, Skills.ANIMAL_HANDLING});
        Dwarf dwarf = new Dwarf();
        mAttackingCharacter = new PlayerCharacter(barb, dwarf, 18, 15, 16, 13, 12, 14);
        mDefendingCharacter = new PlayerCharacter(barb, dwarf, 18, 15, 14, 12, 14, 18);
        Dagger dagger = new Dagger();

        AttackBuilder attackBuilder = new AttackBuilder();
        mDaggerAttack = attackBuilder
                .setAttacker(mAttackingCharacter)
                .setDefender(mDefendingCharacter)
                .setAttackingWeapon(dagger)
                .setMeleeAttack()
                .build();
    }

    @Test
    public void testMeleeDaggerAttack() {
        int defenderStartingHealth = mDefendingCharacter.getHitPoints();
        // verify dagger is using melee attack
        assertTrue(mDaggerAttack.getAttackType() == AttackBuilder.getAttackTypeMelee());

        BattleManager bm = new BattleManager(mDaggerAttack);
        bm.executeAttack();

        if (bm.isLastExecutedAttackHit()) {
            // If attack hit, defender should have less hit points than before attack
            assertTrue(mDefendingCharacter.getHitPoints() < defenderStartingHealth);
        } else {
            // If attack missed, defender should have the same hit points as before attack
            assertTrue(mDefendingCharacter.getHitPoints() == defenderStartingHealth);
        }
    }
}
