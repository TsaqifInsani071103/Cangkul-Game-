package cangkulgame;

import java.util.ArrayList; 
public class playerObj {
  private ArrayList<cardObj> playerHand = new ArrayList<cardObj>();  
  private cardDeck deckOnTable;
  private int playerNumber; 

  public playerObj(int playerNumber, cardDeck deckOfCards){
    this.playerNumber = playerNumber; 
    this.deckOnTable = deckOfCards; 
  } 

  protected void playerTakeCard(){
    playerHand.add(deckOnTable.getCardFromDeck()); 
  }

  public String toString(){
    return "" + playerNumber; 
  }



}
