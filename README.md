AllStudy content
============================
total : 9

android library : 1
- common

app module : 7
- engstudy
- allstudylauncher
- communicatestudy
- jemediaplayer
- contentsave
- bleapp
- sendstudy

explain : 1
- gradle


index
--------
1. [common](#common)
2. [engstudy](#engstudy)
3. [allstudylauncher](#allstudylauncher)
4. [communicatestudy](#communicatestudy)
5. [jemediaplayer](#jemediaplayer)
6. [contentsave](#contentsave)
7. [bleapp](#bleapp)
8. [sendstudy](#sendstudy)
9. [gradle](#gradle)


common <a id="common">
--------
android library
All Module use common library
comnon library collect reuse class 


engstudy <a id="engstudy">
---------
start, eng study, server, db

1. apply
  * db
  * gradle productFlavor, buildtype

2. method 1 explanation
~~~~
    code ...
~~~~

allstudylauncher <a id="allstudylauncher">
--------------------
simple explanation

1. content title

2. method 1 explanation
~~~~
    code ...
~~~~

communicatestudy <a id="communicatestudy">
--------------------
simple explanation

1. content title

2. method 1 explanation
~~~~
    code ...
~~~~

jemediaplayer <a id="jemediaplayer">
--------------------
simple explanation

1. content title

2. method 1 explanation
~~~~
    code ...
~~~~

contentsave <a id="contentsave">
--------------------
simple explanation

1. content title

2. method 1 explanation
~~~~
    code ...
~~~~

bleapp <a id="bleapp">
--------------------
simple explanation

1. content title

2. method 1 explanation
~~~~
    code ...
~~~~

sendstudy <a id="sendstudy">
--------------------
simple explanation

1. content title

2. method 1 explanation
~~~~
    code ...
~~~~


gradle <a id="gradle">
--------------------
gradle setting

1. buildType
  * text
  * to use productFlavors.xxx.signingConfig, productFlavors is above buildtypes

2. buildTypes code
~~~~
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'

            productFlavors.free.signingConfig = signingConfigs.freeRelease
            productFlavors.paid.signingConfig = signingConfigs.paidRelease
        }
        dev {
            debuggable true
            signingConfig signingConfigs.debug
        }
        qa {
            debuggable true;
            signingConfig signingConfigs.debug
        }
    }
~~~~

3. productFlavors
  * text

4. productFlavors code
~~~~
    productFlavors {
        free {
            applicationId "com.jshstudy.allstudy.free"
            versionName defaultConfig.versionName +"-free"
        }

        paid {
            applicationId "com.jshstudy.allstudy.paid"
            versionName defaultConfig.versionName +"-paid"
        }
    }
~~~~

5. Differences
  * Build Types are used for your development cycle (debug, release, qa, staging, etc), while Product Flavors are used for your distribution strategy (free vs paid, rebranding, etc)
  * minifyEnabled property can be configured in Build Types, but NOT in Product Flavors
  * applicationIdSuffix property can be configured in Build Types, but NOT in Product Flavors
  * applicationId can be configured in Product Flavors, but NOT in Build Types
  * minSdkVersion,targetSdkVersion, versionCode, versionName can be configured in Product Flavors, but NOT in Build Types

6. Common
  * signingConfig property can be set in both
  * buildConfig property can be set in both (the ability to provide custom Java code)
  * you can set different dependencies (see example code here) inside dependencies{} block by: flavors (<flavorName>Compile)
  * build type (<buildTypeName>Compile)
  * both flavor and build type combined (<flavorName><BuildTypeName>Compile).



readme.md expain
https://gist.github.com/ihoneymon/652be052a0727ad59601
https://simhyejin.github.io/2016/06/30/Markdown-syntax/

gradle
https://www.myandroidsolutions.com/2016/06/16/android-build-types-flavors/#.WjxeXU1G2Ah
