package com.rk.loginexample.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rk.loginexample.data.local.model.User
import com.rk.loginexample.data.local.dao.UserDao

@Database(entities = [User::class],version = 1)
abstract class Appdatabase :RoomDatabase(){
   abstract fun userDao(): UserDao

    companion object{

        @Volatile
        private var instance: Appdatabase? =null

        private var LOCK=Any()

        operator fun invoke(context: Context)= instance
            ?:synchronized(LOCK){
            instance
                ?: buildDatabase(
                    context
                ).also {
                instance =it
            }

        }

        private fun buildDatabase(context: Context): Appdatabase {

            return Room.databaseBuilder(context,
                Appdatabase::class.java,"UserDatabase.db").build()
        }


    }
}