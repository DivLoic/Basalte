package controllers

import play.api.Logger
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import DataForm.LoginData
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

class Application extends Controller {

  val loginForm: Form[LoginData] = Form(
    mapping(
      "login" -> email,
      "password" -> nonEmptyText(minLength=6),
      "remember" -> boolean
    )(LoginData.apply)(LoginData.unapply)
  )

  /*def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }*/

  /**
    * Render the login form
    * @return Play result
    */
  def login = Action {

    Ok(views.html.loginform(loginForm))

  }


  def connection = Action { implicit request =>

    loginForm.bindFromRequest.fold(
      error => {
        Logger error s"Invalid login-form for: ${error.data("login")}"
        BadRequest(views.html.loginform(loginForm))
      },
      data => {
        Logger info s"the user email : ${data.login}"
        Logger info s"the user passw : ${data.password}"
        Logger info s"the user keepflag : ${data.remember}"

        Redirect(routes.Home.index()).withSession(
          "user" -> data.login, "connected" -> "true"
        )
      }
    )
  }

}