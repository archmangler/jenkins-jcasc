#!/bin/bash
#Simple build script to create deployer image for Eqonex pipelines
#
#./build.sh <the semantic vesion id>

build_version=$1
docker build -t archbungle/jenkins-eqonex:"jcasc-$build_version" .
docker push archbungle/jenkins-eqonex:"jcasc-$build_version"
