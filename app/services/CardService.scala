package services
import game.Card
import javax.inject.Singleton

@Singleton
class CardService{

  val buyALetterCard = new game.Card("BUY_A_LETTER", 20, 1, false, true,
    false,0, 0, false)
  val discountCard = new Card("DISCOUNT", 5, 2, false, false,
    true, 0.75,  0, false)
  val riskCard = new Card("RISK", 8, 2, false, false,
    true, 0, 1, true)
  val consolationCard = new Card("CONSOLATION", 5, 1, false, false,
    true, 0, 0.5, false)
  val categoryCard = new Card("CATEGORY", 5, 1, true, false,
    false, 0, 0, false)
  val noCard = new Card("NO_CARD", 0, 30, false, false,
    true, 0, 0, false)

  val cardNames = Map(
    "BUY_A_LETTER" -> buyALetterCard, "DISCOUNT" -> discountCard, "RISK" -> riskCard,
    "CONSOLATION" -> consolationCard, "CATEGORY" -> categoryCard, "NO_CARD" -> noCard)

  def getCard(str: String) :Card = {
    cardNames(str)
  }


}
