package com.app.demologintask.login

class User {

    private var userName: String? = null

    private var userId: String? = null

    fun getUserName(): String? {
        return userName
    }

    fun setUserName(userName: String?) {
        this.userName = userName
    }

    fun getUserId(): String? {
        return userId
    }

    fun setUserId(userId: String?) {
        this.userId = userId
    }

    override fun toString(): String {
        return "ClassPojo [userName = $userName, userId = $userId]"
    }

}