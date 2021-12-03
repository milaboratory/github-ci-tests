#!/usr/bin/env bash

set -o errexit
set -o nounset


dst_dir="${1}"
app_name="${2}"
target_platform="${3:-${RUNNER_OS:-}}"

echo "Fake App build was triggered for '${dst_dir}' (${target_platform})"

mkdir -p "${dst_dir}"

ext=""
case "${target_platform}" in
  "Linux")
    ext=".AppImage"
    ;;
  "Windows")
    ext=".exe"
    ;;
  "macOS")
    ext=".dmg"
    ;;
  "JavaScript")
    ext=".js"
esac

version="$(jq --raw-output --compact-output '.version' ./package.json)"
echo $'\0' > "${dst_dir}/${app_name}-${version}${ext}"
