package cangkulgame;

import java.util.Stack; 
import java.util.Random; 
public class cardDeck {
  //make this private again
  public Stack<cardObj> deckOfCards = new Stack<cardObj>(); 

  public cardDeck(){
    makeStackOfCards(deckOfCards); 
  }
  private void makeStackOfCards(Stack<cardObj> collectionObject){
    String[] suits = {"Diamonds", "Spades", "Clubs", "Hearts"};
    for (String suit: suits){
      addCardsToDeck(suit, collectionObject); 
    }
  } 

  private void addCardsToDeck(String suit, Stack<cardObj> collectionObject){
    int[] rank = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}; 
    for (int i: rank){
      collectionObject.add(new cardObj(i, suit));
    }
  } 

  public void shuffleDeck(){
    Random randomObject = new Random(); 
    int halfOfDeck = deckOfCards.size() / 2; 
    Stack<cardObj> deckStack = new Stack<cardObj>(); 
    Stack<cardObj> deckQueue = new Stack<cardObj>(); 
    for (int i = 0; i < halfOfDeck; i ++){
      deckQueue.add(deckOfCards.pop()); 
    }
    for(int i = 0; i < halfOfDeck; i ++){
      deckStack.add(deckOfCards.pop()); 
    }
    while(!deckQueue.isEmpty() || !deckStack.isEmpty()){ 
      int randomNumber = randomObject.nextInt(2) + 1; 
      for (int i = 0; i < randomNumber; i ++){
        if (!deckQueue.isEmpty()) deckOfCards.add(deckQueue.pop()); 
      } 
      randomNumber = randomObject.nextInt(2) + 1; 
      for(int i = 0; i< randomNumber; i ++){
        if (!deckStack.isEmpty()) deckOfCards.add(deckStack.pop()); 
      }
     }
  } 

  protected cardObj getCardFromDeck(){
    return deckOfCards.pop(); 
  }

  public String toString(){
    return deckOfCards.toString(); 
  }
}
