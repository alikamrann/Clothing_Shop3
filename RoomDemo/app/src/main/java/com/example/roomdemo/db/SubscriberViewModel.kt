package com.example.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {

    val subscriber = repository.subscribers
    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete: Subscriber
    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>>
        get() = statusMessage

    val saveOrUpdateBtn = MutableLiveData<String>()
    val clearAllBtn = MutableLiveData<String>()

    init {
        saveOrUpdateBtn.value = "Save"
        clearAllBtn.value = "Clear All  "
    }

    fun saveOrUpdate() {
        if (isUpdateOrDelete) {
            update(subscriberToUpdateOrDelete)
            subscriberToUpdateOrDelete.name = inputName.value!!
//            subscriberToUpdateOrDelete.email = inputEmail.value!!
        } else {
            val name = inputName.value!!
            val email = inputEmail.value!!

            inputName.value = ""
            inputEmail.value = ""

            insert(Subscriber(0, name, email,""))
        }
    }

    fun clearAllOrDelete() {
        if (!isUpdateOrDelete)
            clearAll()
        else
            delete(subscriberToUpdateOrDelete)

    }

    fun insert(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(subscriber)
        withContext(Dispatchers.IO) {
            statusMessage.postValue(Event("Subscriber inserted successfully!"))
        }
    }

    fun update(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(subscriber)
        withContext(Dispatchers.IO) {
            statusMessage.postValue(Event("Subscriber updated successfully!"))
        }
    }

    fun delete(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(subscriber)
        withContext(Dispatchers.IO) {
            statusMessage.postValue(Event("Subscriber deleted successfully!"))
        }

    }

    fun clearAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun initSubscriber(subscriber: Subscriber) {
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber

        saveOrUpdateBtn.value = "Update"
        clearAllBtn.value = "delete"
    }
}