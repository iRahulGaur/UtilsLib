# UtilsLib
This is simple library to make daily things easy in android.

![stars](https://img.shields.io/github/stars/iRahulGaur/UtilsLib?style=flat-square) ![forks](https://img.shields.io/github/forks/iRahulGaur/UtilsLib?style=flat-square) ![issues](https://img.shields.io/github/issues/iRahulGaur/UtilsLib?style=flat-square) ![license](https://img.shields.io/github/license/iRahulGaur/UtilsLib?style=flat-square)

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

### How to use

1. using Intents
```javascript
// simple intent from 1 class to another
Utils.setIntent(Activity.this, Activity.class);

// intent with no back logs
Utils.setIntentNoBackLog(Activity.this, Activity.class);

// intent with bundle data
Bundle mBundle = new Bundle();
mBundle.putString("key","data"); // or add anything you want
Utils.setIntent(Activity.this, Activity.class, "ExtraKey", mBundle);
```

### Change Log
*0.1*
  * Initial release

Download the Library 

[Link to Jitpack.io](https://jitpack.io/#iRahulGaur/UtilsLib/0.1 "Utils Library - Jitpack")

Using Library [Arthur's Image Cropper Library](https://github.com/ArthurHub/Android-Image-Cropper )

### License
[Apache 2.0](LICENSE)
