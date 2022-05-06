#!/bin/bash
#simple demo deploy script

kubectl apply -f deployer.yaml

for i in `seq 1 3`
do
  kubectl get pods -n ragnarok
  sleep 1
done
kubectl get services -n ragnarok
