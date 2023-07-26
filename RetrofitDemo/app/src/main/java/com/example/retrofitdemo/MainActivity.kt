package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var retrofitInstance :AlbumServices
    private lateinit var textView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById<TextView>(R.id.textView)
        retrofitInstance =
            RetrofitInstance.getRetrofitInstance().create(AlbumServices::class.java)


//        getAllAlbums()


//        getSpecificItem()


        uploadItem()

    }

    private fun getAllAlbums() {
        val singleResponseLiveData: LiveData<Response<AlbumsItem>> = liveData {
            val response: Response<AlbumsItem> = retrofitInstance.getAlbum(3)
            emit(response)
        }
        singleResponseLiveData.observe(this) {
            val title = it.body()?.title
            Toast.makeText(this, title, Toast.LENGTH_SHORT).show()
        }
    }

    private fun getSpecificItem() {
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response: Response<Albums> = retrofitInstance.getSortAlbums(3)
            emit(response)
        }
        responseLiveData.observe(this) {
            val albumList = it.body()?.listIterator()
            if (albumList != null) {
                while (albumList.hasNext()) {
                    val albumsItem: AlbumsItem = albumList.next()
                    val result =
                        " Album title ${albumsItem.title}" + "\n" +
                                " Album id ${albumsItem.id}" + "\n" +
                                " Album userId  ${albumsItem.userId}" + "\n\n\n"
                    textView.append(result)
                }
            }
        }
    }

    private fun uploadItem(){
        val albumsItem = AlbumsItem(0,"My title",3)
        val postResponse : LiveData<Response<AlbumsItem >> = liveData {
             val  response = retrofitInstance.uploadAlbum(albumsItem)
            emit(response)
        }
        postResponse.observe(this, Observer {
             val  receiveAlbumsItem  = it.body()
            val result =
                " Album title ${receiveAlbumsItem?.title}" + "\n" +
                        " Album id ${receiveAlbumsItem?.id}" + "\n" +
                        " Album userId  ${receiveAlbumsItem?.userId}" + "\n\n\n"
            textView.append(result)

        })
    }


}
