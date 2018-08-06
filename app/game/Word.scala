
class Word {
  val word = "eagle"
  val wordStatus = new Array[Boolean](word.length)
  val category = "animal"
  exist(' ')
  exist('-')





  def revealCategory(): Unit = println(category)

  def reveal(): Unit = println(word)

  def printWord(): Unit = {
    for(i <- 0 until word.length){
      if (wordStatus(i) == true)
        print(word.charAt(i) + " ")
      else
        print("* ")
    }
    println("")
  }


  def exist(letter: Char): Boolean = {
    var result = false
    for(i <- 0 until word.length){
      if (word.charAt(i).equals(letter)){
        wordStatus(i) = true
        result = true
      }
    }
    result
  }

  def openLetter(index: Int): Unit ={
    wordStatus(index) = true
  }

  def allRevealed(): Boolean = {
    for (revealed <- wordStatus){
      if (!revealed)
        return false
    }
    return true
  }

  def indexIsOpen(index: Int): Boolean = {
    if (wordStatus(index))
      true
    else
      false
  }
}

