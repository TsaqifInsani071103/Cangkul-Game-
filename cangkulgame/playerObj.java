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

  protected void playerTakeBackCard(cardObj card){
    playerHand.add(card); 
  }

  protected String displayHand(){
    return playerHand.toString(); 
  } 

  protected int sizeOfHand(){
    return playerHand.size(); 
  }

  protected String takenCard(){
    return playerHand.get(playerHand.size()-1).toString(); 
  }

  protected cardObj launchCard(int index){
    return playerHand.remove(index); 
  } 

  public String toString(){
    return "" + playerNumber; 
  }



}
