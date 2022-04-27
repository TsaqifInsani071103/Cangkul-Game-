package cangkulgame;
import java.util.Queue;
import java.util.LinkedList; 
import java.util.Random; 
import java.util.Scanner; 

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

  private void shuffleDeckOnTable(){
    Random randomObj = new Random(); 
    int randomNumber = randomObj.nextInt(2) + 3; 
    for (int i = 0; i< randomNumber ; i ++){
      deckOnTable.shuffleDeck(); 
    }
  } 

  public void play(){
    System.out.println(); 
    Scanner input = new Scanner(System.in); 
    shuffleDeckOnTable(); 
    playersTakeSeven(); 
    System.out.println("This is cangkul, play da game yuh");
    boolean thereIsWinner = false; 
    while(!thereIsWinner){
      playOneRound(); 
      thereIsWinner = playOneRound(); 
    }

  }

  private boolean playOneRound(){
    Scanner input = new Scanner(System.in); 
    int biggestRank = 0; 
    String tableSuit = "none";
    playerObj roundWinner = playersList.peek(); 
    for (playerObj player: playersList){
      playerObj currentPlayer = player; 
      System.out.println("It is player" + player + "'s turn");
      System.out.println("This is your hand: ");
      System.out.println(currentPlayer.displayHand());
      System.out.println("which one do you want to choose? 0-" + (currentPlayer.sizeOfHand()-1)); 
      int index = input.nextInt(); 
      while (index == -1){
        player.playerTakeCard();
        index = input.nextInt(); 
      }
      cardObj chosenCard = currentPlayer.launchCard(index); 
      while(!checkChoice(tableSuit, chosenCard.getSuit())){
        currentPlayer.playerTakeBackCard(chosenCard);
        System.out.println("The chosen card doesn't match the suit, please choose another one or retrieve another card from the deck. Press -1 to take card from deck"); 
        System.out.println(currentPlayer.displayHand());
        index = input.nextInt(); 
        while (index == -1){
          currentPlayer.playerTakeCard();
          currentPlayer.displayHand(); 
          System.out.println("which one do you want to choose? 0-" + (currentPlayer.sizeOfHand()-1));
          index = input.nextInt(); 
        }
        chosenCard = currentPlayer.launchCard(index);  
      }
      if (winGame(currentPlayer)){
        System.out.println("WIN!!!!"); 
        return true; 
      }  
      System.out.println("You chose: " + chosenCard); 
      System.out.println();
      tableSuit = chosenCard.getSuit(); 
      if (chosenCard.getRank() > biggestRank){
        roundWinner = player; 
        biggestRank = chosenCard.getRank(); 
      }
    }
    System.out.println("This round's winner is " + roundWinner);
    revolvePlayersList(roundWinner); 
    return false; 
  } 

  private boolean playersPlay(){
    for (playerObj player:playersList){
      playerObj currentPlayer = player; 
      promptPlayerChoice(currentPlayer); 
    }
  } 

  private void promptPlayerChoice(playerObj currentPlayer){
    System.out.println("It is player" + currentPlayer + "'s turn");
    System.out.println("This is your hand: ");
    System.out.println(currentPlayer.displayHand());
    System.out.println("which one do you want to choose? 0-" + (currentPlayer.sizeOfHand()-1)); 
    System.out.println("press -1 if you want to take a card from the deck"); 
  } 

  private int processPlayerInput(playerObj currentPlayer){
    Scanner input = new Scanner(System.in); 
    int index = input.nextInt(); 
    while(index == -1){
      currentPlayer.playerTakeCard(); 
      System.out.println("You took "+ currentPlayer.takenCard());
      System.out.println("This is your hand now: ");
      System.out.println(currentPlayer.displayHand()); 
      index = input.nextInt(); 
    } 
    return index; 
  } 

  private boolean checkChoice(String tableSuit, String chosenSuit){
    Scanner inputScanner = new Scanner(System.in); 
    if (!tableSuit.equals("none")){
      if (tableSuit.equals(chosenSuit)){
        return true; 
      }else{
        return false; 
      }
    }
    return true; 
  }

  private void revolvePlayersList(playerObj player){
    Queue<playerObj> queuePlaceholder = new LinkedList<playerObj>(); 
    while(playersList.peek() != player){
      queuePlaceholder.add(playersList.remove()); 
    } 
    playersList.addAll(queuePlaceholder); 
  }

  private void playersTakeSeven(){
    for (playerObj players: playersList){
      for (int i =0; i< 7; i++){
        players.playerTakeCard(); 
      }
    }
  }  

  public void getget(){
    System.out.println(numberOfPlayers);
    System.out.println(deckOnTable); 
    System.out.println(playersList.toString()); 
  } 

  private boolean winGame(playerObj player){ 
    System.out.println(player.sizeOfHand()); 
    if (player.sizeOfHand() == 0){
      return true; 
    }
    return false; 
  }
}
