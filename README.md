# 豆包 AI 助手 Android 应用 (Java 版本)

这是一个完整的 Android 应用，使用 **Java** 语言开发，通过 API 调用豆包大模型。

## 功能特性

- 🤖 调用豆包大模型进行对话
- 💬 支持多轮对话上下文
- 🎨 Material Design 3 设计
- 🚀 基于 Java + MVVM 架构
- 📱 Android 5.0+ (API 24+) 支持

## 项目结构

```
android-doubao-java/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/doubaoapp/
│   │   │   ├── api/           # API 接口定义
│   │   │   ├── model/         # 数据模型
│   │   │   ├── network/       # 网络请求配置
│   │   │   ├── viewmodel/     # ViewModel
│   │   │   ├── adapter/       # RecyclerView 适配器
│   │   │   └── MainActivity.java
│   │   └── res/
│   │       ├── layout/        # 布局文件
│   │       └── values/       # 资源文件
│   └── build.gradle
├── build.gradle
└── settings.gradle
```

## 使用前准备

### 1. 获取 API Key

如果你已经有火山引擎账号和 ARK_API_KEY，直接跳过此步。

如果没有，请：
1. 访问火山引擎控制台：https://console.volcengine.com/ark/
2. 注册/登录账号
3. 获取 API Key

### 2. 获取模型 ID

在火山引擎控制台的模型广场找到你要使用的模型，比如：
- `doubao-pro-32k`
- `doubao-pro-128k`
- `doubao-lite-4k`
- 其他已开通的模型

### 3. 配置应用

打开 `app/src/main/java/com/example/doubaoapp/viewmodel/ChatViewModel.java`，修改以下配置：

```java
// 配置你的 API Key
private final String apiKey = "你的API_KEY";

// 模型名称，根据你开通的模型修改
private final String modelName = "doubao-pro-32k";
```

## 编译和安装

### 使用 Android Studio

1. 打开 Android Studio
2. File → Open → 选择项目根目录
3. 等待 Gradle 同步完成
4. 点击 Run 按钮（或按 Shift + F10）
5. 连接 Android 设备或启动模拟器
6. 应用会自动安装到设备

### 使用命令行

```bash
# 进入项目目录
cd android-doubao-java

# 编译 APK（Debug 版本）
./gradlew assembleDebug

# APK 文件位置
# app/build/outputs/apk/debug/app-debug.apk

# 安装到已连接的设备
adb install app/build/outputs/apk/debug/app-debug.apk
```

## 技术栈

- **语言**: Java
- **架构**: MVVM (Model-View-ViewModel)
- **网络请求**: Retrofit 2 + OkHttp 3
- **JSON 处理**: Gson
- **异步处理**: Retrofit Callback
- **UI 组件**: Material Design Components
- **视图绑定**: DataBinding

## 主要功能说明

### API 调用流程

1. 用户输入消息
2. MainActivity 调用 ViewModel 的 `sendMessage()`
3. ViewModel 构建请求并调用 DoubaoApi
4. Retrofit 发送 HTTP 请求到豆包 API
5. 处理响应并更新 UI

### 代码说明

- **DoubaoApi.java**: 定义 API 接口
- **ChatRequest.java / ChatResponse.java**: 请求和响应数据模型
- **RetrofitClient.java**: 网络客户端配置
- **ChatViewModel.java**: 业务逻辑处理
- **MainActivity.java**: UI 交互
- **ChatAdapter.java**: 聊天消息列表适配器

## 注意事项

1. **API Key 安全**: 生产环境中不要将 API Key 硬编码在代码中，建议使用 BuildConfig 或安全存储
2. **网络权限**: 应用需要 INTERNET 权限（已在 AndroidManifest.xml 中声明）
3. **HTTPS**: API 使用 HTTPS 连接
4. **超时设置**: 网络请求超时设置为 30 秒
5. **模型选择**: 确保使用已开通的模型 ID

## 常见问题

### Q: 编译时提示 Gradle 版本不兼容？
A: 使用 Android Studio 打开项目，它会自动下载匹配的 Gradle 版本。

### Q: API 调用失败？
A:
1. 检查 API Key 是否正确
2. 检查模型 ID 是否已开通
3. 检查网络连接
4. 查看日志中的详细错误信息

### Q: 如何更换模型？
A: 修改 `ChatViewModel.java` 中的 `modelName` 变量。

### Q: 如何生成 Release APK？
A: 在 Android Studio 中 Build → Generate Signed Bundle / APK，或运行 `./gradlew assembleRelease`。

## Java vs Kotlin 版本

| 特性 | Java 版本 | Kotlin 版本 |
|-----|----------|------------|
| 代码行数 | 更多 | 更少 (-40%) |
| 空指针安全 | ❌ | ✅ |
| 协程支持 | ❌ | ✅ |
| 扩展函数 | ❌ | ✅ |
| 学习曲线 | 简单 | 中等 |
| 适用场景 | 老项目维护 | 新项目开发 |

## 许可证

MIT License

## 支持

如有问题，请检查：
1. Android Studio 日志
2. 网络连接
3. API Key 和模型配置

---

**祝使用愉快！** 🎉
