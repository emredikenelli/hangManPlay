package game

import scala.io.Source

class Word (){
  val category = pickCategory()
  val word = getWord(category)
  val wordStatus = new Array[Boolean](word.length)
  exist(' ')
  exist('-')





  def revealCategory(): Unit = println(category)

  def reveal(): Unit = println(word)

  def printWord(): Unit = {
    for(i <- 0 until word.length){
      if (wordStatus(i))
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
    true
  }

  def indexIsOpen(index: Int): Boolean = {
    if (wordStatus(index))
      true
    else
      false
  }

  def getWord(cat: String): String = {
    val bufferedSource = Source.fromFile("/Users/emredikenelli/Downloads/hangman_words/" + cat + ".txt")
    val wrd = bufferedSource.getLines().next()
    bufferedSource.close
    wrd
  }

  def pickCategory(): String = {
    val r = scala.util.Random
    val randomNumber = ((r.nextFloat()) * 9).toInt
    randomNumber match {
      case 0 => "animals"
      case 1 => "body_parts"
      case 2 => "capitals"
      case 3 => "colours"
      case 4 => "health"
      case 5 => "plants"
      case 6 => "professions"
      case 7 => "sports"
      case _ => "weather"
    }

  }


}
