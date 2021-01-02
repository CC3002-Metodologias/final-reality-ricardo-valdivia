package com.github.ricardovaldivia.finalreality.controller;


import com.github.ricardovaldivia.finalreality.controller.states.InvalidTransitionException;
import com.github.ricardovaldivia.finalreality.controller.states.State;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class containing the tests for the State.
 *
 * @author Ignacio Slater Muñoz.
 * @author Ricardo Valdivia Orellana.
 */
public class StateTest {


  /**
   * Test that all the exception throws on the state class.
   */
  @Test
  void exceptionTest(){
    State state = new State(new Controller());
    assertThrows(InvalidTransitionException.class, state::selectAttackTarget);
    assertThrows(InvalidTransitionException.class, state::endTurn);
    assertThrows(InvalidTransitionException.class, state::endGame);
    assertThrows(InvalidTransitionException.class, state::onTurn);
    assertThrows(InvalidTransitionException.class, state::waitTurn);
    assertThrows(InvalidTransitionException.class, state::endingWait);
    }

}
