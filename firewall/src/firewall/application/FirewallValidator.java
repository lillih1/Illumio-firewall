package firewall.application;

import firewall.Firewall;
import firewall.impl.FirewallImpl;

public class FirewallValidator {
	
	public static void main(String[] argc)
	{
		String filename = "src/fw.csv";
		
		Firewall firewall = new FirewallImpl(filename);
		
		System.out.println(firewall.accept_packet("inbound", "udp", 53, "192.168.2.1")); // true
		System.out.println(firewall.accept_packet("inbound", "udp", 53, "192.168.2.2")); // true
		System.out.println(firewall.accept_packet("inbound", "udp", 53, "192.168.2.6")); // false

		System.out.println(firewall.accept_packet("inbound", "tcp", 669, "123.45.56.80")); // false
		System.out.println(firewall.accept_packet("inbound", "tcp", 670, "123.45.56.80")); // true
	
		System.out.println(firewall.accept_packet("inbound", "tcp", 670, "123.45.56a.80")); // false
	}
}

