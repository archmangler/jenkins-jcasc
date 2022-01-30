#!/bin/bash

COVENANT_USER="left"
COVENANT_PASSWORD="right"
CONF=$(echo "'"{\"username\":\"$COVENANT_USER\", \"password\":\"$COVENANT_PASSWORD\"}"'")
echo $CONF
