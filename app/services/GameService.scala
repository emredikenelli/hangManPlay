
import java.util.NoSuchElementException
import javax.inject._
import game.


class GameService @Inject() (moveService: MoveService, cardService: CardService){

  val game: Game;



  def makeMove(card: Option[String], letter: Option[Char], index: Option[Int]): Unit = {
    try{
      if (card.isDefined){
        val mappedCard = cardService.getCard(card.get)
        val nextMove = moveService.createMove(letter, Some(mappedCard), index)
      else{
        val nextMove = moveService.createMove(letter, None, index)
      }
      game.playMove(nextMove)
    }
    catch{
      case ex: NoSuchElementException => println("no such card")
      case ex: IllegalArgumentException => println("cant make that move")
    }
  }
}
