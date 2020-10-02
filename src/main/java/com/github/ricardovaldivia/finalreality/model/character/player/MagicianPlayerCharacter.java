package com.github.ricardovaldivia.finalreality.model.character.player;

import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.BlockingQueue;

public class MagicianPlayerCharacter extends PlayerCharacter{

    /**
     * Creates a new character.
     *
     * @param name
     *          the character's name
     * @param turnsQueue
     *            the queue with the characters waiting for their turn
     * @param mana
     *             amount of character's mana.
     * @param characterClass
     */
    private final int mana;

    public MagicianPlayerCharacter (@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue, CharacterClass characterClass,final int manaCharacter) {
        super(name, turnsQueue, characterClass);
        mana = manaCharacter;
    }
}
