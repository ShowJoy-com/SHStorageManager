# 统一存储模块

为了解决App里存储的混乱而导致不必要的开发和维护成本，以及解决存储混乱导致的不能统一管理的问题，特此提供的统一的存储模块SHStorageManager，包括内存的缓存和磁盘存储，以module和key结合为维度进行存储，支持基本类型和引用类型。

目前，SHStorageManager的缓存采取LruCache，最大条数为10，可配置；持久化存储采取SharedPreferences，不同module存储在不同文件。SHStorageManager的好处在于：
 * 统一管理app所有的存储，可以根据需要清除缓存
 * 清除缓存时，可以清除所有存储，也可以指定清除指定module所有的存储；也可以清除指定module的指定key
 * 使用方便，无需考虑存储的实现

## SHStorageManager的使用

#### 1、aar已上传Jcenter，只要如下gradle依赖即可

    compile 'com.showjoy.android:storage:2.0.0'
    
#### 2、首先在application的onCreate里调用init

    public class MainApplication extends Application {
        @Override
        public void onCreate() {
           super.onCreate();

           SHStorageManager.init(this);
           //SHStorageManager.init(this, MAX_SIZE);
        }
    }
 #### 3、存储数据，支持基本类型和引用类型

     //存储内存缓存
     SHStorageManager.putToCache("detail", "334332324", "{}");

     SHStorageManager.putToCache("detail", "334332324", false);

     SHStorageManager.putToCache("detail", "334332324", 12);

     SHStorageManager.putToCache("detail", "334332324", 12f);

     //存储到disk的数据，这里会先存到cache，再存储到disk
     SHStorageManager.putToDisk("setting", "theme", 0);

     SHStorageManager.putToDisk("detail", "334332324", false);

     SHStorageManager.putToDisk("detail", "334332324", 12);

     SHStorageManager.putToDisk("detail", "334332324", 12f);
     
#### 4、获取数据

先从cache读取，再从disk读取。
从disk读出，会存储cache，以供下次更快读取

     int theme = SHStorageManager.get("setting", "theme", 0);

     boolean theme = SHStorageManager.get("setting", "themeBoolean", false);

     String theme = SHStorageManager.get("setting", "themeString", "test");
     
#### 5、清除缓存

    //清除所有缓存
    SHStorageManager.clearCache();
    //清除指定模块的缓存
    public static void clearCache(String module)

    // public static void removeFromDisk(String module)
    //清除指定模块的指定key
    //public static void removeFromCache(String module, String key)
    // public static void removeFromDisk(String module, String key)

麻烦接入方在这里登记下，谢谢
https://github.com/ShowJoy-com/SHStorageManager/issues/1


​
