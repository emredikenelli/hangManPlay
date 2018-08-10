package game

class Card (cType: String, cardCost: Int, var cardCount: Int, cardRevealsCategory: Boolean, cardOpensLetter: Boolean,
            cardMakesGuess: Boolean, cardGuessDiscount: Double,
            cardNextGuessDiscount: Double, cardNextGuessCondition: Boolean){

  def play(): Unit = {cardCount = cardCount - 1}
  def getCount() = cardCount
  val cardType = cType
  val cost = cardCost
  val revealsCategory = cardRevealsCategory
  val opensLetter = cardOpensLetter
  val makesGuess = cardMakesGuess
  val guessDiscount = cardGuessDiscount
  val nextGuessDiscount = cardNextGuessDiscount
  val nextGuessCondition = cardNextGuessCondition

}
