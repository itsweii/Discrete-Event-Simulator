# Discrete Event Simulator
Event Queuing Simulation aims to model a real-life supermarket checkout point with both human servers and self-checkout counters for customers.

There are two types of servers: human servers and self-checkout counters.
Human Server
Human servers (or simply called servers) are allowed to take occasional breaks. When a server finishes serving a customer, there is a chance that the server takes a rest for a certain amount of time. During the break, the server does not serve the next waiting customer. Upon returning from the break, the server serves the next customer in the queue (if any) immediately.

Self-Checkout Counters
As with all modern supermarkets, there are now a number of self-checkout counters being set up. In particular, if there are k human servers, then the self-checkout counters are identified from k + 1 onwards.

All self-checkout counters share the same queue.
Unlike human servers, self-checkout counters do not rest.
When we print out the wait event, we say that the customer is waiting for the self-checkout counter k + 1, even though this customer may eventually be served by another self-checkout counter.
Use the program Main.java provided to test the program with a default service time of 1.0. Program can also be tested against test cases where the service times could be different when serving different customers.


Input the test cases using vim: $ java Main < 1.in <bt/>
Input:<bt/>
The first line of input consists: number of human servers, self-checkouts, maximum queue length, probablity of rest<bt/>
The following line: arrival time of customers<bt/>
