# Quote of the day

The server will return a quote of the day each time a client connects to the server using TCP connection. Multiple clients can connect at the same time

server program reads a set of quotes from a file. Server has to be started by hand. A client can connect to the server only after the file has been read. A client connection to the server will be either successful or failing. A randomly selected quote will be returned if the connection was successful.
