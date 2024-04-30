helm upgrade --install -n jenkins jenkins1 jenkins/jenkins \
    --description 'deploy.sh' \
    --values values/additional-plugins.yaml \
    --values values/admin-pass.yaml \
    --values values/configscripts-dark-theme.yaml \
    --values values/jenkins-url.yaml \
    --values values/initscripts-disable-script-security.yaml \
    --values values/configscripts-versions-job.yaml \
    --values values/configscripts-welcome-message.yaml \
    --dry-run --debug
    # --dry-run + --debug returns proposed state w/o actually changing the release
