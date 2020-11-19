package com.app.demologintask.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class UserDB(
    @PrimaryKey
    open var id:  String = "",

    open var userName:  String = "" )

    : RealmObject() {
}