Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control and a group of 
enemies controlled by the computer.

-------

This project it's have been following the Model-View-Controller pattern, and for now, it just have the model and the controller. 
It have two principal Models, one for the Characters and another one for the Weapons, where just describe his atributtes, some basic turn logic, based on the weight of the weapon that the character has equipped and the logic of attack and equipment of weapons.
The actual model was decided trying to satisfy the conditions of SOLID principles.

Description 
-------

The model for the Character implements a Interface, follow by an Abstract Class were it can be divided between enemy and player

1. The Enemy Class, which use the Abstract Clas for his constructor and is specified for Enemy CharacterClass type.
2. Player Character Abstract Class, has the attributes and contructor that all the Character's Class type has, which means Knight, Engineer, Thief,Black Mage, White Mage. The attributtes of this class are: Name, the character's name ; TurnsQueue, the queue with the characters waiting for their turn ; characterClass, the class of this character. This one also have a subAbstractClass for the Magicians, because they have a particule atribbute which is the mana, and a concrete class for each type of character.
 2.1 This Abstract class have the lofic of attack and equipment, and the relation with the weapons.

The model for the Weapons, implements a Interface, follow by an Abstract Class:

1. AbstractWeaponClass, this one have as atributtes a name, a physical damage, speed and it's type. Where the type can be: Sword, Axe, Bow, Knife, Staff.
2. This have five concret subclass, one for each kind of weapon.
3. The Staff, also includes an extra atributte, which is the magic damage.

With the purpose of impose a relation between player character's and the weapons, the equipment methods use the double dispatch technique. That it's also used for the attack's method.

The Controller, is implemented like a concret class, where it can be find all methods in order to be able to creates, knows and operate the models. The controller atributtes are:
1. The inventory with the weaponds that a player can equip.
2. The party's player, with de characters that he own.
3. The Queue, with the purpose of implement the logic turn.

