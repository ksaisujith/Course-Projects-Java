# Handling text and zip files

Given are the following files:

               % ssh glados
               # ... password credentials
               % ls -l /home/fac/hpb/public_html/Lectures/Src_28/
               total 1253117
               -rw-r--r-- 1 hpb fac 1000000002 Oct 12 09:13 pi-billion.txt
               -rw-r--r-- 1 hpb fac  469229415 Oct 12 09:13 pi-billion.txt.gz
This file contains pi which is almost 1 GB size. This program counts how many even and odd numbers this file contains. The character ’.’ is not a number.

This program also reads a compressed/uncompressed file, if the file name is given as command-line. A compressed file name will end with ’.gz’. File name is read from stdin, if no file name is specified on the command line.
