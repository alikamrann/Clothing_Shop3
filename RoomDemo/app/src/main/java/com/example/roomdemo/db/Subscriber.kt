package com.example.roomdemo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber_data_table")
data class Subscriber(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subscriber_id")
    var id: Int,
    @ColumnInfo(name = "subscriber_name")
    var name: String,
    @ColumnInfo(name = "subscriber_mail")
    var email: String,

    @ColumnInfo("subscriber_address", defaultValue = "st1")
    var address : String?
)
