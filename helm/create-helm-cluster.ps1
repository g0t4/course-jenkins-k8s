
# https://helm.sh/docs/intro/install
# winget install Helm.Helm

$name = "helm"

minikube --driver docker `
    --nodes 1 `
    --cpus=no-limit --memory=no-limit `
    --kubernetes-version=v1.29.4 `
    --container-runtime=docker `
    --profile=$name `
    start

# switch to cluster profile by default w/ minikube commands
minikube profile $name

# kubectl config set-context --current --namespace jenkins
