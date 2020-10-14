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

This project it's have been following the Model-View-Controller pattern, and for now, it just have the model. 
It have two principal Models, one for the Characters and another one for the Weapons, where just describe his atributtes, and some basic turn logic, based on the weight of the weapon that the character has equipped.
The actual model was decided trying to satisfy the conditions of SOLID principles.

Description 
-------

The model for the Character implements a Interface, follow by an Abstract Class which one has two subclasses:

1. The Enemy Class, which use the Abstract Clas for his constructor and is specified for Enemy CharacterClass type
2. Player Character, has the attributes and contructor that all the Character's Class type has, which means Knight, Engineer, Thief,Black Mage, White Mage. The attributtes of this class are: Name, the character's name ; TurnsQueue, the queue with the characters waiting for their turn ; characterClass, the class of this character. This one also have a subclass for the Magicians, because they have a particule atribbute which is the mana.

The model for the Weapons, just have 2 Classes:

1. WeaponClass, this one have as atributtes a name, a physical damage, speed and it's type. Where the type can be: Sword, Axe, Bow, Knife.
2. MixWeaponCLass, this one have as atributtes the same as WeaponClass plus the magic damage, and the type of this weapon only can be Staff.

