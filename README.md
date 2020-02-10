# Illumio Coding Assessment - Firewall

# Implemenation Descriptions

 - **firewall.model** - This package contains three model classes: Range.java, IPRange.java and Rule.java.
    - Range.java: this class defines two integers min and max to define an integer range. A public method inRange() is to determine if the argument of the method is within the defined range.
    - IPRange.java: this class defines an IP address with an array of Ranges. A public method inRange() is to determine if the argument of the method is within the defined IP range. It throws a FirewallException if the passed in IP address is invalid.
    - Rule.java: this class defines a valid firewall rule, which contains a direction, protocol, a port range and an IP range.

  - **firewall.exception** - This package contains a customized exception FirewallException class.
  
  - **firewall.utils** - This package contains two class: Constants.java and Utils.java.
    - Constants.java: this class defines strings constants.
    - Utils.java: this class contains a utility method parseIP(). It parses a string IP address. It throws exception is IP address is invalid.
    
  - **firewall.rule** - This package defines an interface Firewall.java, which contains a public method accept_packet().
  
  - **firewall.rule.impl** - This package contains a class FirewallImpl.java. It is an implementation of the interface Firewall.java. 

    - Its constructor has an argument to pass an rule input file. In the constructor, firewall rules are read in and added to a private firewall rule list after the validation. If the input line is invalid for a firewall rule, the line is skipped. 
    - Its public method accept_packet() validates if a passed-in IP address satisfies one of defined firewalls.

  - **firewall.rule.tes** - This package contains a sample csv input file and a test class FirewallTest.java. When the program starts, it creates a Firewall object to process firewall rules in the input file. It then calls accept_packet() to validate if IP addresses satisfy the firewall rules.
  

# Possible improvements

There are some possible improvements that could be considered:

  - Performance improvements - We could sort firewall rules in such a way such that wider ranges come first to reduce the validation time.
    
  - If the rule size is huge (e.g. millions), we could arrange the rules differently. For example, for each direction and protocol combination, keep a list of valid port numbers. For each valid port number, keep a list of IP ranges. This could improve the performance since the port range is fixed [1, 65535]. Also, since rules are only processed once, this could reduce the processing time of accept_packet() greatly. 

# Order of potential teams:

1. Platform team

2. Policy team

3. Data team

