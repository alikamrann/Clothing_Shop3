package com.example.retrofitdemo

import com.google.gson.annotations.SerializedName

class AlbumsItem(
    @SerializedName("id")
     val id: Int,
    @SerializedName("title")
     val title: String,
    @SerializedName("userId")
     val userId: Int
)
