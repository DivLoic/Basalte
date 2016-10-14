package controllers

import play.api.mvc._

/**
  * Created by loicmdivad on 08/10/2016.
  */
class Home extends Controller {

  /**
    * Render the home page
    * @return Play result
    */
  def index = Action { Ok(views.html.home("Basalte - It Taught Right", "userA")) }

}
