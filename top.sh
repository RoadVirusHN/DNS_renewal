while sleep 1; do top -b n1 | (head -n 15 && echo "---------") >> top-output.txt ; done