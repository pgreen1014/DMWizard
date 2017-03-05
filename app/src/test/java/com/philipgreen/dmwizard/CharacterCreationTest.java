package com.philipgreen.dmwizard;

import android.content.Context;

import com.philipgreen.dmwizard.races.utils.RaceListManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by pgreen on 3/5/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class CharacterCreationTest {

    private static final String FAKE_STRING = "HELLO WORLD";

    @Mock
    Context mMockContext;

//    @Test
//    public void readStringFromContext_LocalizedString() {
        // Given a mocked Context injected into the object under test...
//        when(mMockContext.getString(R.string.hello_word))
//                .thenReturn(FAKE_STRING);


//    }

    /**
     * Tests that the RaceListEnum string conversion matches the correct UI presentation strings
     */
    @Test
    public void raceListEnumReturnsStringListCorrectly() {
        ArrayList<String> raceListStrings = new ArrayList<>(Arrays.asList("Dwarf", "Elf", "Halfling", "Human", "Dragonborn", "Gnome", "Half Elf", "Half Orc", "Tiefling"));

        ArrayList<String> raceListEnum = RaceListManager.getRaceListForUIPresentation();
        raceListEnum.removeAll(raceListStrings);

        assertTrue("RaceListEnum string conversion does not match proper UI presentation", raceListEnum.size() == 0);
    }
}
