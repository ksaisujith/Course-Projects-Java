# Finding a file

A java program which can be used like a simplified version of find. The manual page of the find program can be found [here](http://man7.org/linux/man-pages/man1/find.1p.html):
<br>
The syntax of the your find is:<br>

               find  starting_directory [-name name|-type (f|d)]|-date|-length]

The semantic is of the above syntax is:<br>

               $ find . -name thisName   # print all files with name:
                                         # thisName starting in current dir
               $ find . -type f          # print all files starting in current dir
               $ find . -type d          # print all files starting in current dir
               $ find . -type d -date    # print all directories starting in
               # current dir and the last modification time
               $ find . -type d -length  # print all directories starting in
                                         # current dir and length of the file
