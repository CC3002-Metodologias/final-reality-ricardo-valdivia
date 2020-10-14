package com.github.ricardovaldivia.finalreality.model.character;


import com.github.ricardovaldivia.finalreality.model.character.player.CharacterClass;
import com.github.ricardovaldivia.finalreality.model.character.player.MagicianPlayerCharacter;
import com.github.ricardovaldivia.finalreality.model.character.player.PlayerCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.*;




class MagicianPlayerCharacterTest extends PlayerCharacterTest {
    private static final String BLACK_MAGE_NAME = "Vivi";
    private static final String WHITE_MAGE_NAME = "Eiko";
    private Map<CharacterClass, String> characterNames;


    @BeforeEach
    void setUp() {
        super.basicSetUp();
        characterNames = new EnumMap<>(CharacterClass.class);
        characterNames.put(CharacterClass.BLACK_MAGE, BLACK_MAGE_NAME);
        characterNames.put(CharacterClass.WHITE_MAGE, WHITE_MAGE_NAME);
        for (var characterClass :
                characterNames.keySet()) {
            testCharacters.add(
                    new MagicianPlayerCharacter(characterNames.get(characterClass), turns, characterClass,50));
        }
    }

    @Test
    void constructorTest() {
        var enemy = new Enemy("Enemy", 10, turns);
        var expectedBlackMagician = new MagicianPlayerCharacter(BLACK_MAGE_NAME, turns,CharacterClass.BLACK_MAGE,50);
        var OtherBlackMagicianName = new MagicianPlayerCharacter(WHITE_MAGE_NAME, turns,CharacterClass.BLACK_MAGE,50);
        var OtherBlackMagicianClass = new MagicianPlayerCharacter(BLACK_MAGE_NAME, turns,CharacterClass.WHITE_MAGE,50);
        var OtherBlackMagicianMana = new MagicianPlayerCharacter(BLACK_MAGE_NAME, turns,CharacterClass.BLACK_MAGE,80);

        for (var character :
                testCharacters) {
            var characterClass = character.getCharacterClass();
            var characterName = characterNames.get(characterClass);
            checkConstruction(new MagicianPlayerCharacter(characterName, turns, characterClass, 50),
                    character,
                    new PlayerCharacter("Test", turns, characterClass),
                    new PlayerCharacter(characterName, turns,
                            characterClass == CharacterClass.THIEF ? CharacterClass.BLACK_MAGE
                                    : CharacterClass.THIEF));
            assertNotEquals(character, enemy);
        }

        assertNotEquals(expectedBlackMagician, OtherBlackMagicianName);
        assertNotEquals(expectedBlackMagician, OtherBlackMagicianClass);
        assertNotEquals(expectedBlackMagician, OtherBlackMagicianMana);

    }

    @Test
    void getManaTest(){
        var BLACK_MAGE = new MagicianPlayerCharacter(BLACK_MAGE_NAME, turns,CharacterClass.BLACK_MAGE, 50);
            assertEquals(50,BLACK_MAGE.getMana());
        var WHITE_MAGE = new MagicianPlayerCharacter(WHITE_MAGE_NAME, turns,CharacterClass.WHITE_MAGE, 100);
        assertEquals(100,WHITE_MAGE.getMana());
        }
}


