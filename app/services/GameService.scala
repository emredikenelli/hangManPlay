package services
import game.Game
import java.util.NoSuchElementException

import com.google.inject.Inject
import javax.inject.Singleton


@Singleton
class GameService  @Inject() (moveService: MoveService, cardService: CardService){

  val game: Game = new Game("NORMAL")



  def makeMove(card: Option[String], letter: Option[Char], index: Option[Int]): Unit = {
    try{
      if (card.isDefined) {
        val mappedCard = cardService.getCard(card.get)
        val nextMove = moveService.createMove(letter, Some(mappedCard), index)
        game.playMove(nextMove)
      }
      else{
        val nextMove = moveService.createMove(letter, None, index)
        game.playMove(nextMove)
      }

    }
    catch{
      case ex: NoSuchElementException => println("no such card")
      case ex: IllegalArgumentException => println("cant make that move")
    }
  }
}
