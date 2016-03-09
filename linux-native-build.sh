#!/bin/bash

srcdir="$1"
destdir="$2"

set -o pipefail
set -Eex

cd "$srcdir"
install -d build
cd build
cmake "$srcdir"
make
install -d "$destdir"
cp src/libfann.so "$destdir/."

