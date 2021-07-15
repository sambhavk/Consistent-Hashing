# Consistent-Hashing
Load Balancing is an important concept when it comes to distributed computing and consistent hashing is a way to balance the incoming load in an flexible and efficient manner so as to maintain a good load factor on each machine. [Read this for understanding significance of load factor -> https://stackoverflow.com/questions/10901752/what-is-the-significance-of-load-factor-in-hashmap]

Standard way to balance the request load is to hash incoming request and transfer it to mapped server. Now any system following this algorithm will not be able to achieve scalability or fault tolerance.

Why?

Suppose a system serving users across globe with Number of servers(N) = 4 -> [s1, s2, s3, s4]. Request r1 comes and gets mapped to server s([hash(r1.id) % 4])=s1. Suppose the server, s1, handling the load , l(s1), crashes then all cache data stored in server s1 will be lost. Now all requests will be mapped to server as per, s([hash(req.id) % 3]). Due to change in request allocation function, most of the cache data residing on servers will be useless.
Exactly the same case will be repeated in case of addition of new server.

That is because <b>*load is dependent on the number of servers*</b>
  
Enters Consistent Hash.</br>
Imagine a ring (consider this as our search space) and with N positions, [0, N-1], mapped on it. The nodes/servers are hashed and mapped in this search space i.e. position of server = [hash(Si) % N] where i -> [0, M-1]. All the incoming requests are mapped on the same search space as [hash(req.id) % N] and is served by next clockwise server. Now as <b>*load is dependent on position of servers on search space*</b> system can store cache data allowing it scalability and fault tolerance.

Fault Tolerance, in which case a machine crashes and Scalability, in which case machines needs to be added to process more requests. These two principles are allowed by Consistent Hashing and also request is mapped in a way such that the load is balanced.
