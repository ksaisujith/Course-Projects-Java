# Producer Consumer

Given is a fixed amount of storage space. Many producer are producing items which will be stored in the storage space, and many consumer removes items from the storage space and consumes the items. The producer can only produce items if there is space in the storage space to store it. The consumer can only take items if they’re storage space has enough items. The producer produces n items each time the production starts. The consumer produces k items each time the production starts.

The producers, like the consumers can not be in busy loops. Each of them as to be notified by the other one.

n, k, how many consumer, how many producer, and the length of the storage is give s command line arguments.
