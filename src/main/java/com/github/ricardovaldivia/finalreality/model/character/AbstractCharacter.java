package com.github.ricardovaldivia.finalreality.model.character;

import com.github.ricardovaldivia.finalreality.model.character.player.CharacterClass;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import org.jetbrains.annotations.NotNull;

/**
 * An abstract class that holds the common behaviour of all the characters in the game.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public abstract class AbstractCharacter implements ICharacter {

  private final BlockingQueue<ICharacter> turnsQueue;
  private final String name;
  protected final CharacterClass characterClass;
  protected ScheduledExecutorService scheduledExecutor;

  public AbstractCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue,
      @NotNull String name, CharacterClass characterClass) {
    this.turnsQueue = turnsQueue;
    this.name = name;
    this.characterClass = characterClass;
  }


  /**
   * Adds this character to the turns queue.
   */
  public void addToQueue() {
    turnsQueue.add(this);
    scheduledExecutor.shutdown();
  }
  /**
   * Returns the name of this character.
   */
  public String getName() {
    return name;
  }

}
