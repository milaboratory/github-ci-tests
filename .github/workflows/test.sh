#!/bin/bash

JSON='{
  "value": "{%0A  \"current-version\": \"2.5.7-19-master\",%0A  \"current-tag\": \"\",%0A  \"current-sha\": \"b6fcb3d998dec91be33ece28e306c833c9f271e6\",%0A  \"previous-version\": \"2.5.7\",%0A  \"previous-tag\": \"v2.5.7\",%0A  \"previous-sha\": \"5f9c5807df711a8e057a09da2d46f9f1cfc97f56\",%0A  \"latest-version\": \"2.5.7\",%0A  \"latest-tag\": \"v2.5.7\",%0A  \"latest-sha\": \"5f9c5807df711a8e057a09da2d46f9f1cfc97f56\",%0A  \"is-release\": \"false\",%0A  \"is-branch-head\": \"true\",%0A  \"is-latest-version\": \"false\",%0A  \"is-latest-major\": \"false\"%0A}"
}'

echo "$JSON" 

