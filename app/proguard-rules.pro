# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontobfuscate
-dontwarn org.apache.**
-dontwarn br.com.gps.**
-keep class br.com.gps.** { *; }
-keep class org.joda.time.** { *; }
-keep class org.apache.commons.** { *; }

#widgets maxima
-keep class maximasistemas.android.Widgets.** { *; }
-keep class com.google.android.gms.common.GooglePlayServicesUtil.** { *; }

-dontwarn maximasistemas.android.Widgets.**
-dontwarn no.geosoft.cc.util.Day.**
-dontwarn com.google.android.gms.common.**
-dontwarn okio.**
-dontwarn org.joda.time.**

-keepattributes InnerClasses
-keepattributes EnclosingMethod

# amazon
#-keep class com.amazonaws.util.**
-dontwarn com.amazonaws.util.**

#Chart
-keep class com.github.mikephil.charting.** { *; }
-dontwarn io.realm.**

# magicalcamera
#-keep class com.frosquivel.magicalcamera.**
#-dontwarn com.frosquivel.magicalcamera.**

# rhino (javascript)
#-dontwarn org.mozilla.javascript.**
#-dontwarn org.mozilla.classfile.**
#-keep class org.mozilla.javascript.** { *; }


-keep class com.viewpagerindicator.**  { *; }
-dontwarn com.viewpagerindicator.**

-keep class org.xmlpull.v1.**  { *; }
-dontwarn org.xmlpull.v1.**

-keepattributes Signature
-keepattributes Exceptions

-assumenosideeffects class android.util.Log {
    public static int v(...);
    public static int d(...);
}

-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-verbose
# Optimization is turned off by default. Dex does not like code run
# through the ProGuard optimize and preverify steps (and performs some
# of these optimizations on its own).
-dontoptimize
-dontpreverify
# Note that if you want to enable optimization, you cannot just
# include optimization flags in your own project configuration file;
# instead you will need to point to the
# "proguard-android-optimize.txt" file instead of this one from your
# project.properties file.
-keepattributes *Annotation*
-keep public class com.google.vending.licensing.ILicensingService
-keep public class com.android.vending.licensing.ILicensingService
# For native methods, see http://proguard.sourceforge.net/manual/examples.html#native
-keepclasseswithmembernames class * {
    native <methods>;
}
# keep setters in Views so that animations can still work.
# see http://proguard.sourceforge.net/manual/examples.html#beans
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}
# We want to keep methods in Activity that could be used in the XML attribute onClick
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}
# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}
-keepclassmembers class **.R$* {
    public static <fields>;
}
# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**
-dontwarn android.databinding.**
-keep class android.databinding.** { *; }

-ignorewarnings
# Evernote
#-dontwarn com.evernote.android.job.gcm.**
#-dontwarn com.evernote.android.job.util.GcmAvailableHelper

#-keep public class com.evernote.android.job.v21.PlatformJobService
#-keep public class com.evernote.android.job.v14.PlatformAlarmService
#-keep public class com.evernote.android.job.v14.PlatformAlarmReceiver
#-keep public class com.evernote.android.job.JobBootReceiver

-dontnote org.apache.commons.codec.**

-keep class com.itextpdf.** { *; }
-dontwarn com.itextpdf.**

-dontwarn retrofit2.**
-keep class retrofit2.** { *; }