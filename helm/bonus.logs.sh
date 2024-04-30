kubectl logs pods/jenkins1-0 --all-containers --prefix --follow
# watch for output from all containers (each line prefix containers container name), use this and then run ./deploy.sh to see what is logged during a given upgrade
