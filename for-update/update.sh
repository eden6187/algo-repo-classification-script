#!/bin/bash

# 반드시 source 명령어를 사용해서 작성해야 한다.

# First. you should create venv for script
python3 -m venv venv-for-script

#Second. you should activate your venv
SHELL_PATH=`pwd -P`
source $SHELL_PATH/venv-for-script/bin/activate

pip install --upgrade pip
pip install pyyaml

# Third. run script
python3 $SHELL_PATH/main.py

# Last. deactivate venv
deactivate