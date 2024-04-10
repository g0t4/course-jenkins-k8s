#!/usr/bin/env bash

minikube --driver docker \
    --nodes 1 \
    --cpus=no-limit --memory=no-limit \
    --kubernetes-version=v1.29.3 \
    --container-runtime=docker \
    --profile=manifest \
    start

# switch to cluster profile by default w/ minikube commands
minikube profile manifest
