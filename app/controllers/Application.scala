package controllers

import play.api._
import play.api.data.Form
import play.api.data.Forms._
import play.api.mvc._

class Application extends Controller {

  case class LoginData(login: String, password: String, remember: Boolean)

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  /**
    * Render the login form
    * @return Play result
    */
  def login = Action {

    case class LoginData(login: String, password: String, remember: Boolean)

    val loginForm = Form(
      mapping(
        "login" -> email,
        "password" -> nonEmptyText(minLength=6),
        "remember" -> boolean
      )(LoginData.apply)(LoginData.unapply)
    )

    Ok(views.html.loginform(loginForm))
  }

  /**
    * Parce the content of a form & connect the user
    * @return
    */
  def connection = Action {

    case class LoginData(login: String, password: String, remember: Boolean)

    val loginForm = Form(
      mapping(
        "login" -> email,
        "password" -> nonEmptyText(minLength=6),
        "remember" -> boolean
      )(LoginData.apply)(LoginData.unapply)
    )

    loginForm.fold(
      error => {
        BadRequest(views.html.loginform(loginForm))
      },

      data => {

        Logger info "the user email : $data.login"
        Logger info "the user passw : $data.password"
        Redirect(routes.Application.index())
      }
    )
  }

}