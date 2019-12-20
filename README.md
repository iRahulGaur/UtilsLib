# UtilsLib
This is simple library to make daily things easy in android.

![stars](https://img.shields.io/github/stars/iRahulGaur/UtilsLib?style=flat-square) ![forks](https://img.shields.io/github/forks/iRahulGaur/UtilsLib?style=flat-square) ![issues](https://img.shields.io/github/issues/iRahulGaur/UtilsLib?style=flat-square) ![license](https://img.shields.io/github/license/iRahulGaur/UtilsLib?style=flat-square) [![](https://jitpack.io/v/iRahulGaur/UtilsLib.svg)](https://jitpack.io/#iRahulGaur/UtilsLib)


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
    implementation 'com.github.irahulgaur:utilslib:0.1'
}
```

### Table of Content
    1. Intents
    2. Toast
    3. ImagePicker
    4. SharedPreferences
    5. AES Encryption
    6. Progress Dialog

### How to use

1. Intents
```javascript
// simple intent from 1 class to another
Utils.setIntent(Activity.this, Activity.class);

// intent with no back logs
Utils.setIntentNoBackLog(Activity.this, Activity.class);

// intent with bundle data
Bundle mBundle = new Bundle();
mBundle.putString("key","data"); // or add anything you want
Utils.setIntent(Activity.this, Activity.class, "ExtraKey", mBundle);

// intent to MainActivity or any LandingPage with No back logs
// set your LandingPage 1 time
Utils.setHomeActivityClass(MainActivity.class);
Utils.sendToMain(this);
```

2. Toast
```javascript
int length = 0; // 0 for Short 1 for Long
Utils.showMessage(this, "message", length);
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
//init sharedPreferences
Utils.setPreferenceManager(this);

// set SharedPreference Name
Utils.setSharedPreferenceFileName("name");

//save String
Utils.saveString("key", "value");
//get String, default value is ""
Utils.getString("key");

//save boolean
Utils.saveBoolean("key", value);
//get boolean, default value is false
Utils.getBoolean("key");

//save int
Utils.saveInt("key", value);
//get int, default value is 0
Utils.getInt("key");

//clear sharedPreferences
Utils.clearPreferences();
```
5. AES Encryption of Strings
```javascript
//set SecreyKey, size should be 16 char
// to generate a random String user
Utils.getAlphaNumericString(charLength);
//or
Utils.SecretKeyString = Utils.getAlphaNumericString(16);

//Encrypt String
String message = "Some message";
String encryptedMessage = Utils.AESEncryptionString(message);

//Decrypt String
String decryptedMessage = Utils.AESDecryptionString(encryptedMessage);
```
6. Progress Dialog
```javascript
//showing progress dialog
Utils.showProgressDialog("message", Activity.this);

//Dismissing progress dialog
Utils.dismissDialog();
```

### Change Log
*0.1*
  * Initial release
  
*0.2*
  * Added Progress Dialog
  * Updated ReadMe

Download the Library [Link to Jitpack.io](https://jitpack.io/#iRahulGaur/UtilsLib/0.1 "Utils Library - Jitpack")

Using Library [Arthur's Image Cropper Library](https://github.com/ArthurHub/Android-Image-Cropper )

### License
[Apache 2.0](LICENSE)
