package com.philipgreen.dmwizard;

import com.philipgreen.dmwizard.battle.AttackBuilder;
import com.philipgreen.dmwizard.data.Skills;
import com.philipgreen.dmwizard.playerClasses.Barbarian;
import com.philipgreen.dmwizard.races.Dwarf;
import com.philipgreen.dmwizard.weapons.Dagger;
import com.philipgreen.dmwizard.weapons.ShortBow;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by pgreen on 10/7/16.
 */

public class AttackBuilderTest {
    private PlayerCharacter mAttacker;
    private PlayerCharacter mDefender;
    private AttackBuilder mAttackBuilder;

    @Before
    public void initialize() {
        mAttackBuilder = new AttackBuilder();

        Barbarian barb = new Barbarian(1, new Skills[]{Skills.ATHLETICS, Skills.ANIMAL_HANDLING});
        Dwarf dwarf = new Dwarf();
        mAttacker = new PlayerCharacter(barb, dwarf, 18, 15, 16, 13, 12, 14);
        mDefender = new PlayerCharacter(barb, dwarf, 18, 15, 14, 12, 14, 18);
    }

    @Test
    public void basicBuildTest() {
        mAttackBuilder
            .setMeleeAttack()
            .setAttackingWeapon(new Dagger())
            .setAttacker(mAttacker)
            .setDefender(mDefender)
            .setPlayerDistance(5);

        assertTrue(testBuild(mAttackBuilder));
    }

    @Test
    public void attackerNotSetTest() {
        mAttackBuilder
                .setMeleeAttack()
                .setDefender(mDefender)
                .setAttackingWeapon(new Dagger());

        assertFalse("Build must fail if attacker not set", testBuild(mAttackBuilder));
    }

    @Test
    public void defenderNotSetTest() {
        mAttackBuilder
            .setMeleeAttack()
            .setAttacker(mAttacker)
            .setAttackingWeapon(new Dagger());

        assertFalse("Build must fail if defender not set", testBuild(mAttackBuilder));
    }

    @Test
    public void weaponNotSetTest() {
        mAttackBuilder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setMeleeAttack();

        assertFalse("Weapon must be set", testBuild(mAttackBuilder));
    }

    @Test
    public void attackTypeNotSetTest() {
        mAttackBuilder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setAttackingWeapon(new Dagger());

        assertFalse("AttackType must be set", testBuild(mAttackBuilder));
    }

    @Test
    public void testUsingNonRangedWeaponAsRanged() {
        mAttackBuilder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setAttackingWeapon(new Dagger())
                .setRangedAttack()
                .setPlayerDistance(20);

        assertFalse("cannot use dagger as ranged weapon", testBuild(mAttackBuilder));
    }

    @Test
    public void testMeleeDistance() {
        mAttackBuilder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setAttackingWeapon(new Dagger())
                .setMeleeAttack();

        mAttackBuilder.setPlayerDistance(5);
        assertTrue("melee attack should hit at 5 ft distance", testBuild(mAttackBuilder));

        mAttackBuilder.setPlayerDistance(15);
        assertFalse("melee attack should not work at 15 ft distance", testBuild(mAttackBuilder));

        mAttackBuilder.setPlayerDistance(10);
        assertFalse("melee attack should not work at 10 ft distance unless reach weapon", testBuild(mAttackBuilder));
    }

    @Test
    public void testThrownDistance() {
        mAttackBuilder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setAttackingWeapon(new Dagger())
                .setThrownAttack();

        assertFalse("distance must be set for thrown attack", testBuild(mAttackBuilder));

        mAttackBuilder.setPlayerDistance(20);

        assertTrue("thrown dagger should hit at 20 foot range", testBuild(mAttackBuilder));

        mAttackBuilder.setPlayerDistance(60);
        assertTrue("thrown dagger should hit with disadvantage at 60 ft range", testBuild(mAttackBuilder));

        mAttackBuilder.setPlayerDistance(65);
        assertFalse("thrown dagger should not work at 65 ft range", testBuild(mAttackBuilder));

        mAttackBuilder.setPlayerDistance(5);
        assertTrue("thrown dagger should work at disadvantage at close range", testBuild(mAttackBuilder));
    }

    @Test
    public void daggerTest() {
        Dagger dagger = new Dagger();

        // Set fields that will stay same for dagger
        mAttackBuilder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setAttackingWeapon(dagger);

        // test melee attack
        mAttackBuilder
                .setMeleeAttack()
                .setPlayerDistance(5);
        assertTrue("Melee dagger attack should pass", testBuild(mAttackBuilder));

        // test thrown attack
        mAttackBuilder
                .setThrownAttack()
                .setPlayerDistance(15);
        assertTrue("Thrown dagger attack should pass", testBuild(mAttackBuilder));

        // Test two handed attacks
        mAttackBuilder
                .setMeleeAttack()
                .setPlayerDistance(5)
                .setTwoHandedAttack();
        assertFalse("Cannot use dagger as melee && two handed", testBuild(mAttackBuilder));
        mAttackBuilder
                .setThrownAttack()
                .setTwoHandedAttack();
        assertFalse("Cannot use dagger as Thrown && two handed", testBuild(mAttackBuilder));

        mAttackBuilder
                .setMeleeAttack()
                .setTwoHandedAttack(false)
                .setAttackModifierStrength();
        assertTrue("dagger is finesse weapon and can use strength", testBuild(mAttackBuilder));
        mAttackBuilder
                .setAttackModifierDexterity();
        assertTrue("dagger is finesse weapon and can use dexterity", testBuild(mAttackBuilder));
    }

    @Test
    public void shortBowTest() {
        ShortBow shortBow = new ShortBow();

        mAttackBuilder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setAttackingWeapon(shortBow);

        // test ranged attack
        mAttackBuilder
                .setRangedAttack()
                .setPlayerDistance(60);
        assertTrue(testBuild(mAttackBuilder));

        // test melee ranged attack
        mAttackBuilder
                .setPlayerDistance(5);
        assertTrue("Ranged weapons at melee distance should build", testBuild(mAttackBuilder));
        assertTrue("Ranged weapons at melee distance should build with disadvantage", mAttackBuilder.isDisadvantage());

        // test setting clearing advantage/disadvantage
        mAttackBuilder
                .clearAdvantageState();
        assertFalse(mAttackBuilder.isDisadvantage());

        mAttackBuilder
                .setPlayerDistance(90);
        assertTrue("Ranged weapon should build when distance less than max range", testBuild(mAttackBuilder));
        assertTrue("Ranged weapon fired between normal range and max range should have disadvantage", mAttackBuilder.isDisadvantage());

        mAttackBuilder
                .setPlayerDistance(325);
        assertFalse("Ranged weapon above max distance should fail", testBuild(mAttackBuilder));
    }

    private boolean testBuild(AttackBuilder builder) {
        boolean buildSuccess = true;

        try {
            builder.build();
        } catch (IllegalArgumentException | NullPointerException e) {
            buildSuccess = false;
        }

        return buildSuccess;
    }

}
