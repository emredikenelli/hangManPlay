package services
import game.Move
import game.Card
import javax.inject.Singleton

@Singleton
class MoveService{
  def createMove(letter: Option[Char] , card: Option[Card], i: Option[Int]) :Move = {
    new Move(letter, card, i)
  }
}
