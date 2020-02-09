package firewall.model;

import firewall.exceptions.FirewallException;
import firewall.utils.Utils;

public class IPRange {
	private Range[] ranges;
	
	public IPRange(Range[] ranges)
	{
		this.ranges = ranges;
	}
	
	public boolean inRange(String ipAddress) throws FirewallException
	{
		int[] ip = Utils.parseIP(ipAddress);
		
		for (int i=0; i<4; i++)
		{
			if (!ranges[i].inRange(ip[i])) return false;
		}
		
		return true;
	}
}
