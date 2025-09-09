#!/bin/sh

# This script is executed by the ngrok-helper service.

# Make the script exit immediately if any command fails
set -e

# 1. Print a startup message
echo '[helper] Starting up...'

# 2. Install necessary packages (curl for making HTTP requests, jq for parsing JSON)
echo '[helper] Installing packages (curl, jq)...'
apk add --no-cache curl jq
echo '[helper] Packages installed successfully.'

# 3. Wait for the ngrok service to initialize and create the tunnel
echo '[helper] Waiting for 8 seconds for ngrok to initialize...'
sleep 8

# 4. Attempt to fetch the public URL from the ngrok API
echo '[helper] Attempting to fetch tunnels from ngrok API at http://ngrok:4040'
API_RESPONSE=$(curl --fail --show-error --silent http://ngrok:4040/api/tunnels)

# 5. Print the raw API response for debugging purposes
echo '[helper] Received API response:'
echo $API_RESPONSE

# 6. Parse the JSON response to extract the public URL
echo '[helper] Parsing response to find public URL...'
URL=$(echo $API_RESPONSE | jq -r '.tunnels[0].public_url')

# 7. Print the final public URL
echo '[helper] ---> Ngrok public URL is: ' $URL

# 8. Keep the container alive so we can inspect it if needed
echo '[helper] Script finished. Staying alive...'
tail -f /dev/null
