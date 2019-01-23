#!/bin/bash
for i in {1..282}; do
    awk NR=="$i" test.json > "$i.json"
done
