package com.github.ricardovaldivia.finalreality.model.character.player;

import com.github.ricardovaldivia.finalreality.model.character.ICharacter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;


/**
 * A class that holds all the information of a single MagicianCharacter of the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 *
 */
public class MagicianPlayerCharacter extends PlayerCharacter {

    /**
     * Creates a new character.
     *
     * @param name
     * the character's name
     * @param turnsQueue
     * the queue with the characters waiting for their turn
     * @param mana
     * amount of character's mana.
     * @param characterClass
     */
    private final int mana;

    public MagicianPlayerCharacter(@NotNull String name, @NotNull BlockingQueue<ICharacter> turnsQueue, CharacterClass characterClass, final int manaCharacter) {
        super(name, turnsQueue, characterClass);
        mana = manaCharacter;
    }

    /**
     * Returns the mana of this character.
     */
    public int getMana() {
        return mana;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCharacterClass());
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MagicianPlayerCharacter)) {
            return false;
        }
        final MagicianPlayerCharacter that = (MagicianPlayerCharacter) o;
        return getCharacterClass() == that.getCharacterClass()
                && getName().equals(that.getName()) &&
                getMana() == that.getMana();
    }

}
