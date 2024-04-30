#!/usr/bin/env fish

# TLDR: diff output of "helm get all ..." and "helm upgrade --install --dry-run --debug ..." (in bonus.deploy.dry-run.sh)
# helm get = current release state
#    i.e. values, manifests, notes etc
diff_two_commands 'helm get all -n jenkins jenkins1' './bonus.deploy.dry-run.sh'

# my fish shell implementation of diff_two_commands:
#   https://github.com/g0t4/dotfiles/blob/1823d5d26a9915c59c507c3fb19b26ae4c7640d9/fish/load_last/diff-specific.fish#L49-L60
#   basically run both commands and pass output to icdiff command
