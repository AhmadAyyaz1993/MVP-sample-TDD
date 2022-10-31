# SadaPayTest

### Note: 
Please checkout the master branch for the code

I have built this project using MVP architecture and tried to keep it simple and clean. There are few of the libraries I have mentioned below that I have used to achieve different purposes in the app. I have also implemented some unit tests for the business logic in the app for the api call that's been used to fetch the trending repos from the remote data source. 

Some important external libraries/dependencies that I have used in the app are as follows:

## Libraries

### Retrofit

```gradle
 implementation 'com.squareup.retrofit2:retrofit:2.9.0'
 implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
```
I have used retrofit to handle the network calls which uses the GsonConverterFactory to parse the json to the data objects.

### Dagger Hilt
```gradle
 implementation "com.google.dagger:hilt-android:2.43.2"
 kapt "com.google.dagger:hilt-compiler:2.43.2"
```
I have used this library to handle the dependencies in the app. 

### Mvp moxy
```gradle
 implementation "com.github.moxy-community:moxy:2.2.2"
 annotationProcessor "com.github.moxy-community:moxy-compiler:2.2.2"
 implementation "com.github.moxy-community:moxy-androidx:2.2.2"
 implementation "com.github.moxy-community:moxy-ktx:2.2.2"
 kapt "com.github.moxy-community:moxy-compiler:2.2.2"
```
I have used this library to handle the state of the presenter. This library helps in maintaining the state of the presenter and automatically handles the recreations of the activities. Just like Viewmodels this library helps in making the presenter lifecycle aware.

### MockWebserver
```gradle
 testImplementation('com.squareup.okhttp3:mockwebserver:4.9.1')
```
This library is being used for writing the unit test. It helps us to create the fake server and mocking the response in case of some api call.
