package controllers

import play.api.Logger
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._
import DataForm.LoginData
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

class Application extends Controller {

  val loginForm = Form(
    mapping(
      "login" -> email,
      "password" -> nonEmptyText(minLength=6),
      "remember" -> boolean
    )(LoginData.apply)(LoginData.unapply)
  )

  /**
    * The welcome page
    * @return
    */
  def home = Action { implicit request => Ok(
    views.html.template("Basalte - It Taught Right")
    (views.html.home())
  )}

  /**
    * Render the login form
    * @return Play result
    */
  def login = Action { implicit request => Ok(
      views.html.template("Basalte - Login page")
      (views.html.loginform(loginForm))
  )}

  /**
    * Logout a user and redirect to the home
    * @return
    */
  def logout = Action { implicit request =>
    Redirect(routes.Application.home()).withNewSession
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

        Redirect(routes.Application.home()).withSession(
          "user" -> data.login, "connected" -> "true"
        )
      }
    )
  }

}