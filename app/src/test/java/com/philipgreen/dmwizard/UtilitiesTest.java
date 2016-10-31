package com.philipgreen.dmwizard;

import com.philipgreen.dmwizard.utils.Dice;

import org.junit.Test;
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

}
