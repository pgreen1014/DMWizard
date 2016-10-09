package com.philipgreen.dmwizard;

import com.philipgreen.dmwizard.battle.Attack;
import com.philipgreen.dmwizard.battle.AttackBuilder;
import com.philipgreen.dmwizard.battle.BattleManager;
import com.philipgreen.dmwizard.data.Skills;
import com.philipgreen.dmwizard.playerClasses.Barbarian;
import com.philipgreen.dmwizard.races.Dwarf;
import com.philipgreen.dmwizard.weapons.Dagger;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Created by pgreen on 10/5/16.
 */

public class BattleTest {
    private PlayerCharacter mAttackingCharacter;
    private PlayerCharacter mDefendingCharacter;
    private int mDefenderStartingHealth;
    private Attack mDaggerMeleeAttack;
    private Attack mDaggerThrownAttack;

    @Before
    public void initialize() {
        Barbarian barb = new Barbarian(1, new Skills[]{Skills.ATHLETICS, Skills.ANIMAL_HANDLING});
        Dwarf dwarf = new Dwarf();
        mAttackingCharacter = new PlayerCharacter(barb, dwarf, 18, 15, 16, 13, 12, 14);
        mDefendingCharacter = new PlayerCharacter(barb, dwarf, 18, 15, 14, 12, 14, 18);
        mDefenderStartingHealth = mDefendingCharacter.getHitPoints();
        Dagger dagger = new Dagger();

        AttackBuilder attackBuilder = new AttackBuilder();
        mDaggerMeleeAttack = attackBuilder
                .setAttacker(mAttackingCharacter)
                .setDefender(mDefendingCharacter)
                .setAttackingWeapon(dagger)
                .setMeleeAttack()
                .build();

        mDaggerThrownAttack = attackBuilder
                .setAttacker(mAttackingCharacter)
                .setDefender(mDefendingCharacter)
                .setAttackingWeapon(dagger)
                .setPlayerDistance(20)
                .setThrownAttack()
                .build();
    }

    @Test
    public void testMeleeDaggerAttack() {
        // verify dagger is using melee attack
        assertTrue(mDaggerMeleeAttack.getAttackType() == AttackBuilder.getAttackTypeMelee());

        BattleManager bm = new BattleManager(mDaggerMeleeAttack);
        bm.executeAttack();

        if (bm.isLastExecutedAttackHit()) {
            // If attack hit, defender should have less hit points than before attack
            assertTrue(mDefendingCharacter.getHitPoints() < mDefenderStartingHealth);
        } else {
            // If attack missed, defender should have the same hit points as before attack
            assertTrue(mDefendingCharacter.getHitPoints() == mDefenderStartingHealth);
        }
    }

    @Test
    public void testThrownDaggerAttack() {
        assertTrue(mDaggerThrownAttack.getAttackType() == AttackBuilder.getAttackTypeThrown());

        BattleManager bm = new BattleManager(mDaggerThrownAttack);
        bm.executeAttack();

        if (bm.isLastExecutedAttackHit()) {
            assertTrue(mDefendingCharacter.getHitPoints() < mDefenderStartingHealth);
        } else {
            assertTrue(mDefendingCharacter.getHitPoints() == mDefenderStartingHealth);
        }
    }
}
