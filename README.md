# 博客系统

原项目地址：
- [https://gitee.com/WoGxing/blog-system](https://gitee.com/WoGxing/blog-system)
- [https://gitee.com/WoGxing/blog-vue](https://gitee.com/WoGxing/blog-vue)

## 运行
构建前端 blog-vue
```
npm install
npm fund
npm run build
```

将dist目录下的文件复制到后端的static目录下

构建后端 blog-server
```
./mvnw clean package -DskipTests
java -jar target/blog-system-0.0.1-SNAPSHOT.jar
```
