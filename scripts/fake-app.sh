#!/usr/bin/env bash

set -o errexit
set -o nounset


dst_dir="${1}"
target_platform="${2:-${RUNNER_OS:-}}"

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
:> "${dst_dir}/fake-app-${version}${ext}"
