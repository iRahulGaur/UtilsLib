# UtilsLib
This is simple library to make daily things easy in android.

![stars](https://img.shields.io/github/stars/iRahulGaur/UtilsLib?style=flat-square) ![forks](https://img.shields.io/github/forks/iRahulGaur/UtilsLib?style=flat-square) ![issues](https://img.shields.io/github/issues/iRahulGaur/UtilsLib?style=flat-square) ![license](https://img.shields.io/github/license/iRahulGaur/UtilsLib?style=flat-square) [![](https://jitpack.io/v/iRahulGaur/UtilsLib.svg)](https://jitpack.io/#iRahulGaur/UtilsLib) ![API](https://img.shields.io/badge/Android%20API-v21%2B-blue)

### [Link to Utils.java](https://github.com/iRahulGaur/UtilsLib/blob/master/utils/src/main/java/com/rahulgaur/utils/Utils.java)

### How to download 

Add this in your Top-Level Gradle
```
allprojects {
    repositories {
    ...
        maven { url 'https://jitpack.io' }
	}
}
```

Add this in your App Gradle
```
dependencies {
    implementation 'com.github.iRahulGaur:UtilsLib:1.x.x'
}
```
---
### Open to any contribution
---

### Table of Content
    1. Intents
    2. Toast
    3. ImagePicker
    4. SharedPreferences
    5. AES Encryption
    6. Network Check
    
### How to use

1. Intents
```javascript
// simple intent from 1 class to another
Utils.setIntent(ActivityFrom.this, ActivityTo.class);

// intent with no back logs
Utils.setIntentNoBackLog(ActivityFrom.this, ActivityTo.class);

// intent with bundle data
Bundle mBundle = new Bundle();
mBundle.putString("key","data"); // or add anything you want
Utils.setIntent(ActivityFrom.this, ActivityTo.class, "ExtraKey", mBundle);

// intent to MainActivity or any LandingPage with No back logs
// set your LandingPage 1 time
Utils.homeActivity = MainActivity.class; //set your home/main/landing class here
Utils.sendToMain(Activity.this); //directly send to pre-set HomeActivity or
```

2. Toast and Logs
```javascript
int length = 0; // 0 for Short 1 for Long
Utils.showMessage(this, "message", length);

// to show logs in error
Utils.showLogE("message"); // pass the message
Utils.showLogE("TAG", "message"); // pass TAG and message
Utils.showLogE("TAG", "message", "Exception"); // pass TAG and Message and Exception
   
Exception exception = new Exception(); // some exception
Utils.showLogE(exception); // pass Exception
Utils.showLogE("TAG", "message", exception); // pass TAG and Message and Exception
Utils.showLogE("TAG", exception); // pass TAG and Exception
```

3. Image Picker
```javascript
int x, y; // x and y are ratio of result Image eg: 1:1
x = 1;
y = 1;
Utils.imagePicker(this, x , y);

// x and y are optional, without crop ratio Image will be of any shape
Utils.imagePicker(this); 
```

4. Shared Preferences
```javascript
// init shared preferences
PreferenceManager.setPreferenceManager(this, "sharedPreferenceName");

//save String
PreferenceManager.saveString("key", "value");
//get String, default value is ""
PreferenceManager.getString("key");

//save boolean
PreferenceManager.saveBoolean("key", value);
//get boolean, default value is false
PreferenceManager.getBoolean("key");

//save int
PreferenceManager.saveInt("key", value);
//get int, default value is 0
PreferenceManager.getInt("key");

//clear sharedPreferences
PreferenceManager.clearPreferences();
```
5. AES Encryption of Strings
```javascript
//set SecreyKey, size should be 16 char
// to generate a random String user
DataEncryption.setSecretKeyString(DataEncryption.getAlphaNumericString(16));
//or
DataEncryption.setSecretKeyString("some secret key");

//Encrypt String
String message = "Some message";
String encryptedMessage = DataEncryption.AESEncryptionString(message);

//Decrypt String
String decryptedMessage = DataEncryption.AESDecryptionString(encryptedMessage);
```
6. Network Check
```javascript
//to check network connectivity you need to add permission in your manifest
// <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

if (Utils.isNetworkConnected(this)){
    // network is connected 
} else {
    // no internet connection
    // optional show dialog
    Utils.showAlertConnectionError(this);
    // or with title and message
    Utils.showAlertConnectionError(this, "title", "message");
}
```

### Change Log
*0.1*
  * Initial release
  
*0.2*
  * Added Progress Dialog
  * Updated ReadMe
  
*0.21*
  * Removed Progress
  
*1.0.0*
  * Added Logs and NetworkChecker

*1.0.1*
  * Fixed some issues


Download the Library [Link to Jitpack.io](https://jitpack.io/#iRahulGaur/UtilsLib "Utils Library - Jitpack")

Using Library [Arthur's Image Cropper Library](https://github.com/ArthurHub/Android-Image-Cropper )

### License
[Apache 2.0](LICENSE)
