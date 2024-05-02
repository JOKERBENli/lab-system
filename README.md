![图片1](https://github.com/JOKERBENli/lab-system/assets/51358679/a2f4954a-f25b-452f-8a2b-b9ad76a3a80f)
前端部署

1.解压IEMS-vue-springboot.rar
npm i vue-router
npm i pinia-plugin-persistedstate
npm i element-plus
npm i vue3-particles
npm i tsparticles-slim
npm install --save @antv/l7
npm install --save @antv/l7-maps
npm i axios
执行
npm run dev

docker部署
安装Docker
1、安装：
yum install docker

2、启动/停止/重启docker服务
service docker start
service docker stop
service docker restart

3、查看docker版本信息
docker version

4、运行helloword，因为不存在此镜像，docker会自动下载运行本镜像
docker run hello-world

5、查看所有docker镜像
docker images

二、安装Nginx
1、拉取Nginx镜像文件
docker pull nginx

3、创建并运行Nginx容器

docker run -d --name nginx01 -p 3344:80 nginx 命令详解：
docker run 启动一个镜像
-d 表示后台允许
--name nginx01 表示为当前容器起一个别名
-p 3344:80 表示将本机的3344端口映射到nginx镜像的80端口

4、查看正在运行的容器
docker ps

5、查看Nginx是否部署成功
curl localhost:3344

拓展 1、进入Nginx容器当中
docker exec -it nginx01 /bin/bash

2、在容器中查询出nginx的相关配置文件存放位置

whereis nginx
3、停止并移除Nginx容器

docker stop bedfd2a72585 #停止容器

docker rm bedfd2a72585 #移除容器
# bedfd2a72585表示容器的ID，即：CONTAINER ID
4、本地创建管理目录

mkdir -p /data/nginx
mkdir -p /data/nginx/www
mkdir -p /data/nginx/conf
mkdir -p /data/nginx/logs
5、将容器中的相应文件copy到刚创建的管理目录中

docker cp bedfd2a72585:/etc/nginx/nginx.conf /data/nginx/
docker cp bedfd2a72585:/etc/nginx/conf.d /data/nginx/conf/
docker cp bedfd2a72585:/usr/share/nginx/html/ /data/nginx/www/
docker cp bedfd2a72585:/var/log/nginx/ /data/nginx/logs/

注：docker cp bedfd2a72585中的 "bedfd2a72585" 为容器ID（docker ps可查看）
6、再次启动容器并作目录挂载

docker run --name nginx -p 80:80 -v /data/nginx/nginx.conf:/etc/nginx/nginx.conf -v /data/nginx/www/:/usr/share/nginx/html/ -v /data/nginx/logs/:/var/log/nginx/ -v /data/nginx/conf/:/etc/nginx/conf.d --privileged=true -d nginx
三、安装Mysql 1、下拉mysql镜像文件

docker pull mysql  #默认最新版本
docker pull mysql:xxx  #指定版本号
2、启动mysql容器 第一种：

docker run --name mysql01 -d -p 3310:3306 -e MYSQL_ROOT_PASSWORD=root mysql

命令详解：

# --name 自定义容器名称
# -d 后台运行
# -p 指定映射的端口号
# -e MYSQL_ROOT_PASSWORD=root 数据库密钥
第二种：

docker run --restart=always --privileged=true -d -v /home/mysql/data:/var/lib/mysql -v /home/mysql/conf:/etc/mysql/conf.d -v /home/mysql/my.cnf:/etc/mysql/my.cnf -p 3311:3306 --name mysql02 -e MYSQL_ROOT_PASSWORD=root mysql
四、部署SpringBoot项目 1、整合后端成Jar包并编写Dockerfile文件 2、Dockerfile内容详解

FROM java:8  #工程java版本
COPY *.jar /app.jar  #将所有的jar包整合为app.jar
EXPOSE 9099  #暴露后端端口号
ENTRYPOINT ["java","-jar","app.jar"]  #执行jar包
3、将文件上传到Linux服务器上面，必须放在同级目录一起！

4、构建镜像

docker build -t api .  #点千万别漏了，这里取名镜像为api，可以随便取名！
6、创建一个新的容器并运行.

docker run -d -p 9099:9099 --name httapi api
#将9099端口映射到9099端口，端口记得放开
#httapi为自定义容器名字
#api是镜像名字
7、查看正在运行的容器

docker ps

五、部署Vue项目 1、打包Vue工程并同时编写default.conf文件和Dockerfile文件

2、default.conf文件和Dockerfile文件详细

default.conf配置

server {
listen       80;
server_name  ip地址; # 修改为docker服务宿主机的ip

    location / {
        root   /usr/share/nginx/html;
        index  index.html index.htm;
        try_files $uri $uri/ /index.html =404;
    }
     location /api {
      proxy_pass http://ip地址:端口号/;
    }
 
    error_page   500 502 503 504  /50x.html;
    location = /50x.html {
        root   html;
    }
}

Dockerfile配置


# 基础镜像使用Nginx
FROM nginx
# 作者
MAINTAINER htt
# 添加时区到环境变量，亚洲，上海
ENV TimeZone=Asia/Shanghai
# 将前端dist文件中的内容复制到nginx目录
COPY dist  /usr/share/nginx/html/
# 用本地的nginx配置文件覆盖镜像的Nginx配置
COPY default.conf /etc/nginx/conf.d
# 暴露端口
EXPOSE 80
3、上传这三个文件到Linux服务器的同一个文件夹当中 4、构建镜像

docker build -t vue . #点千万别漏了，这里取名镜像为vue，可以随便取名！
5、创建一个新的容器并运行

docker run -d -p 8088:80 --name httvue vue
#将8088端口映射到80端口，端口记得放开
#httvue为自定义容器名字
#vue是镜像名字
