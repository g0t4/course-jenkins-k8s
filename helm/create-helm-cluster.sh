#!/usr/bin/env bash

# https://helm.sh/docs/intro/install
# brew install helm

_name="helm"
minikube --driver docker \
    --nodes 1 \
    --cpus=no-limit --memory=no-limit \
    --kubernetes-version=v1.30.0-rc.2 \
    --container-runtime=docker \
    --profile=${_name} \
    start

# switch to cluster profile by default w/ minikube commands
minikube profile ${_name}

# kubectl config set-context --current --namespace jenkins
