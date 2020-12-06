package com.github.ricardovaldivia.finalreality.model.character.player;

import com.github.ricardovaldivia.finalreality.model.weapon.IWeapon;

public interface IPlayerCharacter {
  void equip(IWeapon weapon);


  boolean isEquipped();

  IWeapon getEquippedWeapon();
}
