#!/usr/bin/env bash
set -x
docker build . -t ibar-payday-bank:3.0
docker tag ibar-payday-bank:1.0 gcr.io/winter-monolith-281508/ibar-payday-bank:3.0
docker push  gcr.io/winter-monolith-281508/ibar-payday-bank:3.0