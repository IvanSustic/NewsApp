package hr.tvz.android.newsapp.login.loginController

import hr.tvz.android.newsapp.ServiceGenerator
import hr.tvz.android.newsapp.login.loginView.LoginView
import hr.tvz.android.newsapp.login.model.AccessToken
import hr.tvz.android.newsapp.login.model.IToken
import hr.tvz.android.newsapp.login.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginController(var loginView: LoginView): ILoginController {
    lateinit var accesToken: AccessToken

    val client: IToken = ServiceGenerator().createService(
        IToken::class.java,
        "http://10.0.2.2:8080/auth/api/v1/"
    )
    override fun onLogin(username: String, password: String) {
        val token: Call<AccessToken?>? = client.getAccessToken(User(username,password))
        token?.enqueue(object: Callback<AccessToken?> {
            override fun onResponse(
                call: Call<AccessToken?>?,
                response: Response<AccessToken?>
            ) {

                if (response.isSuccessful) {
                    accesToken = response.body()!!
                    loginView.onLoginSuccess(accesToken)


                }

            }

            override fun onFailure(call: Call<AccessToken?>, t: Throwable) {
                loginView.onLoginError("Neuspješan login.")

            }

        })
    }


    override fun logout(token: String) {
        val client2: IToken? = ServiceGenerator().createService(
            IToken::class.java,

            "http://10.0.2.2:8080/auth/api/v1/",

        )
        val token: Call<String>? = client2?.logout(token)
        token?.enqueue(object: Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {

                if (response.isSuccessful) {
                    loginView.onLoginError("Logout")
                }

            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                loginView.onLoginError("Neuspješan login.")

            }

        })

    }

}