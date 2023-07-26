package com.example.viewmodelscope.model

import kotlinx.coroutines.delay

class UserRepository  {

    suspend fun getUser() : List<User>{
        delay(8000)
        val users : List<User> = listOf(
            User(1,"Sam"),
            User(2,"Ali"),
            User(3,"Kamran"),
            User(4,"Parsa")
        )
        return users
    }
}