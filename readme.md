![图片1](https://github.com/JOKERBENli/lab-system/assets/51358679/a2f4954a-f25b-452f-8a2b-b9ad76a3a80f)
前端部署<br>

1.解压IEMS-vue-springboot.rar<br>
npm i vue-router<br>
npm i pinia-plugin-persistedstate<br>
npm i element-plus<br>
npm i vue3-particles<br>
npm i tsparticles-slim<br>
npm install --save @antv/l7<br>
npm install --save @antv/l7-maps<br>
npm i axios<br>
执行<br>
npm run dev<br>

docker部署<br>
安装Docker<br>
1、安装：<br>
yum install docker<br>

2、启动/停止/重启docker服务<br>
service docker start<br>
service docker stop<br>
service docker restart<br>

3、查看docker版本信息<br>
docker version<br>

4、运行helloword，因为不存在此镜像，docker会自动下载运行本镜像<br>
docker run hello-world<br>

5、查看所有docker镜像<br>
docker images<br>

二、安装Nginx<br>
1、拉取Nginx镜像文件<br>
docker pull nginx<br>

3、创建并运行Nginx容器<br>

docker run -d --name nginx01 -p 3344:80 nginx 命令详解：<br>
docker run 启动一个镜像<br>
-d 表示后台允许<br>
--name nginx01 表示为当前容器起一个别名<br>
-p 3344:80 表示将本机的3344端口映射到nginx镜像的80端口<br>

4、查看正在运行的容器<br>
docker ps<br>

5、查看Nginx是否部署成功<br>
curl localhost:3344<br>

拓展 1、进入Nginx容器当中<br>
docker exec -it nginx01 /bin/bash<br>

2、在容器中查询出nginx的相关配置文件存放位置<br>

whereis nginx<br>
3、停止并移除Nginx容器<br>

docker stop bedfd2a72585 #停止容器<br>

docker rm bedfd2a72585 #移除容器<br>

#bedfd2a72585表示容器的ID，即：CONTAINER ID<br>
4、本地创建管理目录<br>

mkdir -p /data/nginx<br>
mkdir -p /data/nginx/www<br>
mkdir -p /data/nginx/conf<br>
mkdir -p /data/nginx/logs<br>
5、将容器中的相应文件copy到刚创建的管理目录中<br>

docker cp bedfd2a72585:/etc/nginx/nginx.conf /data/nginx/<br>
docker cp bedfd2a72585:/etc/nginx/conf.d /data/nginx/conf/<br>
docker cp bedfd2a72585:/usr/share/nginx/html/ /data/nginx/www/<br>
docker cp bedfd2a72585:/var/log/nginx/ /data/nginx/logs/<br>

注：docker cp bedfd2a72585中的 "bedfd2a72585" 为容器ID（docker ps可查看）<br>
6、再次启动容器并作目录挂载<br>

docker run --name nginx -p 80:80 <br>
-v /data/nginx/nginx.conf:/etc/nginx/nginx.conf <br>
-v /data/nginx/www/:/usr/share/nginx/html/<br>
-v /data/nginx/logs/:/var/log/nginx/ <br>
-v /data/nginx/conf/:/etc/nginx/conf.d --privileged=true -d nginx<br>
三、安装Mysql 1、下拉mysql镜像文件<br>

docker pull mysql  #默认最新版本<br>
docker pull mysql:xxx  #指定版本号<br>
2、启动mysql容器 第一种：<br>

docker run --name mysql01 -d -p 3310:3306 -e MYSQL_ROOT_PASSWORD=root mysql<br>

命令详解：<br>

#--name 自定义容器名称<br>
#-d 后台运行<br>
#-p 指定映射的端口号<br>
#-e MYSQL_ROOT_PASSWORD=root 数据库密钥<br>
第二种：<br>

docker run --restart=always --privileged=true -d <br>
-v /home/mysql/data:/var/lib/mysql <br>
-v /home/mysql/conf:/etc/mysql/conf.d <br>
-v /home/mysql/my.cnf:/etc/mysql/my.cnf <br>
-p 3311:3306 --name mysql02 -e MYSQL_ROOT_PASSWORD=root mysql<br>
四、部署SpringBoot项目 1、整合后端成Jar包并编写Dockerfile文件 2、Dockerfile内容详解<br>

FROM java:8  #工程java版本
COPY *.jar /app.jar  #将所有的jar包整合为app.jar
EXPOSE 9099  #暴露后端端口号
ENTRYPOINT ["java","-jar","app.jar"]  #执行jar包<br>
3、将文件上传到Linux服务器上面，必须放在同级目录一起！<br>

4、构建镜像<br>

docker build -t api .  #点千万别漏了，这里取名镜像为api，可以随便取名！<br>
6、创建一个新的容器并运行.<br>

docker run -d -p 9099:9099 --name httapi api<br>
#将9099端口映射到9099端口，端口记得放开<br>
#httapi为自定义容器名字<br>
#api是镜像名字<br>
7、查看正在运行的容器<br>

docker ps<br>

五、部署Vue项目 1、打包Vue工程并同时编写default.conf文件和Dockerfile文件<br>

2、default.conf文件和Dockerfile文件详细<br>

default.conf配置<br>

server {<br>
listen       80;<br>
server_name  ip地址; # 修改为docker服务宿主机的ip<br>

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


#基础镜像使用Nginx<br>
FROM nginx<br>
#作者<br>
MAINTAINER htt<br>
#添加时区到环境变量，亚洲，上海<br>
ENV TimeZone=Asia/Shanghai<br>
#将前端dist文件中的内容复制到nginx目录<br>
COPY dist  /usr/share/nginx/html/<br>
#用本地的nginx配置文件覆盖镜像的Nginx配置<br>
COPY default.conf /etc/nginx/conf.d<br>
#暴露端口<br>
EXPOSE 80<br>


3、上传这三个文件到Linux服务器的同一个文件夹当中<br> 4、构建镜像<br>

docker build -t vue . #点千万别漏了，这里取名镜像为vue，可以随便取名！<br>
5、创建一个新的容器并运行<br>

docker run -d -p 8088:80 --name httvue vue<br>
#将8088端口映射到80端口，端口记得放开<br>
#httvue为自定义容器名字<br>
#vue是镜像名字<br>