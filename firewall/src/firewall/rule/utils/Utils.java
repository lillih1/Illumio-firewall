package firewall.rule.utils;

import firewall.rule.exceptions.FirewallException;

public class Utils {
	public static int[] parseIP(String ipAddress) throws FirewallException
	{
		String[] strs = ipAddress.split("\\.");
		
		if (strs.length != 4)
		{
			throw new FirewallException("Malformatted IP Address: " + ipAddress);
		}
		
		int[] ip = new int[4];

		for (int i=0; i<4; i++)
		{
			try {
				int n = Integer.parseInt(strs[i]);

				if (n < 0 || n > 255)
				{
					throw new FirewallException("Out of range IP Address: " + ipAddress);
				}

				ip[i] = n;
			}
			catch (NumberFormatException e)
			{
				throw new FirewallException("Malformatted IP component " + strs[i] + 
						" in IP address: " + ipAddress);
			}
		}
		
		return ip;
	}
}
