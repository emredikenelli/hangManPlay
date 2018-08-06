import game.Move

class MoveService{
  def createMove(letter: Option[Char] , card: Option[Card], i: Option[Int]) :Move = {
    new Move(letter, card, i)
  }
}
