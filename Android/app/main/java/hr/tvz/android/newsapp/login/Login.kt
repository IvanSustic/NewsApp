package hr.tvz.android.newsapp.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import hr.tvz.android.newsapp.adminNews.AdminNews

import hr.tvz.android.newsapp.databinding.ActivityLoginBinding
import hr.tvz.android.newsapp.login.loginController.LoginController
import hr.tvz.android.newsapp.login.loginView.LoginView
import hr.tvz.android.newsapp.login.model.AccessToken
import hr.tvz.android.newsapp.userNews.UserNews

class Login : AppCompatActivity(), LoginView {
    lateinit var binding: ActivityLoginBinding
    lateinit var loginController: LoginController
    var accesTokenS : String = ""
    var TokenS : String = ""
    lateinit var acesToken :AccessToken
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
        loginController = LoginController(this)
        binding.loginButton.setOnClickListener({
            loginController.onLogin(binding.username.text.toString(),binding.password.text.toString())
        })

    }

    override fun onLoginSuccess(accessToken: AccessToken) {
        accesTokenS = accessToken.accessToken
        TokenS =accessToken.token
        acesToken = accessToken
        var i :Intent
        println(binding.username.text.toString())
        if (binding.username.text.toString() =="admin"){
            i = Intent(this, AdminNews::class.java)
        } else
        {
            i = Intent(this, UserNews::class.java)
        }

        var arguments = Bundle()
        arguments.putString(UserNews().token, accessToken.accessToken)
        i.putExtras(arguments)
        startActivity(i)

    }

    override fun onLoginError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {

        super.onResume()
        if (!TokenS.isNullOrBlank()){

            loginController.logout(TokenS)
        }


    }
}