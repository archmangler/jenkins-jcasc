#!/bin/bash
#Simple build script to save typing:
#
#./build.sh <the semantic vesion id>

build_version=$1
docker build -t archbungle/jenkins:"jcasc-$build_version" .
docker push archbungle/jenkins:"jcasc-$build_version"
