package hr.tvz.android.newsapp.login.loginView

import hr.tvz.android.newsapp.login.model.AccessToken
import hr.tvz.android.newsapp.model.Kategorija

interface LoginView {
    fun onLoginSuccess(accessToken: AccessToken)
    fun onLoginError(message: String?)
}