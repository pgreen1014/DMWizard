package com.philipgreen.dmwizard;

import com.philipgreen.dmwizard.player.CharacterBuilder;
import com.philipgreen.dmwizard.races.Dwarf;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by pgreen on 10/30/16.
 *
 * Test for CharacterBuilder.java
 */

public class CharacterBuilderTest {
    CharacterBuilder mBuilder = new CharacterBuilder();

    @Test
    public void setPlayerRaceTest() {
        mBuilder.setRace(new Dwarf());
        assertTrue(mBuilder.getRace() instanceof Dwarf);
    }
}
