# 灵喵工坊 MES —— 后端生产镜像
#
# 构建上下文为 deploy-prod/，因此这里用相对路径复制 jar。
# 构建前请先把编译产物放到 deploy-prod/server/neko-workshop-server.jar
# 基础镜像源：服务器直连 docker.io 会超时，默认走华为云 SWR 镜像。
# 如需改回官方源，构建时传入 --build-arg BASE_IMAGE=eclipse-temurin:25-jre
ARG BASE_IMAGE=swr.cn-north-4.myhuaweicloud.com/ddn-k8s/docker.io/library/eclipse-temurin:25-jre
FROM ${BASE_IMAGE}

# 时区与字符集
ENV TZ=Asia/Shanghai \
    LANG=C.UTF-8

# 以非 root 用户运行，降低容器逃逸风险（UID/GID 取 10001，避开基础镜像已有账号）
RUN groupadd -r -g 10001 mes && useradd -r -u 10001 -g mes mes \
    && mkdir -p /neko-workshop-server/logs \
    && chown -R mes:mes /neko-workshop-server

WORKDIR /neko-workshop-server

# 后端 Jar（构建前放好）
COPY --chown=mes:mes server/neko-workshop-server.jar app.jar

USER mes

# 启动参数：JAVA_OPTS 可通过 compose 覆盖（内存等）
ENV JAVA_OPTS="-Xms1024m -Xmx2048m -Djava.security.egd=file:/dev/./urandom"

EXPOSE 48080

# 用 exec 形式配合 sh -c，确保 java 进程能正确接收停止信号
CMD ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]
