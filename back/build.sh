#!/bin/sh

export jenkins_home=/var/jenkins_home
export usst_website_back=$jenkins_home/workespace/usst_website/back
# shellcheck disable=SC2164
cd $jenkins_home
# 创建挂载目录
if [ ! -e volumes/usst_website ]; then
  mkdir -p volumes/usst_website
  # shellcheck disable=SC2164
  cd volumes/usst_website
  mkdir -p mysql nginx nacos
fi

export usst_website_volumes=$jenkins_home/volumes/usst_website

