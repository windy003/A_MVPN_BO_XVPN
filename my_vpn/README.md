# Simple VPN

一个基于Android VpnService的简化版VPN应用，专门支持Trojan协议。

## 功能特性

- ✅ 从剪贴板读取订阅链接
- ✅ 支持Trojan协议
- ✅ 简洁的用户界面
- ✅ 一键连接/断开

## 使用说明

1. 复制Trojan订阅链接或单个trojan://链接到剪贴板
2. 打开应用，点击"粘贴订阅链接"按钮
3. 点击"连接"按钮开始VPN连接

## 支持的链接格式

- 单个Trojan链接：`trojan://password@server:port`
- Base64编码的订阅链接（包含trojan://链接）

## 系统要求

- Android 5.0 (API 21) 及以上版本
- VPN权限

## 编译说明

1. 确保安装了Android Studio
2. 导入项目
3. 同步Gradle
4. 编译并安装到设备

## 注意事项

- 这是一个简化版本，主要用于学习和测试
- 实际的网络代理功能需要集成相关的网络库
- 请遵守当地法律法规使用VPN服务

## 基于项目

本项目参考了A_xivpn项目的架构设计，进行了大幅简化。