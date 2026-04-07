# Java 版本 Android 应用 - 快速开始指南

## 项目特点

这是 **Java 版本**的 Android 豆包 AI 助手应用，与之前的 Kotlin 版本功能完全相同，但使用 Java 语言开发。

## 三步编译安装

### 1️⃣ 配置 API Key

编辑 `app/src/main/java/com/example/doubaoapp/viewmodel/ChatViewModel.java`：

```java
// 配置你的 API Key
private final String apiKey = "ced6e412-98ed-4aa5-aa64-dc5b0a5a61b1";

// 模型名称，根据你开通的模型修改
private final String modelName = "doubao-pro-32k";
```

### 2️⃣ 编译 APK

使用命令行：
```bash
cd android-doubao-java
./gradlew assembleDebug
```

或使用 Android Studio：点击 Run 按钮

### 3️⃣ 安装到手机

```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

如果用 Android Studio，会自动安装。

---

## Android Studio 使用

1. **打开项目**: File → Open → 选择 `android-doubao-java` 文件夹
2. **等待同步**: 第一次打开会自动下载依赖（几分钟）
3. **连接设备**: 用 USB 连接手机或启动模拟器
4. **点击运行**: 绿色三角形按钮

## 常用命令

```bash
# 查看已连接的设备
adb devices

# 清理项目
./gradlew clean

# 重新编译
./gradlew assembleDebug

# 安装到指定设备
adb -s 设备ID install app/build/outputs/apk/debug/app-debug.apk

# 查看日志
adb logcat | grep DoubaoApp
```

## 获取模型 ID

1. 访问：https://console.volcengine.com/ark/
2. 左侧菜单 → 模型广场
3. 找到你想用的模型（如"豆包 Pro 32K"）
4. 模型详情页可以看到模型 ID，例如：`doubao-pro-32k`

## 测试 API

安装应用后：
1. 打开应用
2. 在输入框输入：你好
3. 点击发送
4. 等待豆包回复

## Java vs Kotlin 对比

| 特性 | Java 版本 | Kotlin 版本 |
|-----|----------|------------|
| 代码行数 | 更多 | 更少 (-40%) |
| 空指针安全 | ❌ | ✅ |
| 协程支持 | ❌ | ✅ |
| 扩展函数 | ❌ | ✅ |
| 学习曲线 | 简单 | 中等 |
| 官方推荐 | ⚠️ | ✅✅✅ |
| 适用场景 | 老项目维护 | 新项目开发 |

## 技术栈

- **语言**: Java
- **架构**: MVVM
- **网络**: Retrofit 2 + OkHttp 3
- **JSON**: Gson
- **UI**: Material Design Components
- **异步**: Retrofit Callback

## 需要帮助？

- 查看 README.md 了解详细说明
- 检查 ChatViewModel.java 中的配置
- 查看 Android Studio 的 Logcat 日志

---

**提示**: 首次编译可能需要 5-10 分钟下载依赖，请耐心等待。
