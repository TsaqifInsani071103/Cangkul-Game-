import cangkulgame.cangkulCardGame;
public class mainGame{
  public static void main(String[] args){
    System.out.println("this is the main game homie"); 
    cangkulCardGame game = new cangkulCardGame(4);
    game.printNumberOfPlayers();
  }

}