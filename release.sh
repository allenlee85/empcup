#!/bin/sh

lein clean
lein cljsbuild once min

cp resources/public/css/*.css docs/css/
cp resources/public/js/compiled/empcup.js docs/js/compiled/
cp resources/public/index.html docs/

