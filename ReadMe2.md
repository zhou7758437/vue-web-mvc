#开发环境要求
    1.JDK 1.8
    2.Node V8.9以上
    3.Java开发IDE建议使用IntelliJ

## 整个项目编译
```aidl
mvn clean install -DskipTests
```
## 项目版本升级
```aidl
mvn build-helper:parse-version versions:set -DnewVersion=${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.nextIncrementalVersion} versions:commit
```

## Server端启动
```$xslt
1.进入vue-web-mvc-web模块
2.自行mvn spring-boot:run命令
```

## 前端(开发阶段)
```$xslt
1.进入vue-web-mvc-web/src/main/resource/vue-web-mvc-web目录
2.在控制台运行npm run dev命令
3.访问localhost:3333 可以打开前端页面
```
 
 ## 前端(发布阶段)
 ```$xslt
 1.进入vue-web-mvc-web/src/main/resource/vue-web-mvc-web目录
 2.在控制台运行npm run deploy命令
 ```
 
 ### PS:
    1.前端和后台同时启动可以进行前后端联调
    2.



