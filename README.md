# Awesome App for Android

![GitHub Cards Preview](https://github.com/gilarps/awesome-app-android/blob/master/art/app.png)

Awesome App is and Android App contains randomized image and descriptions from [Pexels API](https://www.pexels.com/api/).

## Tech Stack

This project uses feature monolith architecture with MVVM

## Development setup

This project developed using Android Studio Bumblebee | 2021.1.1 Beta 4

### Libraries

- Application entirely written in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/)
- Uses [Koin](https://github.com/InsertKoinIO/koin) for dependency injection
- Uses [Jetpack Navigation](https://developer.android.com/guide/navigation) for navigation between fragment
- Uses [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) to store key-value pairs
- Uses [Paging](https://developer.android.com/jetpack/androidx/releases/paging) to load data gradually
- Uses [Moshi](https://github.com/square/moshi) for JSON parser
- Uses [Glide](https://github.com/bumptech/glide) for image loader


### API

Using [Pexels API](https://www.pexels.com/api/) to fetch photos. 
