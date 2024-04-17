#!/usr/bin/env bash

# pre-requisites
#   brew install docker minikube helm
#   brew install jq yq bat watch # optional
#

minikube --driver docker \
    --nodes 1 \
    --cpus=no-limit --memory=no-limit \
    --kubernetes-version=v1.30.0-rc.2 \
    --container-runtime=docker \
    --profile=manifest \
    start

# switch to cluster profile by default w/ minikube commands
minikube profile manifest

# kubectl config set-context --current --namespace jenkins
