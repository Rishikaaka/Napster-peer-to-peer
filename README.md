# Napster-peer-to-peer
Java RMI
The Java Remote Method Invocation (RMI) system allows an object running in one Java virtual
machine to invoke methods on an object running in another Java virtual machine. RMI provides for remote
communication between programs written in the Java programming language.
RMI applications often comprise two separate programs, a server and a client. A typical server
program creates some remote objects, makes references to these objects accessible, and waits for clients
to invoke methods on these objects. A typical client program obtains a remote reference to one or more
remote objects on a server and then invokes methods on them. RMI provides the mechanism by which
the server and the client communicate and pass information back and forth. Such an application is
sometimes referred to as a distributed object application.
The following illustration depicts an RMI distributed application that uses the RMI registry to obtain
a reference to a remote object. The server calls the registry to associate (or bind) a name with a remote
object. The client looks up the remote object by its name in the server's registry and then invokes a
method on it. The illustration also shows that the RMI system uses an existing web server to load class
definitions, from server to client and from client to server, for objects when needed.

a)Central Indexing Server: Central indexing server indexes the contents of all the peers
that register with it and search facility to peers.
● Each peer register the files of shared directory using registry method with peer id and
filename as parameters.when peer invokes register method all its files will be registered
by indexing server with index
● Each peer can search files when connected to index server and returns all the matching
peers to the requestor.
b)Peer: In Napster-style peer-to-peer file sharing system peer acts as both client and
server. When a peer search for file using the search method then indexing server returns the list
of the other peers that hold the file . the user can pick one such peer and the client then
connects to this peers and sends the requested file.


This creates Indexing server having following remote methods Insertintoregistry: insert peerId and filename into central indexing server Peersearch: search file name and returns peerid Insertportnumber: insert port number and peerid Returnportnumber: takes peerid and returns portnumber
