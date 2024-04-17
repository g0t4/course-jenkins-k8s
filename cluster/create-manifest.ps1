
# pre-requisites
#   winget install Docker.DockerDesktop  Kubernetes.minikube  Helm.Helm
#   winget install jqlang.jq  MikeFarah.yq  sharkdp.bat  # optional
#

minikube --driver docker `
    --nodes 1 `
    --cpus=no-limit --memory=no-limit `
    --kubernetes-version=v1.29.4 `
    --container-runtime=docker `
    --profile=manifest `
    start

# switch to cluster profile by default w/ minikube commands
minikube profile manifest

# kubectl config set-context --current --namespace jenkins
