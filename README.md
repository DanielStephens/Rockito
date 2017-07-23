# Rockito
A wrapper project for Mockito to ease the process of refactoring. This is currently a naive approach and can only be used to refactor methods by treating them as a 'black box' which we have no information about.

It does however aid in checking nothing has been broken, by easing the process of testing multiple possible inputs and mapping their outcomes. I have used this to refactor a class of 20 complexity, by writing the equivalent of a map of all 3 million input combinations and their outputs. This provides safety and reduces the risk of this type of work.