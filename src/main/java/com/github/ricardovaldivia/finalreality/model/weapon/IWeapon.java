package com.github.ricardovaldivia.finalreality.model.weapon;


import com.github.ricardovaldivia.finalreality.model.character.player.classes.*;

import java.util.HashMap;

public interface IWeapon {

  /**
   * Returns the name of this weapon.
   */
  String getName();

  /**
   * Returns the physical damage of this weapon.
   */
  int getPhysicalDamage();

  /**
   * Returns the weigh of this weapon.
   */
  int getWeight();

  /**
   * Returns the weapon that will be equipped by a BlackMage.
   */
  IWeapon equippedByBlackMage(BlackMage blackMage);

  /**
   * Returns the weapon that will be equipped by a WhiteMage.
   */
  IWeapon equippedByWhiteMage(WhiteMage whiteMage);

  /**
   * Returns the weapon that will be equipped by an Engineer.
   */
  IWeapon equippedByEngineer(Engineer engineer);

  /**
   * Returns the weapon that will be equipped by a Thief.
   */
  IWeapon equippedByThief(Thief thief);

  /**
   * Returns the weapon that will be equipped by a Knight.
   */
  IWeapon equippedByKnight(Knight knight);

  /**
   * Returns the current status of the weapon
   */
  HashMap<String, String> getCurrentInfo();
}
