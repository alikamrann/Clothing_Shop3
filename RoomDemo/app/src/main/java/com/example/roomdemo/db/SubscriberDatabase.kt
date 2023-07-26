package com.example.roomdemo.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RenameColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.example.roomdemo.R

@Database(
    entities = [Subscriber::class],
    version = 3,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 2, to = 3, spec = SubscriberDatabase.Migration3To4::class)
    ]
)
abstract class SubscriberDatabase : RoomDatabase() {

    abstract val subscriberDao: SubscriberDao

    @RenameColumn(
        tableName = "subscriber_data_table",
        fromColumnName = "subscriber_email",
        toColumnName = "subscriber_mail"
    )
    class Migration3To4 : AutoMigrationSpec

    companion object {
        @Volatile
        private var INSTANCE: SubscriberDatabase? = null
        fun getInstance(context: Context): SubscriberDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}