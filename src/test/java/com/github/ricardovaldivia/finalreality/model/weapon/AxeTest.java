package com.github.ricardovaldivia.finalreality.model.weapon;

import com.github.ricardovaldivia.finalreality.model.character.player.classes.BlackMage;
import com.github.ricardovaldivia.finalreality.model.character.player.classes.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

class AxeTest extends AbstractWeaponTest{
  private static final String AXE_NAME = "Axe";
  private int weight;
  private int physicalDamage;
  private Axe testAxeWeapon;

  @BeforeEach
  void setUp() {
    var seed =  new Random().nextInt();
    super.basicSetUp(seed);
    physicalDamage = r.nextInt(50);
    weight = r.nextInt(30);
    testAxeWeapon = new Axe(AXE_NAME, physicalDamage, weight);
  }

  /**
   * Checks that the class' constructor and equals method works properly.
   */
  @RepeatedTest(600)
  void constructorTest(){
    checkConstruction(new Axe(AXE_NAME, physicalDamage, weight),
        testAxeWeapon,
        new Axe("Axe2", r.nextInt(50), r.nextInt(30)),
        new Bow("Bow", r.nextInt(50), r.nextInt(30)),
        new Axe("Axe3", physicalDamage, weight),
        new Axe(AXE_NAME, r.nextInt(50) + 51, weight),
        new Axe(AXE_NAME, physicalDamage, r.nextInt(30) + 31));
  }

}