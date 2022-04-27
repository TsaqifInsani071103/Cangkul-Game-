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

  protected void initializeHand(){
    playerHand.add(deckOnTable.getCardFromDeck());
  }

  protected boolean playerTakeCard(String suit){
    if (!deckOnTable.deckOfCards.isEmpty()){
      if (suit.equals("none")){
        System.out.println("You can't draw in the beginning of the round");
        return false;
      }else if (weHaveSuit(suit)){
        System.out.println("you have the card with the suit, you can't draw more");
        return false;
      }else{
        playerHand.add(deckOnTable.getCardFromDeck()); 
        return true; 
      }
    }else{
      System.out.println("You can no longer draw from the deck since it's finished");
      return false; 
    }
  }

  private boolean weHaveSuit(String suit){
    ArrayList<String> listOfSuits = new ArrayList<String>();
    for (cardObj card: playerHand){
      listOfSuits.add(card.getSuit());
    }
    if (listOfSuits.contains(suit)){
      return true; 
    }
    return false; 
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
