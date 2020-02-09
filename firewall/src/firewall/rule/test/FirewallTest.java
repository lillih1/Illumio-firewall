package firewall.rule.test;

import firewall.rule.Firewall;
import firewall.rule.impl.FirewallImpl;

public class FirewallTest {
	
	public static void main(String[] argc)
	{
		String filename = "src/firewall/rule/test/fw.csv";
		
		Firewall firewall = new FirewallImpl(filename);
		
		System.out.println(firewall.accept_packet("inbound", "udp", 53, "192.168.2.1")); // true
		System.out.println(firewall.accept_packet("inbound", "udp", 53, "192.168.2.2")); // true
		System.out.println(firewall.accept_packet("inbound", "udp", 53, "192.168.2.6")); // false

		System.out.println(firewall.accept_packet("inbound", "tcp", 669, "123.45.56.80")); // false
		System.out.println(firewall.accept_packet("inbound", "tcp", 670, "123.45.56.80")); // true
	
		System.out.println(firewall.accept_packet("inbound", "tcp", 670, "123.45.56a.80")); // false
	}
}

