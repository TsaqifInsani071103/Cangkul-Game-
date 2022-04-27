package cangkulgame;


public class cardObj {
  private int rank; 
  private String suit; 

  public cardObj(int rank, String suit){
    this.rank = rank;
    this.suit = suit;  
  }

  public String getSuit(){
    return suit; 
  }

  //this one for testign 
  public int getRank(){
    return rank; 
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
    String cardInStringFormat = checkRank(rank) + " of " + suit; 
    return cardInStringFormat; 
  }
}
