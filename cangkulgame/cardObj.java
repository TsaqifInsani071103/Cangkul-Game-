package cangkulgame;


public class cardObj {
  private int rank; 
  private String suit; 

  public cardObj(int rank, String suit){
    this.rank = rank;
    this.suit = suit;  
  }

  public String getSuitAndRank(){
    String suitAndRank = "" + rank + " " + suit; 
    return suitAndRank; 
  }

  public String toString(){
    String cardInStringFormat = "rank: " + rank + ", suit: " + suit; 
    return cardInStringFormat; 
  }
}
