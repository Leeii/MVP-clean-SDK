### MVP-clean架构底层

基于mvp-clean架构实现的框架底层
该框架实现主要考虑复用性，任何基于mvp-clean的架构都可以使用该底包

普通依赖关系如下：（如实例）
```
                   app
                    ↓
                lib-base
                    ↓
                lib-core

```

组件化项目使用

```
                   app
                    ↓
        ________________________________
      模块1   模块2         模块3  ...  模块N
        |______|____________|__________|
        ↓      ↓            ↓          ↓
                    ↓
               实现组件中间层
                    ↓
                lib-base
                    ↓
                lib-core
```


架构如图所示

![mvp-clena 架构示意图](https://upload-images.jianshu.io/upload_images/195193-73c5ad459c2199c2.png)

与普通MVP最大的不同是加入了 Domain Layer 和 usecases，把原本位于 Presenter 中臃肿的逻辑代码移到了 Domain layer 中，减轻了 Presenter 的体量，而 usecases 定义了每个业务的具体操作，细化了业务粒度，也有效提高了代码的重用性。




##### lib-core 

主要存放各种常用工具类
- .android
    - `Logger` 日志工具类
    - `Network` 网络状态工具类，检测网络连接等
    - `UIUtil` 界面工具类，用于转化界面单位，获取屏幕宽高等
    - `PreferencesHelper` SharedPreferences 操作工具类
    - `...`
    
- charset
    - `Base64`
    - `MD5`

- util

    - `Check` 判断检查类，检查字符串，集合数组等是否为空
    - `...`


##### lib-base

- common
    - `BaseActivity` 基础Activity 实现了BaseView部分方法
    - `BaseFragment` 基础Fragment 实现了BaseView的部分方法
    
- domain
    - `UseCase` 基本useCase
    - `UseCaseWithParameter` 待参数请求的UseCase
- http
    - `DefaultNetworkObserver` 基础网络错误处理观察者
        1. 构造传入 `Context` 处理为弹出Toast
        2. [LoadingHelper](https://github.com/Leeii/mvp-clean-sdk/blob/master/lib-base/src/main/java/com/leeiidesu/lib/base/loading/LoadingHelper.java) 处理为布局切换
        3. 不传 默认只打印错误堆栈信息
- imageloader
    - `ImageLoader` 图片加载工具 需在用之前初始化
        ```
        // imageEngine ImageEngine 的实现
        // imageConfig 全局图片加载 可设置默认站位图，错误站位图等
        ImageLoader.getInstance().init(imageEngine,imageConfig)
        ```
    - `ImageEngine` 执行图片加载 默认无实现，需要按需实现，提供Glide的默认实现
        `implementation 'com.leeiidesu:imageloader-glide:1.0.4'`
        
    - `ImageConfiguration` 图片加载的参数
- loading
    - `LoadingHelper` 布局切换工具，可切换至
        1. `.showLoading()` 显示加载中布局
        2. `.showError()` 显示加载错误时的布局
        3. `.showEmpty()` 显示数据为空时的布局
        4. `.showNoNetwork()` 显示网络不存在时的布局
        
        各种布局没有默认实现，因为各个项目需要的布局并不一致
        需要在使用前初始化 `LoadingHelperViewCreator.setDefaultStatusViewCreator(creator)`
- mvp
    - `BaseView` 基础View 带有部分使用频率很高的方法
    - `BasePresenter` 基础Presenter 暂无方法
- tools
    - `LoggerInterceptor` 日志拦截器
- `ActivityManager` Activity管理类
- `RxBus` RxJava实现的事件订阅处理类