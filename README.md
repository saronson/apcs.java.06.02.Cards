# Cards

Card games are implemented well with objects. A card can be an object with instance variables for rank and suit. A deck can be an array of card objects.  

1.  Create a well-designed Card class with proper instance variables, accessors, mutators, constructors, toString and equals methods.  
    Look at instructions in Card.java
2.  Finish the Deck class that will hold 52 card objects.  
    Look at instructions in Deck.java
3.  Finish the BlackjackHand class.  
    Look at instructions in BlackjackHand.java
4.  Write the game of Blackjack as described in Main.java.
5.  After the base game of Blackjack is working, add the ability to store a third card in BlackjackHand.  In the game, have each hand make one extra decision.  If the hand's blackjack value is less than 17, then have the hand take a "hit" (i.e. add an extra card from the deck).  You will need to add 
  ```public void addCard(Card card)``` to BlackjackHand.  Make sure the game still works properly.
