#!/bin/sh

jenkins_home=/var/jenkins_home

cd $jenkins_home
# 创建
if [ ! -e volumes/usst_website ]; then
  mkdir -p volumes/usst_website
fi
# shellcheck disable=SC2164
#cd $jenkins_home/workspace
#ls

# 获取当前路径 /var/jenkins_home/workspace/usst_website/back
#if [ -z "$BASE_DIR" ]; then
#	BASE_DIR=${0%/*}
#fi



#export DATA_DIR=${BASE_DIR}/datadir
#export CONF_DIR=${BASE_DIR}
#
#_mkdir(){
#	if [ -n "$1" -a ! -d "$1" ]; then
#		mkdir $1
#	fi
#}
#
#_mkdir ${CONF_DIR}/app
#
#if [ ! -e "${CONF_DIR}/app/dist" ];
#then
#	echo "${CONF_DIR}/app目录下没有前端编译后的dist目录" >&2
#	exit 1
#fi
#
#_mkdir ${CONF_DIR}/app/server
#
#if [ ! -e "${CONF_DIR}/app/server/navsmart.jar" ];
#then
#	echo "${CONF_DIR}/app/server目录下navsmart.jar\n如果jar包已经存在, 请改名为navsmart.jar\n不存在, 请复制到该目录下" >&2
#	exit 1
#fi
#
#if [ ! -e "${CONF_DIR}/app/server/application.yaml" ];
#then
#  echo "${CONF_DIR}/app/server/application.yaml 不存在, 使用模板创建"
#	cat > ${CONF_DIR}/app/server/application.yaml << EOF
#spring:
#  # 数据库连接的配置，需要配合docker-compose.yml
#  datasource:
#    url: jdbc:mariadb://mariadb:3306/nav_dev?allowMultiQueries=true
#    username: root
#    password: 123456
#  data:
#    mongodb:
#      uri: mongodb://root:123456@mongodb:27017/test?authSource=admin&authMechanism=SCRAM-SHA-1
#
## 日志输出配置
#logging:
#  file:
#    name: navsmart.log
#
#navsmart:
#  # es数据库连接的配置
#  es:
#    hostname: es
#  # ip过滤配置
#  ip-check:
#    #是否开启ip过滤,默认开启
#    check_enable: true
#    #需要进行ip过滤的请求
#    path:
#      - /api/building/**
#    #ip白名单 ipv4形式
#    ip_whitelist:
#      - 127.0.0.1
#      - 0:0:0:0:0:0:0:1
#debug: false
#EOF
#  echo "默认的application.yaml创建完成";
#fi
#
#if [ ! -e "${CONF_DIR}/es-plugins" ];
#then
#	echo "${CONF_DIR}/es-plugins 目录不存在,尝试查找es-plugins.tgz压缩包"
#	tar -xzf ${CONF_DIR}/es-plugins.tgz -C ${CONF_DIR}
#	if [ "$?" != "0" ];then
#		echo "${CONF_DIR}/es-plugins.tgz不存在.请手动复制es-plugins.tgz或es-plugins到\"${CONF_DIR}\"目录下" >&2
#		exit 1
#	else
#		echo "解压完成.生成es-plugins目录"
#	fi
#fi
#
#_mkdir ${DATA_DIR}
#_mkdir ${DATA_DIR}/mongodb-data
#_mkdir ${DATA_DIR}/mariadb-data
#
#sh -c "cd ${0%/*} && docker compose down && docker compose up -d"