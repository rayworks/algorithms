#!/bin/sh

out_file="solutions.txt"
# original leetcode problems:
grep -E -R " [0-9][0-9]*\.? [A-Z]"  ./src/main/java > $out_file

# ./src/main/java/org/sean/tree/MaxWidthBinaryTree.java: * 662. Maximum Width of Binary Tree
# filter names
cat $out_file | cut -f 2,3 -d ':' | grep -E "[0-9]+\.? [-,',a-z,A-Z,0-9, ]+" -o  > titles.txt

# filter paths
#cat $out_file | grep -E "\.[a-z,A-Z,0-9,/]*\.java" -o | wc

cat $out_file | cut -f 1 -d ':' > paths.txt

rm -f $out_file

./readme_gen.py > README.md

rm -f titles.txt
rm -f paths.txt