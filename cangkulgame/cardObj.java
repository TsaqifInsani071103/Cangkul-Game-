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

  private String checkRank(int rank){
    switch(rank){
      case 11: 
        return "Jack"; 
      case 12: 
        return "Queen";
      case 13: 
        return "King";
      case 14: 
        return "Ace"; 
      default: 
        return "" + rank; 
    }
  } 

  public String toString(){
    String cardInStringFormat = "rank: " + checkRank(rank) + " suit: " + suit; 
    return cardInStringFormat; 
  }
}
