package com.example.movieapp

object Constant {
    const val book_key = "book_key"
    const val book_list = "bookList"
    const val defaultBookList = "{bookList:[]}"
    const val bookDetailData = "bookDetailData"
    val genres = arrayOf("Select Genre", "Biography", "Fantasy", "Mystery", "Romance", "Science Fiction")
    val bookType = arrayOf("Select Type", "Fiction", "Non-Fiction")
    val repeatInterval = arrayOf("Select Repeat Interval", "1 Hr", "2 Hrs", "24 Hrs")
    const val sharedPrefName = "com.example.movieapp"
    const val requestStorageCode = 121
    val imageExtensions = listOf("jpg", "jpeg", "png", "gif", "bmp")
    val countryOption = listOf("India", "UAE", "US")
    const val statusBarColor = "statusBarColor"
    const val actionBarColor = "actionBarColor"
    const val imageFolderPath = "imageFolderPath"
    const val location_key = "location_key"
    const val location_list = "location_list"
    const val location_detail = "location_detail"
    const val defaultLocList = "{locationList:[]}"
    const val taskReminderChannel = "TASK_REMINDER_CHANNEL"
    const val taskReminder = "TASK_REMINDER"
    const val WEATHER_API_END_POINT = "https://api.openweathermap.org/"
    const val DOG_BREEDS_BASE_URL = "https://api.thedogapi.com/"
    const val GET_DOG_BREEDS_API = "v1/breeds"
    const val SEARCH_DOG_BREEDS_API = "v1/breeds/search"
    const val GET_BREED_INFO = "v1/breeds/{breed_id}"
}