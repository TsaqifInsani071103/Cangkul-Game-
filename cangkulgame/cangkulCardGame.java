package cangkulgame;
import java.util.Queue;
import java.util.LinkedList; 

public class cangkulCardGame {
  private int numberOfPlayers = 1; 
  private cardDeck deckOnTable; 
  private Queue<playerObj> playersList = new LinkedList<playerObj>(); 

  public cangkulCardGame(int numberOfPlayers){
    this.numberOfPlayers = numberOfPlayers; 
    this.deckOnTable = new cardDeck(); 
    addPlayersToList(); 
  }

  private void addPlayersToList(){
    for (int i = 1; i <= numberOfPlayers; i++){
      playersList.add(new playerObj(i, deckOnTable));  
    }
  }

  public void getget(){
    System.out.println(numberOfPlayers);
    System.out.println(deckOnTable); 
    System.out.println(playersList.toString()); 
  } 
}
