# 奥丁LinkSDK

- [奥丁LinkSDK官网](http://www.odinanalysis.com/link.html)
- 移动端场景还原解决方案，实现Web与App的无缝链接

## 一、集成说明

### 配置build.gradle

- 项目根目录的build.gradle

```groovy
buildscript {

    repositories {
        ...
        maven { url "http://maven.odinlk.com:58080/repository/android/" }
    }
    dependencies {
       ...
    }
}
allprojects {
    repositories {
       ...
        maven {
            url "http://maven.odinlk.com:58080/repository/android/"
        }
    }
}
```

- module工程的build.gradle

```
implementation 'com.odin.link:LinkSDK:1.0.2_beta'
```

### 权限申请

- 请在AndroidManifest中添加如下权限

```xml
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

### 混淆设置

- OdinLinkSDK已经做了混淆处理，再次混淆会导致不可预期的错误，请在您的混淆脚本中添加如下的配置，跳过对OdinLinkSDK的混淆操作

```
-keep class com.odin.link.**{*;}
-dontwarn com.odin.link.**
```

## 二、OdinLinkSDK的Api说明

### 初始化

```Java
/**
 * 初始化
 *
 * @param context       上下文
 * @param odinAppKey    odinAppKey
 * @param odinAppSecret odinAppSecret
 */
public static synchronized void init(@NonNull Context context, @NonNull String odinAppKey, @NonNull String odinAppSecret)
```



### 解析参数

- 此方法在需要唤醒或者还原的Activity中的onCreate和onNewIntent方法中调用。

```java
/**
 * 解析链接打开APP时传递的参数
 *
 * @param uri 打开APP的scheme链接
 * @return 参数集合
 */
public static HashMap<String, Object> getLinkParamsByUri(Uri uri)
```

例如：

```java
public class UriSchemeActivity extends AppCompatActivity {
 
   @Override
   protected void onCreate(@Nullable Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     Intent intent = getIntent();
     dealLinkIntent(intent);
   }
 
   @Override
   protected void onNewIntent(Intent intent) {
     super.onNewIntent(intent);
     setIntent(intent);
     dealLinkIntent(intent);
   }
 
   /**
     * 处理跳转后的逻辑。
	*
     * @param intent intent
     */
    private void dealLinkIntent(Intent intent) {
        Uri uri = intent.getData();
        if (uri != null) {
            //Link跳转时携带的参数，解析后请自行还原相关场景或做其他操作
            HashMap<String, Object> mapLinkParams = OdinLink.getLinkParamsByUri(uri);
        }
    }
 }
```



## 三、APP和后台配置

### 奥丁开发者中心配置

![link](C:\Users\Administrator\Desktop\link.png)



**唯一标识**：自动生成，在App Links方案中使用。为了精准链接APP的路径，如果使用AppLink方案，请在AndroidMainfest.xml中的跳转页面的path后添加该字段（示例：/demo/pagea000001，原path:/demo/pagea）；

**包名**：您的APP的包名(示例：com.odin.link.demo)；

**下载/引导地址**：您的APP的下载/引导下载地址，可以是应用商店的下载地址，也可以自定义。如果需要自定义配置，请勾选“自定义引导地址”；

 **URL Scheme**：点击输入框输入您的Android应用的URI Scheme协议（示例：mlink://com.odin.link）；

**高级配置**：是配置App Links的开关项，如果需要配置请勾选开启。App Links是谷歌官方应用深度链接标准，Android 6.0以上版本支持，可以实现应用间无缝跳转。但是由于国内环境，请尽量使用URL Scheme方案。

**指纹证书(SHA256)**：使用java的 keytool 命令获取SHA256指纹证书：keytool -list -v –keystore { my-release-key.keystore文件的存放路径}，请填写线上发布包所对应的证书（指纹证书示例：AD:7D:09:5D:66:8C:39:4C:62:32:C3:47:6B:53:56:21:2D:AB:52:35:FF:4E:6B:1F:8F:D3:D0:E1:43:4E:C5:6B）；

**App Links**：自动生成（示例：https://www.odinlink.com）；

**路由配置**：配置安卓需要跳转的页面的path，APP和开发者中心需要保持一致，否则将不能跳转。

**路径标识**：标识路径的值，请保持唯一；

**安卓页面名称**：APP项目中配置跳转安卓页面Activity中<intent-filter/>的path属性值

### APP端设置

- 在AndroidMainfest.xml中添加URI Scheme，以下只是展示了单个页面的配置情况，多个页面配置只需将需要跳转的页面都配置上即可。示例代码如下所示：

```xml
<activity
   android:name="com.odin.link.demo.link.UriSchemeActivity"
   android:alwaysRetainTaskState="true"
   android:launchMode="singleTask"
   android:noHistory="true">
 

<!--注意事项-->

<!--1、Uri Scheme方式和APP Links方式两者选其一-->

<!--2、由于国内环境，请尽量采用Uri Scheme方式,如果选择APP Links方式，为了保持打开APP的成功率，请也一定配置Uri Scheme方式-->
<!--3、不管采用哪种方式，scheme、host和path请按照odin官网开发者中心配置的内容填写，请保持一致，否则将不能跳转-->
<!--4、配置多个Link页面，请按UriSchemeActivity的方式进行-->

<!--Uri Scheme方式-->
   <intent-filter>
     <action android:name="android.intent.action.VIEW" />
 
     <category android:name="android.intent.category.DEFAULT" />
     <category android:name="android.intent.category.BROWSABLE" />
	 <!--以上参数严禁修改-->
       
     <!--以下参数请于开发者中心配置保持一致-->
	 <!--mlinl://com.odin.link是开发者中心的Uri Scheme参数，path是路由配置中的值-->
      <data
            android:host="com.odin.link"
            android:path="/demo/pagea"
            android:scheme="mlink" />
    </intent-filter>
</activity>
```

