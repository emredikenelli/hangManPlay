package controllers

import services.GameService
import javax.inject._
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.mvc._

class GameController @Inject()(cc: ControllerComponents, gs: GameService) extends AbstractController(cc) {

  case class Move(card: Option[String], letter: Option[Char], index: Option[Int])

  implicit val moveReads: Reads[Move] = (
    (JsPath \ "card").readNullable[String] and
      (JsPath \ "letter").readNullable[String].map {str =>
        if(str.isDefined)
          Some(str.get.head)
        else
          None
      } and
      (JsPath \ "index").readNullable[Int]
    )(Move.apply _)



  def play = Action(parse.json) { request =>
    val moveResult = request.body.validate[Move]
    moveResult.fold(
      errors => {
        BadRequest(Json.obj("status" ->"KO", "message" -> JsError.toJson(errors)))
      },
      move => {
        gs.makeMove(move.card, move.letter, move.index)
        Ok(Json.obj("status" ->"OK", "message" -> ("move made.") ))
      }
    )
  }
}
