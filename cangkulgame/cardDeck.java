package cangkulgame;

import java.util.ArrayList; 
public class cardDeck {
  private ArrayList<cardObj> deckOfCards = new ArrayList<cardObj>(); 

  public cardDeck(){
    String[] suits = {"Diamonds", "Spades", "Clubs", "Hearts"};
    for (String suit: suits){
      addCardsToDeck(suit); 
    }
  }

  private void addCardsToDeck(String suit){
    int[] rank = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14}; 
    for (int i: rank){
      deckOfCards.add(new cardObj(i, suit));
    }
  } 
}
