package com.app.demologintask.login


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.app.demologintask.R
import com.app.demologintask.databinding.ActivityLoginBinding
import com.app.demologintask.login.viewmodel.LoginViewModel
import com.app.demologintask.model.UserDB
import com.app.demologintask.util.CustomeProgressDialog
import io.realm.Realm


class LoginActivity : AppCompatActivity() {

    var binding: ActivityLoginBinding? = null
    var viewmodel: LoginViewModel? = null
    var customeProgressDialog: CustomeProgressDialog? = null

    private lateinit var realM: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Realm.init(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewmodel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding?.viewmodel = viewmodel
        customeProgressDialog = CustomeProgressDialog(this)
        initObservables()


        realM = Realm.getDefaultInstance()
    }

    private fun initObservables() {
        viewmodel?.progressDialog?.observe(this, Observer {
            if (viewmodel?.username?.get()!!.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please Enter Username",
                    Toast.LENGTH_SHORT
                ).show()

            } else if (viewmodel?.password?.get()!!.isEmpty()) {
                Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (it!!) customeProgressDialog?.show() else customeProgressDialog?.dismiss()
            }
        })

        viewmodel?.userLogin?.observe(this, Observer { user ->

            viewmodel?.username?.set("")
            viewmodel?.password?.set("")

            if (user != null) {

                if (user.getErrorCode() == "00") {

                    var userDB = UserDB()

                    realM.beginTransaction()

                    userDB.id = user.getUser()?.getUserId().toString()
                    userDB.userName = user.getUser()?.getUserName().toString()

                    realM.insertOrUpdate(userDB)
                    realM.commitTransaction()

                    Toast.makeText(
                        this,
                        "Welcome, ${user?.getUser()?.getUserName()}",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(this, "${user?.getErrorMessage()}", Toast.LENGTH_LONG).show()
                }
            }


        })
    }


}
