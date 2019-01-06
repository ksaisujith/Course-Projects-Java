# Grep command

This program implements a subset of [grep’s](https://linux.die.net/man/1/grep) functionlity.
The grep utility searches any given input files, selecting lines that match one or more patterns. By default, a pattern matches an input line if the regular expression in the pattern matches the input line without its trailing newline. An empty expression matches every line. Each input line that matches at least one of the patterns is written to the standard output.


Patterns may consist of one or more lines, allowing any of the pattern lines to match a portion of the input.

Command-line arguments control the particalur funcitonlity of grep.


grep [OPTIONS] PATTERN [FILE...]  More than one argument might be used for a particular call.

this program implements following arguments:

      • -c
      • -l
      • -w
      • -q


