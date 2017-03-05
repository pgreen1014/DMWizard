package com.philipgreen.dmwizard;

import com.philipgreen.dmwizard.playerClasses.utils.PlayerClassEnum;
import com.philipgreen.dmwizard.races.utils.RaceListManager;
import com.philipgreen.dmwizard.utils.Dice;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by pgreen on 10/5/16.
 */

public class UtilitiesTest {

    // Verify that rolling single die will never be less than 0 or greater than number passed as argument
    // by rolling 100 times;
    @Test
    public void rollDieTest() throws Exception {
        for(int i = 0; i <= 99; i++) {
            int rollResult = Dice.rollDie(20);
            assertTrue(rollResult >= 1 && rollResult <= 20);
        }
    }

    // Verify that rolling single die will never be less than 0 or greater than the two number multiplied
    // by rolling 100 times;
    @Test
    public void rollDiceTest() throws Exception {
        for(int i = 0; i <= 99; i++) {
            int rollResult = Dice.rollDice(2, 6);
            assertTrue(rollResult >= 1 && rollResult <= 12);
        }
    }

    /**
     * Tests that the RaceListEnum string conversion matches the correct UI presentation strings
     *
     */
    @Test
    public void raceListEnumReturnsStringListCorrectly() {
        ArrayList<String> correctRaceListStrings = new ArrayList<>(Arrays.asList("Dwarf", "Elf", "Halfling", "Human", "Dragonborn", "Gnome", "Half Elf", "Half Orc", "Tiefling"));

        ArrayList<String> raceListEnum = RaceListManager.getRaceListForUIPresentation();
        raceListEnum.removeAll(correctRaceListStrings);

        assertTrue("RaceListEnum string conversion does not match proper UI presentation", raceListEnum.size() == 0);
    }

    /**
     * Tests that PlayerClassEnum toString() returns correct String for UI
     */
    @Test
    public void classListEnumReturnsCorrectUIStrings() {
        ArrayList<String> correctClassListStrings = new ArrayList<>(Arrays.asList("Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rogue", "Sorcerer", "Warlock", "Wizard"));

        List<String> classList = new ArrayList<>();

        for (PlayerClassEnum classEnum: PlayerClassEnum.values()) {
            classList.add(classEnum.toString());
        }

        classList.removeAll(correctClassListStrings);

        assertTrue("ClassListEnum string conversion does not match proper UI presentation", classList.size() == 0);
    }

}
