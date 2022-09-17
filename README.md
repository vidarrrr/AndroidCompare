# AndroidCompare
Kotlin XML vs Kotlin Jetpack Compose vs Flutter

# Release APK Sizes
```
Kotlin XML -> 4.59MB
Kotlin Jetpack Compose -> 4.85MB
Flutter -> 17.48MB
```

# After installation
```
Kotlin XML apk size -> 26.09MB
Kotlin Jetpack Compose apk size -> 36.36MB
Flutter apk size -> 29.43MB
```

# Speed Test
> Scenario: Open app -> Get data -> Scroll to last data -> Click on data and show toast message -> Wait until message disappears -> Close app.

> Scenario base at hand, not UI testing

# Total Seconds
```
5.16 	kotlin xml 
5.63 	kotlin compose
4.74 	flutter
```

# Kotlin XML 
```
CPU   VSS       RSS
0%    864488K   67576K  com.anime.app
6%    907848K   87984K  com.anime.app
2%    893084K   84300K  com.anime.app
```

# Kotlin Jetpack Compose
```
CPU   VSS       RSS
0%    835808K   39336K  com.anime.jetpack.compose
4%    907776K   90472K  com.anime.jetpack.compose
25%   908476K   94204K  com.anime.jetpack.compose
```

# Flutter
```
CPU   VSS       RSS
0%    885108K   79216K com.example.anime_app_flutter
4%    891704K   89328K com.example.anime_app_flutter
```

# Details 

> https://stackoverflow.com/questions/14042687/cpu-usage-per-application-in-android)

> Command : adb shell top | grep anime

> Flutter 2.10.4 Dart 2.16.2  DevTools 2.9.2

> Kotlin 1.7.10

> Kotlin 1.7.10 Compose Version = 1.3.0-beta01

**These tests are based on Android 6 Device**
