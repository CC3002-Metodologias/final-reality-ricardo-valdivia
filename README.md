Final Reality
=============

![http://creativecommons.org/licenses/by/4.0/](https://i.creativecommons.org/l/by/4.0/88x31.png)

This work is licensed under a 
[Creative Commons Attribution 4.0 International License](http://creativecommons.org/licenses/by/4.0/)

Context
-------

This project's goal is to create a (simplified) clone of _Final Fantasy_'s combat, a game developed
by [_Square Enix_](https://www.square-enix.com)
Broadly speaking for the combat the player has a group of characters to control, and a group of 
enemies controlled by the computer.

-------

This project it's have been following the Model-View-Controller pattern, and for now, it just has the model and the controller. 
It has two principal Models, one for the Characters and another one for the Weapons, where just describe his attributes, some basic turn logic, based on the weight of the weapon that the character has equipped, and the logic of attack and equipment of weapons.
The actual model was decided trying to satisfy the conditions of SOLID principles.

Description 
-------

The model for the Character implements an Interface, follow by an Abstract Class where it can be divided between enemy and player

1. The Enemy Class, which use the Abstract Class for his constructor and is specified for Enemy CharacterClass type.
2. Player Character Abstract Class, has the attributes and constructor that all the Character's Class type has, which means Knight, Engineer, Thief, Black Mage, White Mage. The attributes of this class are: Name, the character's name ; TurnsQueue, the queue with the characters waiting for their turn ; characterClass, the class of this character. This one also have a subAbstractClass for the Magicians, because they have a particular attribute which is the mana, and a concrete class for each type of character.
   
       2.1 This Abstract class have the logic of attack and equipment, and the relation with the weapons.
       
   
The model for the Weapons, implements an Interface, follow by an Abstract Class:

1. AbstractWeaponClass, this one have as attributes a name, a physical damage, speed, and it's type. Where the type can be: Sword, Axe, Bow, Knife, Staff.
2. This has five concrete subclass, one for each kind of weapon.
3. The Staff, also includes an extra attribute, which is the magic damage.

With the purpose of impose a relation between player character's and the weapons, the equipment methods use the double dispatch technique. That it's also used for the attack's method.

The Controller, is implemented like a concrete class, where it can be found all methods in order to be able to creates, knows and operate the models. The controller attributes are:
1. The inventory with the weapons that a player can equip.
2. The party's player, with de characters that he own.
3. The Queue, with the purpose of implement the logic turn.

Also, the controller use observer pattern to notify:

1. When the turn end, this always happen after a character do his attack.
2. If a player character died.
3. If an enemy died.
4. When the turns list is not empty anymore.


This pattern is accompanied by State pattern, to set all the possible all possible states where the user can be located.
The States are:

1. Start Game.
2. On Turn.
3. Select Attack Target.
4. End Turn.
5. Wait Turn.
6. Ending Wait turn.
7. End Game.

And the action of the user make the controller change of state.

-------
View
-------
the view varies in six scenes or stages, and these are:

1. Start Game, where is set the size of the parties, and then are created the player characters.
2. Player Game, here the user has to equip the weapon, set the objective and do the attack.
3. Enemy Game, where it has to set the objective and do the attack.
4. Waiting Game, here we wait for a character to enter the queue.
5. Win Game, displays a text telling the user that he win.
6. Lose Game, displays a text telling the user that he loose.

-------
How to use
----------

After run the project, a window will be open. Where the action to make will be:

1. The first action for the user will be set the party number, 
this it will be the size for both, the enemies, and the player characters.

Then, the window will display the information of the enemies that were created with random stats, and
2. The user will have to set the name on box
3. Before choose the character class and set (this process has to repeated
until the user creates the same amount of characters that was chosen at the beginning). 
   
After this, a button Start Game will appear on the upper left corner.

When the user press tha Start Game button, another stage will be showed. In this point it the user can go through 2 stages:

If the turn is the player's,

1. First the user has to choose a weapon according to the class of the current character, then set it pressing the set weapon button.
2. After this, will have to choose the enemy that the user want to attack, and set it with the Set objective button.
3. Then do the attack with the attack button.


And if the turn is the Enemy,

1. The user will have to press the set objective button, to choose random the objective of the enemy.
2. Then do the attack with the attack button.

Then just enjoy the game and try to win.



