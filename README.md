#### 创建一个MAVEN空项目

![spring1](/home/amu/IdeaProjects/myspringmvc/img/spring1.png)



---

#### 在`pom.xml`中追加依赖库和maven打包命令

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>myspringmvc</groupId>
    <artifactId>myspringmvc</artifactId>
    <packaging>war</packaging>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-core -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>5.1.5.RELEASE</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.1.5.RELEASE</version>
        </dependency>

    </dependencies>
    <build>
        <finalName>myspring</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.2.2</version>
                <configuration>
                    <webResources>
                        <resource>
                            <!-- this is relative to the pom.xml directory -->
                            <directory>src/myspring/WEB-INF</directory>
                            <targetPath>WEB-INF</targetPath>
                        </resource>
                    </webResources>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

* 配置参考网址

  http://maven.apache.org/plugins/index.html

  https://mvnrepository.com/search?q=spring



  ---

#### 在src目录下新建如下文件

```sh
xx@xx:~/IdeaProjects/myspringmvc/src$ tree myspring/
myspring/
└── WEB-INF
    └── web.xml

1 directory, 1 file

```

* 其中`web.xml`文件内容如下：

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  
  <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                        http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
    version="3.1">
  
      <listener>
          <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
      </listener>
  
      <context-param>
          <param-name>contextConfigLocation</param-name>
          <param-value>classpath:app-context.xml</param-value>
      </context-param>
  
      <servlet>
          <servlet-name>bbbb</servlet-name>
          <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
          <init-param>
              <param-name>contextConfigLocation</param-name>
              <param-value>classpath:myapp-servlet.xml</param-value>
          </init-param>
          <load-on-startup>1</load-on-startup>
      </servlet>
  
      <servlet-mapping>
          <servlet-name>bbbb</servlet-name>
          <url-pattern>/*</url-pattern>
      </servlet-mapping>
  
   </web-app>
  ```

  ---

#### 新建资源文件和Java类，目录结构如下：

```
xx@xx:~/IdeaProjects/myspringmvc/src$ tree main
main
├── java
│   └── com
│       └── amu
│           └── Controllers
│               └── MyIndex.java
└── resources
    ├── app-context.xml
    └── myapp-servlet.xml

5 directories, 3 files

```

* `app-context.xml`内容如下：

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/mvc"
         xmlns:context="http://www.springframework.org/schema/context"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
      <mvc:annotation-driven/>
      <context:component-scan base-package="com.amu" />
  </beans>
  ```

* `myapp-servlet.xml`内容如下：

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
  </beans>
  ```

* `MyIndex.java`内容如下

  ```java
  package com.amu.Controllers;
  
  import org.springframework.stereotype.Controller;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.ResponseBody;
  
  @Controller
  public class MyIndex {
  
      @RequestMapping("index")
      public @ResponseBody String loadIndex(){
          return "this is my first spring";
      }
  }
  
  ```

---

#### 编译和打包

* 编译

  直接在`IDEA`中点击`builder`

* 打包

  切换到项目目录下执行

  `mvn package`

  ![spring2](/home/amu/IdeaProjects/myspringmvc/img/spring2.png)

此时可以看到在`target`目录下生成了`myspringmvc.war`文件，将该文件放到`tomcat`目录下就行，重启服务，在浏览器访问