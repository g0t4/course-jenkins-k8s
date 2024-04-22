helm upgrade --install -n jenkins jenkins1 jenkins/jenkins \
    --description 'deploy.sh' \
    --values values/additional-plugins.yaml \
    --values values/admin-pass.yaml \
    --values values/configscripts-dark-theme.yaml \
    --values values/jenkins-url.yaml

# helm history -n jenkins jenkins1
