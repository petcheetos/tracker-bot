#!/usr/bin/env bash
set -e

if [ -z "$TOKEN" ]; then
  echo "Usage: TOKEN=<your_token> ./start.sh"
  exit 1
fi

echo "=== 1) CI ==="
docker-compose up --build --exit-code-from ci ci

echo "=== 2) Starting runtime-services ==="
docker-compose up -d scrapper bot

echo "scrapper → localhost:8080, bot → localhost:8090"
