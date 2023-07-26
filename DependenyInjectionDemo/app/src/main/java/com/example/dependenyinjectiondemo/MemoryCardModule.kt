package com.example.dependenyinjectiondemo

import android.util.Log
import dagger.Module
import dagger.Provides


@Module
class MemoryCardModule( val  memorySize : Int)  {

    @Provides
    fun providesMemoryCard() : MemoryCard{

        Log.i("MYTAG","size of the memory is $memorySize")
        return MemoryCard()
    }
}