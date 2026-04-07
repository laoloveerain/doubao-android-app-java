# Add project specific ProGuard rules here.
-keep class com.example.doubaoapp.** { *; }
-keep class com.example.doubaoapp.model.** { *; }
-keep class com.example.doubaoapp.api.** { *; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}
