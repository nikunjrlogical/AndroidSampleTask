package com.app.demologintask.unittest

class LoginValidator {

    companion object {
        val EMPTY = ""
        val MIN_USERNAME_LENGTH = 30
        val MIN_PASSWORD_LENGTH = 16
    }

    fun emptyUsername(username: String): Boolean {
        return username == EMPTY
    }

    fun validateUsername(username: String): Boolean {
        return username.length <= MIN_USERNAME_LENGTH
    }

    fun emptyPassword(password: String): Boolean {
        return password == EMPTY
    }
    fun validatePassword(password: String): Boolean {
        return password.length <= MIN_PASSWORD_LENGTH
    }
}
