package firewall.model;

import firewall.utils.Utils;
import firwall.exceptions.FirewallException;

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
