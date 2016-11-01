package controllers

import play.api.mvc._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

/**
  * Created by loicmdivad on 08/10/2016.
  */
class Home extends Controller {

  /**
    * Render the home page
    * @return Play result
    */
  def index = Action { implicit request =>  Ok(views.html.home("Basalte - It Taught Right", "userA")) }

}
