package controllers

import play.api.mvc._

/**
  * Created by loicmdivad on 08/10/2016.
  */
class Admin extends Controller{

  def admin() = {
    Ok("i hope you are a admin")
  }

}
