package game

class Move (letter: Option[Char] , card: Option[Card], i: Option[Int]){

  require(isValid())

  private var succeeded: Boolean = _

  val usedLetter = letter

  val usedCard = card

  val index = i

  def setResult(res: Boolean) = succeeded = res

  def isValid(): Boolean = {
    if(card.isDefined){
      if (card.get.makesGuess && !letter.isDefined)
        false
      else if(card.get.opensLetter && !i.isDefined)
        false
      else if (!card.get.makesGuess && letter.isDefined)
        false
      else
        true
    }
    else if (i.isDefined)
      false
    else if (!letter.isDefined)
      false
    else
      true

  }


  def calculateDiscountForNextMove(): Double = {
    if (!card.isDefined)
      0
    else if (card.get.nextGuessCondition == succeeded)
      card.get.nextGuessDiscount
    else
      0
  }


}
