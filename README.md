# StatusBar
关于Android StatusBar版本适配的Demo，仅供学习交流之用

1. 透明状态栏自Android4.4（API19）开始引入，因此4.4以下的版本是不可以显示透明状态栏的。
2. API19以上将状态栏显示为透明，只需要在res/values-v19/styles.xml文件中，设置  
```  
<item name="android:windowTranslucentStatus">true</item>  
```  
即可。如果应用中只有部分Activity的状态栏显示为透明状态，推荐以下写法：  
```
<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
</style>  

<!-- V19添加透明状态栏的配置 -->
<style name="ActivityTheme.TranslucentStatus" parent="AppTheme">
    <item name="android:windowTranslucentStatus">true</item>
</style>  

<!-- V19以下不用配置 -->
<style name="ActivityTheme.TranslucentStatus" parent="AppTheme">
</style>
```  
这样，哪个Activity需要透明状态栏，就将其Theme设置为”ActivityTheme.TranslucentStatus",  灵活方便。   
```
<activity android:name=".TranslucentStatusActivity"
        android:theme="@style/ActivityTheme.TranslucentStatus">
```  
3. 以上信息设置完以后，Activity的内容就从屏幕的顶端开始绘制了，而不是从状态栏的bottom开始。如果还是希望Activity的内容从状态栏bottom开始绘制，则需要再新建一个style，打开fitSystemWindow属性，如下：  
```
<!-- 该属性各版本都可以加，V19以下也可以不加，只保留style字段 -->
<style name="ActivityTheme.TranslucentStatus.FitSystemWindow" parent="ActivityTheme.TranslucentStatus">
    <item name="android:fitsSystemWindows">true</item>
</style>
```  
或者将相应根布局的fitSystemWindow属性打开，也是一样的效果。  
4. 经过以上设置，透明状态栏有了，基本的屏幕适配也有了，但还有一点不足就是现实状态栏的那块区域，目前只有根布局可以在上面绘制，而子View是不可以的，为了能更充分的利用状态栏的区域，我们可以选择将fitSystemWindow属性应用到子View上，而不是根据局或者Activity的属性上。即：  
Activity还是设置为透明状态栏  
```
<activity android:name=".TranslucentStatusActivity"
        android:theme="@style/ActivityTheme.TranslucentStatus">
```  
然后将布局文件中，需要绘制到状态栏bottom位置的view，添加上fitSystemWindow属性，而从屏幕顶端开始绘制的view，则不需要。  
```
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="#000077">

    <!-- 绿色背景会从屏幕顶端开始绘制 -->
    <android.support.v7.widget.AppCompatImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#007700" />

    <!— 文字则从状态栏的bottom开始绘制（从状态栏的bottom开始绘制的说法是错误的，这里这样说是为了理解方便） -->
    <android.support.v7.widget.AppCompatTextView
        android:id="@+id/api”
        android:fitsSystemWindows="true"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:textColor="#000000"
        android:textSize="32sp" />

    <android.support.v7.widget.AppCompatTextView
        android:layout_below="@+id/api"
        android:layout_marginTop="16dp"
        android:id="@+id/explain"
        android:textSize="20sp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginLeft="24dp"
        android:layout_marginRight="24dp" />
</RelativeLayout>
```
