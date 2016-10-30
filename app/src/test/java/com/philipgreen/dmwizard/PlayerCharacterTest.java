package com.philipgreen.dmwizard;

import com.philipgreen.dmwizard.data.BaseStats;
import com.philipgreen.dmwizard.player.utils.Skills;
import com.philipgreen.dmwizard.player.PlayerCharacter;
import com.philipgreen.dmwizard.player.utils.AbilityModifierManager;
import com.philipgreen.dmwizard.playerClasses.Barbarian;
import com.philipgreen.dmwizard.races.Dwarf;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by pgreen on 10/29/16.
 */

public class PlayerCharacterTest {
    PlayerCharacter mPlayerCharacter;

    @Before
    public void initializeCharacter() {
        Barbarian barb = new Barbarian(1, new Skills[]{Skills.ATHLETICS, Skills.ANIMAL_HANDLING});
        Dwarf dwarf = new Dwarf();
        mPlayerCharacter = new PlayerCharacter(barb, dwarf, 18, 15, 16, 13, 12, 14);
    }

    @Test
    public void abilityModifierTest() {
        assertTrue(mPlayerCharacter.getStrengthModifier() == AbilityModifierManager.getModifier(18));
        assertTrue(mPlayerCharacter.getDexterityModifier() == AbilityModifierManager.getModifier(15));
        // Constitution is 18 because being a dwarf adds +2 to constitution
        assertTrue(mPlayerCharacter.getConstitutionModifier() == AbilityModifierManager.getModifier(18));
        assertTrue(mPlayerCharacter.getIntelligenceModifier() == AbilityModifierManager.getModifier(13));
        assertTrue(mPlayerCharacter.getWisdomModifier() == AbilityModifierManager.getModifier(12));
        assertTrue(mPlayerCharacter.getCharismaModifier() == AbilityModifierManager.getModifier(14));

        assertTrue(mPlayerCharacter.getAbilityModifier(BaseStats.STRENGTH)
                == mPlayerCharacter.getStrengthModifier());

        assertTrue(mPlayerCharacter.getAbilityModifier(BaseStats.DEXTERITY)
                == mPlayerCharacter.getDexterityModifier());

        assertTrue(mPlayerCharacter.getAbilityModifier(BaseStats.CONSTITUTION)
                == mPlayerCharacter.getConstitutionModifier());

        assertTrue(mPlayerCharacter.getAbilityModifier(BaseStats.INTELLIGENCE)
                == mPlayerCharacter.getIntelligenceModifier());

        assertTrue(mPlayerCharacter.getAbilityModifier(BaseStats.WISDOM)
                == mPlayerCharacter.getWisdomModifier());

        assertTrue(mPlayerCharacter.getAbilityModifier(BaseStats.CHARISMA)
                == mPlayerCharacter.getCharismaModifier());

    }
}
