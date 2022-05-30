 ![begin](C:\Users\Administrator\Desktop\1653656666(1).jpg)

## 前言

- 网络请求在我们开发中起的很大比重，有一个好的网络框架可以节省我们的开发工作量，也可以避免一些在开发中不该出现的bug

- Retrofit是一个轻量级框架，基于OkHttp的一个Restful框架
  
  ![图2](C:\Users\Administrator\Desktop\笔记\开源框架\retrofit\微信图片_20220527220425.png)
  
  今天我们就从<mark>使用方式和源码</mark>两个角度来分析下Retrofit v2.0
  
  Android体系课学习 之 开源框架学习
  
  [Android体系课学习 之 网络请求库OkHttp使用方式(附Demo)]()
  
  [Android体系课学习 之 网络请求库OkHttp看这一篇就够了]()
  
  [Android体系课学习 之 网络请求库Retrofit使用方式(附Demo)]()
  
  [Android体系课学习 之 网络请求库Retrofit看这一篇就够了]()
  
  [Android体系课学习 之 RxJava看这篇就够了]()
  
  [[Android体系课学习 之 RxJava操作符详解]()
  
  [[Android体系课学习 之 RxJava源码分析]()

## 1.目录

   <img src="file:///C:/Users/Administrator/Desktop/笔记/开源框架/retrofit/Retrofit%202.0目录介绍.png" title="" alt="目录" width="548">

## 2.简介

- 功能
  
  - 使用接口方法注解的方式配置网络请求参数

- 优点
  
  - 功能强大：支持同步和异步，支持自定义数据解析类型，常用如Json
  
  - 支持多平台，如Android，Java8等
  
  - 使用简单：通过注释的方式动态配置交易请求参数
  
  - 使用多种设计模式，对模块之间解耦，拓展性好
  
  - 目前最新版本支持协程的方式进行网络请求，不需要再指定使用同步还是异步方式

- 使用场景
  
  > 支持所有的网络请求场景，特别是请求次数比较频繁的场景，因为内部是基于OkHttp，而OkHttp内部使用了各种拦截器，且对请求有做缓存IO复用操作，防止短时间重复连接操作，浪费资源。

需要注意：Retrofit请求的本质是通过OkHttp发送网路请求，而Retrofit只是负责网络前期接口的封装

![图](C:\Users\Administrator\Desktop\笔记\开源框架\retrofit\retrofit网络流程.png)

## 3.具体使用

由于本文主要讲的是Retrofit的源码分析这块，具体使用方式可以看这：

[Android体系课学习 之 网络请求库Retrofit使用方式(附Demo)]()

> 如果只是为了查看Retrofit基本使用方式直接跳转到上面链接
> 
> 有想进阶的可以继续往下看

## 4.源码分析

 `本文分析的源码版本：`

` implementation 'com.squareup.retrofit2:retrofit:2.9.0'  

我们先来回忆下Retrofit的使用流程：

- 创建Retrofit实例

- 创建请求的实体类，并配置网络请求参数

- 创建请求实例并发送网络请求

- 响应数据处理

#### 4.1创建Retrofit实例

    

## 总结
