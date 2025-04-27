#!/usr/bin/env bash
set -euo pipefail

if [[ -z "${TOKEN:-}" ]]; then
  echo "Usage: TOKEN=<telegram_bot_token> ./start.sh"
  exit 1
fi

echo "Building and runnint tests..."
docker compose build ci
docker compose run --rm ci

echo "Running services..."
docker compose up -d postgresql liquibase scrapper bot

echo "Done"
