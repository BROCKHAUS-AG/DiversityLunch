#!/bin/bash

# Recreate config file
rm -rf ./config/app-config.js
touch ./config/app-config.js

# Add assignment
echo "window.appConfig = {" >> ./config/app-config.js

# Read each line in .env file
# Each line represents key=value pairs
while read -r line || [[ -n "$line" ]];
do
  # Split env variables by character `=`
  if printf '%s\n' "$line" | grep -q -e '='; then
    varname=$(printf '%s' "$line" | sed -e 's/=.*//')
    varvalue=$(printf '%s' "$line" | sed -e 's/^[^=]*=//')
  fi

  # Read value of current variable if exists as Environment variable
  value=$(printf '%s\n' "${!varname}")
  # Otherwise use value from .env file
  [[ -z $value ]] && value=${varvalue}

  # Append configuration property to JS file
  echo "  $varname: \"$value\"," >> ./config/app-config.js
done < .env

echo "}" >> ./config/app-config.js

echo finished
cat ./config/app-config.js
cat .env
