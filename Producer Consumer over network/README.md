# Producing and consuming over a network

This program it is possible to run the producer, client, and storage unit on different machines using TCP/IP for the network communication.

Given is a fixed amount of storage space. Many producer is producing items which will be stored in the storage space, and many consumer removes items from the storage space and consumes the items. The producer can only produce items if there is space in the storage space to store it. The consumer can only take items if they’re storage space has enough items. The producer produces n items each time the production starts. The consumer produces k items each time the production starts.

