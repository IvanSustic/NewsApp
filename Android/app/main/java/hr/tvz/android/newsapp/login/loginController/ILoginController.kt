package hr.tvz.android.newsapp.login.loginController

import hr.tvz.android.newsapp.login.model.AccessToken

interface ILoginController {
    fun onLogin(username: String, password: String)
    fun logout(token: String)
}