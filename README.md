```xml
<!--spring boot starter-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-freemarker</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-quartz</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-websocket</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!--生成api文档-->
        <dependency>
            <groupId>org.springframework.restdocs</groupId>
            <artifactId>spring-restdocs-mockmvc</artifactId>
            <scope>test</scope>
        </dependency>

        <!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>   <!--是否引入该项目依赖的父项目-->
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
        </dependency>
```

### 一、项目包结构

#### 1.1 
```
.
├── java
│   └── com
│       └── dist
│           └── ars
│               ├── ArsServiceApplication.java
│               │   └── 项目主启动类
│               │
│               ├── ServletInitializer.java
│               │   └── 配置项目以Tomcat方式启动（相当于web.xml文件）
│               │
│               ├── aop
│               │   └── 存放aop相关，例如log
│               │
│               ├── config
│               │   └── 存放配置类
│               │
│               ├── dao
│               │   └── 与数据库进行交互（使用SpringDataJpa）
│               │
│               ├── domain
│               │   └── 对dao层的数据进行组装
│               │
│               ├── remote  由service层远程调用第三方服务
│               │   │
│               │   ├── dms
│               │   │   └── 远程调用电子资料管理系统（Data management system）
│               │   │
│               │   ├── ims
│               │   │   └── 远程调用指标管理系统（Indicator management system）
│               │   │   
│               │   └── mms
│               │       └── 远程调用模型管理系统（Model management system）
│               │   
│               ├── service
│               │   └── 由web层进行远程调用
│               │   
│               └── util
│                   └── 存放工具类（系统扩大的话，可以扩展成一个模块）
└── resources
    ├── application.yml
    │   └── 主配置文件
    │
    ├── spring-dubbo.xml
    │   └── dubbo3.0.x必须使用这种配置方式（为了集成dasc）
    │
    ├── dozer
    │   └── dozer映射配置文件
    │   
    └── logback-spring.xml
        └── 配置logback日志打印输出格式..

```

#### 1.2 dgpnr-server-api

```
.
└── java
    └── com
        └── dist
            └── ars
                ├── exceptions
                │   └── 存放当前系统的异常
                │
                ├── model
                │   ├── dto
                │   │   └── 用于web层与service层之间传递
                │   │   
                │   ├── entity
                │   │   └── 用于service层、domain层、dao层交互
                │   │
                │   ├── request
                │   │   └── 用于前端与controller层交互
                │   │
                │   └── response
                │       └── vo
                │           └── 返回前端的结果
                │
                └── service
                    └── 存放service的接口

```




### 

分析：https://blog.csdn.net/mj158518/article/details/51228649

实现：https://blog.csdn.net/mz4138/article/details/81814546



### 1.4 rbac系统

dmn层的作用就是隐藏主外键的关系，所以涉及到主外键的东西，可以像下面这样操作

![](https://ws4.sinaimg.cn/large/006tNc79ly1g2bh4oek5uj30qi09g3zi.jpg)

hibernate的懒加载是在用的时候才加载（for），而get操作并不会让他进行加载
