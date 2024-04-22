helm upgrade --install -n jenkins jenkins1 jenkins/jenkins \
    --description 'deploy.sh' \
    --values values/additional-plugins.yaml

helm history -n jenkins jenkins1
