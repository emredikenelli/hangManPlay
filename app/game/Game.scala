package game

class Game (difficulty: String){
  val word = new Word
  var moveList: List[Move] = Nil
  var points = 100
  reportStatus()



  val letterCosts = Map(
    'e' -> 20, 'a' -> 18, 'r' -> 16, 'i' -> 16, 'o' -> 15,
    't' -> 15, 'n' -> 15, 's' -> 14, 'l' -> 13, 'c' -> 12,
    'u' -> 11, 'd' -> 10, 'p' -> 10, 'm' -> 10, 'h' -> 10,
    'g' -> 9, 'b' -> 8, 'f' -> 8, 'y' -> 8, 'w' -> 6,
    'k' -> 6, 'v' -> 6, 'x' -> 5, 'z' -> 5, 'j' -> 5, 'q' -> 5
  )

  def moveIsPlayible(move: Move): Boolean = {
    val letterCheck = if (move.usedLetter.isDefined) {checkLetter(move.usedLetter.get)} else true
    val cardCheck = if (move.usedCard.isDefined) {checkCard(move.usedCard.get)} else true
    val indexCheck = if (move.index.isDefined) {checkIndex(move.index.get)} else true

    letterCheck && cardCheck && indexCheck
  }


  def checkIndex(index: Int): Boolean = {
    if (index > 0){
      if (index >= word.word.length){
        println("out of bounds")
        return false
      }
      else if (word.indexIsOpen(index)){
        println("that place is already revealed")
        return false
      }
    }
    true
  }




  def checkLetter(letter: Char): Boolean = {

    if (!('a' <= letter && letter <= 'z')){
      println("unknown letter has entered")
      return false
    }

    for (move <- moveList){
      if (move.usedLetter.isDefined){
        if (letter.equals(move.usedLetter.get)){
          println("letter is already used")
          return false
        }
      }
    }
    true
  }




  def checkCard(card: Card): Boolean = {
    if (card.getCount() < 1){
      println("you can not use that card anymore")
      return false
    }
    if (card.cost > points){
      println("not enough points for the card")
      return false
    }

    if (moveList.nonEmpty){
      if (moveList.head.calculateDiscountForNextMove() != 0)
        if (card.guessDiscount != 0 || !card.makesGuess){
          println("you have a discount for this move use it wisely")
          return false
        }

    }
    true
  }



  def reportStatus(): Unit = {
    word.printWord()
    println("points: " + points)
    if(finished()){
      if(word.allRevealed())
        println("you won!!")
      else{
        print("you have lost secret word was: ")
        word.reveal()
      }
    }

  }




  def playMove(move: Move): Unit = {
    if(!finished()){
      if(moveIsPlayible(move)){
        moveList = move :: moveList
        applyMove()
        reportStatus()
      }
      else
        println("move is unplayible")
    }
    else
      println("oyun bitmis")
  }





  def guess(discount: Double): Unit = {
    if (moveList.head.usedLetter.isDefined){
      if(word.exist(moveList.head.usedLetter.get)){
        moveList.head.setResult(true)
        println("you found one!!")
      }
      else{
        val pointDecrease: Int = (letterCosts(moveList.head.usedLetter.get) * (1 - discount)).toInt
        points = points - pointDecrease
        moveList.head.setResult(false)
        println("secret word does not include that letter")
      }
    }
  }


  def applyMove(): Unit = {
    if (moveList.head.usedCard.isDefined){
      val currentCard = moveList.head.usedCard.get
      currentCard.play()
      points = points - currentCard.cost

      if(currentCard.revealsCategory){
        word.revealCategory()
      }
      if (currentCard.opensLetter){
        word.openLetter(moveList.head.index.get)
      }
      if (currentCard.makesGuess){
        if (currentCard.guessDiscount != 0){
          guess(currentCard.guessDiscount)
        }
        else{
          if(moveList.length > 1)
            guess(moveList(1).calculateDiscountForNextMove())
          else
            guess(0)
        }
      }
    }
    else{
      if(moveList.length > 1)
        guess(moveList(1).calculateDiscountForNextMove())
      else
        guess(0)
    }
  }

  def finished(): Boolean = {
    if(word.allRevealed() || points < 0)
      true
    else
      false
  }



}

