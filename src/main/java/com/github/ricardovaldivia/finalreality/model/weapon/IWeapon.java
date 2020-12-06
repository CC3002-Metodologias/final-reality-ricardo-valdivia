package com.github.ricardovaldivia.finalreality.model.weapon;


import com.github.ricardovaldivia.finalreality.model.character.player.classes.*;

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

  IWeapon equippedByBlackMage(BlackMage blackMage);

  IWeapon equippedByWhiteMage(WhiteMage whiteMage);

  IWeapon equippedByEngineer(Engineer engineer);

  IWeapon equippedByThief(Thief thief);

  IWeapon equippedByKnight(Knight knight);
}
