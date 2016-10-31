package com.philipgreen.dmwizard;

import com.philipgreen.dmwizard.player.CharacterBuilder;
import com.philipgreen.dmwizard.player.utils.Skills;
import com.philipgreen.dmwizard.playerClasses.Barbarian;
import com.philipgreen.dmwizard.races.Dwarf;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertTrue;

/**
 * Created by pgreen on 10/30/16.
 *
 * Test for CharacterBuilder.java
 */

 //// TODO: 10/30/16 add test for backgrounds when completed

public class CharacterBuilderTest {
    private CharacterBuilder mBuilder;

    @Before
    public void init() {
        mBuilder = new CharacterBuilder();
    }

    @Test
    public void setPlayerRaceTest() {
        mBuilder.setRace(new Dwarf());
        assertTrue(mBuilder.getRace() instanceof Dwarf);
    }

    @Test
    public void setPlayerClassTest() {
        mBuilder.setClass(new Barbarian(1, new Skills[]{Skills.ATHLETICS, Skills.ANIMAL_HANDLING}));
        assertTrue(mBuilder.getPlayerClass() instanceof Barbarian);
    }

    @Test
    public void setCharacterAbilitiesTest() {
        mBuilder.setStrength(10)
                .setDexterity(11)
                .setConstitution(12)
                .setIntelligence(13)
                .setWisdom(14)
                .setCharisma(15);

        assertTrue(mBuilder.getStrength() == 10);
        assertTrue(mBuilder.getDexterity() == 11);
        assertTrue(mBuilder.getConstitution() == 12);
        assertTrue(mBuilder.getIntelligence() == 13);
        assertTrue(mBuilder.getWisdom() == 14);
        assertTrue(mBuilder.getCharisma() == 15);
    }

    @Test
    public void setSkillsTest() {
        ArrayList<Skills> skills = new ArrayList<>();
        skills.add(0, Skills.ANIMAL_HANDLING);
        skills.add(1, Skills.ATHLETICS);
        mBuilder.setSkills(skills);

        ArrayList<Skills> setSkills = mBuilder.getSkills();
        assertTrue(setSkills.get(0) == Skills.ANIMAL_HANDLING);
        assertTrue(setSkills.get(1) == Skills.ATHLETICS);
    }
}
