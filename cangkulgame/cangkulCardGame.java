package cangkulgame;
import java.util.Queue;
import java.util.LinkedList; 
import java.util.Random; 
import java.util.Scanner; 
import java.util.ArrayList; 

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

  private void shuffleDeckMultipleTimes(){
    Random randomObj = new Random(); 
    int randomNumber = randomObj.nextInt(4) + 3; 
    for (int i = 0; i< randomNumber ; i ++){
      deckOnTable.shuffleDeck(); 
    }
  } 

  public void play(){
    System.out.println(); 
    Scanner input = new Scanner(System.in); 
    shuffleDeckMultipleTimes(); 
    playersTakeSeven(); 
    System.out.println("This is cangkul, play da game yuh");
    boolean thereIsWinner = false; 
    while(!thereIsWinner){
      thereIsWinner = playOneRound(); 
    }

  }

  private boolean playOneRound(){
    cardObj biggestRank = new cardObj(0,"placeholder"); 
    String tableSuit = "none";
    playerObj roundWinner = new playerObj(0, deckOnTable); 
    boolean gameWon = false; 
    for (playerObj player: playersList){
      playerObj currentPlayer = player; 
      if (!tableSuit.equals("none")){
        System.out.println("Card to beat is: " + biggestRank.toString()); 
      }
      System.out.println("It is player " + currentPlayer + "'s turn");
      int index = processPlayerInput(currentPlayer, tableSuit); 
      if(index == 1000){
        playerObj leastAmountOfCards = currentPlayer;
        ArrayList<playerObj> leastAmountOfCardsPlayers = new ArrayList<playerObj>(); 
        for (playerObj thisPlayer: playersList){
          if (thisPlayer.sizeOfHand() <= leastAmountOfCards.sizeOfHand()){
            leastAmountOfCards = thisPlayer; 
            leastAmountOfCardsPlayers.add(thisPlayer); 
          } 
        }
        if (leastAmountOfCardsPlayers.size() > 1){
          System.out.println("No one wins, its a tie!");
        }else{
          System.out.println("Player " + leastAmountOfCards + " wins by default since deck is" 
          + " finished and the player has the least amount of cards"); 
        }
        gameWon = true;
        break; 
      } 
      cardObj chosenCard = currentPlayer.launchCard(index); 
      chosenCard = chooseValidCard(tableSuit, chosenCard, currentPlayer); 
      if (winGame(currentPlayer)){
        System.out.println("Player " + currentPlayer + " WINS!!!!"); 
        gameWon = true; 
        break; 
      } 
      System.out.println("Player " + currentPlayer + " chose: " + chosenCard); 
      System.out.println();
      tableSuit = chosenCard.getSuit(); 
      if (chosenCard.getRank() > biggestRank.getRank()){
        roundWinner = player; 
        biggestRank = chosenCard; 
      }
    }
    if (gameWon) return true; 
    revolvePlayersList(roundWinner); 
    System.out.print("\033[H\033[2J");
    System.out.println("Player " + roundWinner + " won the round"); 
    return false; 
  } 


  private void promptPlayerChoice(playerObj currentPlayer){
    System.out.println();
    System.out.println("This is your hand: ");
    System.out.println(currentPlayer.displayHand());
    System.out.println("which one do you want to choose? 0-" + (currentPlayer.sizeOfHand()-1)); 
    System.out.println("press -1 if you want to take a card from the deck"); 
  } 

  private int processPlayerInput(playerObj currentPlayer, String tableSuit){
    promptPlayerChoice(currentPlayer); 
    Scanner input = new Scanner(System.in); 
    String index = input.nextLine(); 
    index = whileNumberIsntValid(index, currentPlayer); 
    index = whileTakeFromDeck(index, currentPlayer, tableSuit); 
    return Integer.parseInt(index); 
  } 

  private String whileNumberIsntValid(String index, playerObj currentPlayer){
    Scanner input = new Scanner(System.in);  
    while(!checkIfNumberValid(index, currentPlayer)){
      System.out.println("Please input a valid number between 0-" + (currentPlayer.sizeOfHand()-1));
      promptPlayerChoice(currentPlayer);
      index = input.nextLine(); 
    }
    return index; 
  } 

  private String whileTakeFromDeck(String index, playerObj currentPlayer, String tableSuit){
    Scanner input = new Scanner(System.in); 
    while(Integer.parseInt(index) == -1){
      if(currentPlayer.playerTakeCard(tableSuit)){
        System.out.println("You took "+ currentPlayer.takenCard());
      }
      if (winGameByDefault()){
        return "1000"; 
      }
      promptPlayerChoice(currentPlayer);
      index = input.nextLine(); 
      index = whileNumberIsntValid(index, currentPlayer); 
    } 
    return index; 
  } 

  private boolean checkIfNumberValid(String index, playerObj currentPlayer){
    try{
      if (Integer.parseInt(index) < currentPlayer.sizeOfHand()){
        return true; 
      }
    }catch(Exception e){
    }
    return false; 
  } 

  private cardObj chooseValidCard(String tableSuit, cardObj chosenCard, playerObj currentPlayer){
    cardObj validCard = chosenCard; 
    while(!checkChoice(tableSuit, validCard.getSuit())){
      currentPlayer.playerTakeBackCard(validCard);
      System.out.println(); 
      System.out.println("The chosen card doesn't match the suit, please choose another one or retrieve another card from the deck. Press -1 to take card from deck"); 
      int index = processPlayerInput(currentPlayer, tableSuit); 
      validCard = currentPlayer.launchCard(index); 
    }
    return validCard; 
  } 

  private boolean checkChoice(String tableSuit, String chosenSuit){
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
        players.initializeHand(); 
      }
    }
  }  

  private boolean winGameByDefault(){
    if (deckOnTable.deckOfCards.isEmpty()){
      return true; 
    }
    return false; 
  }

  private boolean winGame(playerObj player){ 
    if (player.sizeOfHand() == 0){
      return true; 
    }
    return false; 
  }
}
