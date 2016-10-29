package com.philipgreen.dmwizard;

import com.philipgreen.dmwizard.battle.Attack;
import com.philipgreen.dmwizard.battle.AttackBuilder;
import com.philipgreen.dmwizard.battle.damageRolls.DamageRollRegular;
import com.philipgreen.dmwizard.battle.damageRolls.DamageRollVersatile;
import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.data.Skills;
import com.philipgreen.dmwizard.playerClasses.Barbarian;
import com.philipgreen.dmwizard.races.Dwarf;
import com.philipgreen.dmwizard.weapons.Dagger;
import com.philipgreen.dmwizard.weapons.Dart;
import com.philipgreen.dmwizard.weapons.Glaive;
import com.philipgreen.dmwizard.weapons.LightHammer;
import com.philipgreen.dmwizard.weapons.Quarterstaff;
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
    private Attack mAttack;

    @Before
    public void initialize() {
        mAttackBuilder = new AttackBuilder();

        Barbarian barb = new Barbarian(1, new Skills[]{Skills.ATHLETICS, Skills.ANIMAL_HANDLING});
        Dwarf dwarf = new Dwarf();
        mAttacker = new PlayerCharacter(barb, dwarf, 18, 15, 16, 13, 12, 14);
        mDefender = new PlayerCharacter(barb, dwarf, 18, 15, 14, 12, 14, 18);
    }

    //// TODO: 10/28/16 add test for Heavy weapons when small race is complete

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
    public void testFinesseWeapon() {
        // test strength modifier
        mAttackBuilder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setMeleeAttack()
                .setPlayerDistance(5)
                .setAttackingWeapon(new Dagger())
                .setMeleeAttack()
                .setAttackModifierStrength();
        assertTrue("finesse weapon should be able to use strength as modifier", testBuild(mAttackBuilder));
        assertTrue(mAttack.getAttackModifierStat() == BaseStats.STRENGTH);

        // test dex modifier with same weapon
        mAttackBuilder.setAttackModifierDexterity();
        assertTrue("finesse weapon should be able to use dexterity as modifier", testBuild(mAttackBuilder));
        assertTrue(mAttack.getAttackModifierStat() == BaseStats.DEXTERITY);
    }

    @Test
    public void testRange() {
        // test normal range
        mAttackBuilder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setRangedAttack()
                .setAttackingWeapon(new ShortBow())
                .setPlayerDistance(80);
        assertTrue(testBuild(mAttackBuilder));
        assertFalse("weapon within normal range should not have disadvantage",mAttack.isDisadvantage());
        assertFalse("weapon within normal range should not have advantage", mAttack.isAdvantage());

        mAttackBuilder.setPlayerDistance(75);
        assertTrue(testBuild(mAttackBuilder));
        assertFalse("weapon within normal range should not have disadvantage",mAttack.isDisadvantage());
        assertFalse("weapon within normal range should not have advantage", mAttack.isAdvantage());

        // test within melee range
        mAttackBuilder.setPlayerDistance(5);
        assertTrue(testBuild(mAttackBuilder));
        assertTrue("ranged weapon within melee range should have disadvantage",mAttack.isDisadvantage());
        assertFalse("ranged weapon within melee range should not have advantage", mAttack.isAdvantage());

        // test long range
        mAttackBuilder.setPlayerDistance(85);
        assertTrue(testBuild(mAttackBuilder));
        assertTrue("ranged weapon within long range should have disadvantage",mAttack.isDisadvantage());
        assertFalse("ranged weapon within long range should not have advantage", mAttack.isAdvantage());

        mAttackBuilder.setPlayerDistance(320);
        assertTrue(testBuild(mAttackBuilder));
        assertTrue("ranged weapon within long range should have disadvantage",mAttack.isDisadvantage());
        assertFalse("ranged weapon within long range should not have advantage", mAttack.isAdvantage());

        mAttackBuilder.setPlayerDistance(325);
        assertFalse("ranged weapon outside long range should fail", testBuild(mAttackBuilder));
    }

    @Test
    public void testReachWeapon() {
        mAttackBuilder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setMeleeAttack()
                .setAttackingWeapon(new Glaive())
                .setPlayerDistance(10);
        assertTrue("Reach weapon should be able to hit within 10 feet", testBuild(mAttackBuilder));

        mAttackBuilder.setPlayerDistance(5);
        assertTrue("Reach weapon should be able to hit within 5 feet", testBuild(mAttackBuilder));

        mAttackBuilder.setPlayerDistance(15);
        assertFalse("Reach weapon should not be able to hit above 10 feet range", testBuild(mAttackBuilder));
    }

    @Test
    public void testTwoHandedWeapon() {
        mAttackBuilder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setMeleeAttack()
                .setAttackingWeapon(new Glaive())
                .setPlayerDistance(5);
        assertTrue(testBuild(mAttackBuilder));
        assertTrue(mAttack.isTwoHandedAttack());
        //// TODO: 10/29/16 test that off hand weapon can't be set when two-handed weapon code is complete
    }

    @Test
    public void testVersatileWeapon() {
        mAttackBuilder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setMeleeAttack()
                .setAttackingWeapon(new Quarterstaff())
                .setPlayerDistance(5);
        assertTrue(testBuild(mAttackBuilder));
        assertTrue(mAttack.getDamageRollBehavior() instanceof DamageRollRegular);

        mAttackBuilder.setTwoHandedAttack();
        assertTrue(testBuild(mAttackBuilder));
        assertTrue(mAttack.getDamageRollBehavior() instanceof DamageRollVersatile);
    }

    @Test
    public void testThrownAttack() {
        // test normal range
        mAttackBuilder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setAttackingWeapon(new LightHammer())
                .setThrownAttack()
                .setPlayerDistance(20);
        assertTrue(testBuild(mAttackBuilder));
        assertFalse(mAttack.isAdvantage());
        assertFalse(mAttack.isDisadvantage());
        assertTrue("thrown attack modifier should be the same as its regular attack", mAttack.getAttackModifierStat() == BaseStats.STRENGTH);

        // Test long range
        mAttackBuilder.setPlayerDistance(25);
        assertTrue(testBuild(mAttackBuilder));
        assertFalse(mAttack.isAdvantage());
        assertTrue("thrown attack in long range should have disadvantage", mAttack.isDisadvantage());

        mAttackBuilder.setPlayerDistance(60);
        assertTrue(testBuild(mAttackBuilder));
        assertFalse(mAttack.isAdvantage());
        assertTrue("thrown attack in long range should have disadvantage", mAttack.isDisadvantage());

        // Test melee range
        mAttackBuilder.setPlayerDistance(5);
        assertTrue(testBuild(mAttackBuilder));
        assertFalse(mAttack.isAdvantage());
        assertTrue("thrown attack in melee range should have disadvantage", mAttack.isDisadvantage());

        // Test out of range
        mAttackBuilder.setPlayerDistance(65);
        assertFalse("thrown attack out of range should not build", testBuild(mAttackBuilder));

        // Test attack modifier with non-melee throwable which should be Dexterity
        AttackBuilder builder = new AttackBuilder();
        builder
                .setAttacker(mAttacker)
                .setDefender(mDefender)
                .setAttackingWeapon(new Dart())
                .setThrownAttack()
                .setPlayerDistance(20);
        assertTrue(testBuild(builder));
        assertTrue("thrown attack modifier should be the same as its regular attack", mAttack.getAttackModifierStat() == BaseStats.DEXTERITY);
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
            mAttack = builder.build();
        } catch (IllegalArgumentException | NullPointerException e) {
            buildSuccess = false;
        }

        return buildSuccess;
    }

}
