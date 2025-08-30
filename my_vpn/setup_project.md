# 项目设置说明

由于缺少gradle-wrapper.jar文件，需要手动设置项目。请按照以下步骤操作：

## 方法一：使用Android Studio

1. 用Android Studio打开项目目录 `D:\files\projects\my_vpn`
2. Android Studio会自动下载gradle wrapper和依赖
3. 等待同步完成即可

## 方法二：手动下载Gradle Wrapper

1. 在项目根目录运行：
```bash
gradle wrapper --gradle-version 8.2
```

2. 或者从现有的Android项目复制gradle/wrapper/gradle-wrapper.jar文件

## 方法三：更简单的设置

如果上述方法不行，可以将gradle版本调整为更稳定的版本。

### 修改gradle/wrapper/gradle-wrapper.properties：

```properties
distributionBase=GRADLE_USER_HOME
distributionUrl=https\://services.gradle.org/distributions/gradle-7.6-bin.zip
distributionPath=wrapper/dists
zipStorePath=wrapper/dists
zipStoreBase=GRADLE_USER_HOME
```

### 修改build.gradle中的Android Gradle Plugin版本：

```gradle
classpath 'com.android.tools.build:gradle:7.4.2'
```

## 项目功能

项目已经包含以下功能：

1. **节点管理**：使用Room数据库存储Trojan节点
2. **剪贴板支持**：从剪贴板读取trojan://链接或订阅
3. **简洁界面**：Material Design风格
4. **VPN服务**：基于Android VpnService

## 使用方法

1. 复制trojan://链接到剪贴板
2. 在应用中点击"节点管理"
3. 点击"从剪贴板添加"
4. 选择节点后返回主界面
5. 点击"连接"开始VPN连接