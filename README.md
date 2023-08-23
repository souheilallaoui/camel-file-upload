# camel-file-upload

Demo application showing file upload HTTP multipart/form-data upload using servlet in Camel. This simple route logs the number of attachments in the Camel Exchange.

### attachmentMultipartBinding
The option **attachmentMultipartBinding** binds the multipart form data to the Camel Exchange as attachments.
```java
from("servlet:multipart?servletName=FileUploadServlet&attachmentMultipartBinding=true")
    .process(this::getNumberOfAttachments)
    .log("Number of attachments: ${header.numberOfAttachments}");
```

To run the application, run the command `mvn clean spring-boot:run`

## Issue
The route works correctly when using the **tomcat** embedded web server, but it doesn't work when using the **undertow** embedded web server.

To switch from undertow to tomcat, comment the **spring-boot-starter-undertow** dependency and the **spring-boot-starter-tomcat** exclusion as follows:

```xml
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
      <!-- <exclusions>
        <exclusion>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
      </exclusions> -->
    </dependency>
    <!-- <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-undertow</artifactId>
    </dependency> -->
```