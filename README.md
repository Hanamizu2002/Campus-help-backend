# Campus-Help-backend

这是一个基于SpringBoot的校园帮系统的后端程序，使用Maven进行构建，并通过Docker进行容器化部署。

## 先决条件

在开始之前，请确保你已经安装或部署了以下软件：

- Docker
- Git
- MySQL
- Tencent COS

## 构建Docker镜像

1. 克隆项目仓库：

   ```sh
   git clone https://github.com/Hanamizu2002/Campus-help-backend.git
   cd Campus-help-backend
   ```

2. 修改application.yml文件，将数据库连接信息修改为你自己的数据库信息。
   ```yaml
    url: jdbc:mysql://localhost:3306/campus_help?characterEcoding=utf-8&serverTimezone=UTC
    username: root
    password: MQTG123123
   ```

3. 填写cos配置
   ```yaml
   cos:
   secretId: your-secret-id
   secretKey: your-secret-key
   region: ap-shanghai
   ```

4. 构建Docker镜像：

   ```sh
   docker build -t hanamizu/campushelpbackend:latest .
   ```

## 运行Docker容器

使用以下命令运行Docker容器：

```sh
docker run -d -p 8080:8080 --name campushelpbackend hanamizu/campushelpbackend:latest
```

这会将容器中的8080端口映射到主机的8080端口。

## 停止和删除容器

要停止运行中的容器，请使用：

```sh
docker stop campushelpbackend
```

要删除停止的容器，请使用：

```sh
docker rm campushelpbackend
```
