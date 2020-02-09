package firewall.rule.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import firewall.exceptions.FirewallException;
import firewall.model.IPRange;
import firewall.model.Range;
import firewall.model.Rule;
import firewall.rule.Firewall;
import firewall.utils.Constants;
import firewall.utils.Utils;

public class FirewallImpl implements Firewall 
{
	private List<Rule> rules = null;
	
	public FirewallImpl(String filename)
	{
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File(filename)));
		} catch (FileNotFoundException e) {
			System.out.println("Error opening input file: " + filename);
			e.printStackTrace();
			System.exit(-1);
		}

		rules = new ArrayList<Rule>();
		
		int lineNumber = 0;
		String line = null;
		
		try {
			while ((line = reader.readLine()) != null)
			{
				lineNumber++;
				String[] fields = line.split(",");
				
				if (fields.length != 4)
				{
					System.out.println("Invalid file format at line " + lineNumber + ". Skipping the line");
					continue;
				}
				
				String direction = fields[0].trim();
				if (!validDirectiion(direction))
				{
					System.out.println("Invalid direction at line " + lineNumber + ". Skipping the line");
					continue;
				}
				
				String protocol = fields[1].trim();
				if (!validProtocol(protocol))
				{
					System.out.println("Invalid protocol at line " + lineNumber + ". Skipping the line");
					continue;
				}

				Range portRange = readRange(fields[2].trim(), 1, 65535);
				if (portRange == null)
				{
					System.out.println("Invalid port range at line " + lineNumber + ". Skipping the line");
					continue;
				}
			
				IPRange ipRange = readIPRange(fields[3].trim());
				if (ipRange == null)
				{
					System.out.println("Invalid IP range at line " + lineNumber + ". Skipping the line");
					continue;
				}
				
				rules.add(new Rule(direction, protocol, portRange, ipRange));
			}
		} catch (IOException e) {
			System.out.println("Error reading a line from file: " + filename);
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public boolean accept_packet(String direction, String protocol, int port, String ip)
	{
		for (Rule rule : rules)
		{
			if (satisfies(rule, direction, protocol, port, ip))
			{
				return true;
			}
		}
		
		return false;
	}

	private boolean satisfies(Rule rule, String direction, String protocol, int port, String ip)
	{
		if (!rule.getDirection().equals(direction)) return false;

		if (!rule.getProtocol().equals(protocol)) return false;

		if (!rule.getPortRange().inRange(port)) return false;

		try {
			if (!rule.getIpRange().inRange(ip)) return false;
		}
		catch (FirewallException e)
		{
//			System.out.println("ERROR: " + e.getMessage());
			return false;
		}
		
		return true;
	}	
	
	private boolean validDirectiion(String direction)
	{
		if (direction == null) return false;
		
		return direction.equals(Constants.INBOUND)|| direction.equals(Constants.OUTBOUND);
	}
	
	private boolean validProtocol(String protocol)
	{
		if (protocol == null) return false;
		
		return protocol.equals(Constants.TCP)|| protocol.equals(Constants.UDP);
	}
	
	private Range readRange(String str, int lowerBound, int upperBound)
	{
		Range range = null;
		
		if (str.indexOf("-") >= 0)
		{
			String[] strs = str.split("-");
			try
			{
				if (strs.length != 2) return null;
				
				int r1 = Integer.parseInt(strs[0]);
				int r2 = Integer.parseInt(strs[1]);
				
				if (r1 < lowerBound || r2 > upperBound || r1 > r2)
				{
					return null;
				}
				
				range = new Range(r1, r2);
			}
			catch (NumberFormatException e)
			{
				return null;
			}
		}
		else
		{
			int p = Integer.parseInt(str);
			if (p < lowerBound || p > upperBound)
			{
				return null;
			}
			
			range = new Range(p, p);
		}
		
		return range;
	}
		
	private IPRange readIPRange(String ip)
	{
		Range[] ranges = new Range[4];

		if (ip.indexOf("-") >= 0)
		{
			String[] ips = ip.split("-");
			if (ips.length != 2)
			{
				return null;
			}

			int[] lower = null;
			try {
				lower = Utils.parseIP(ips[0]);
			}
			catch(FirewallException e)
			{
				return null;
			}

			int[] upper = null;
			try {
				upper = Utils.parseIP(ips[1]);
			}
			catch(FirewallException e)
			{
				return null;
			}
			
			for (int i=0; i<4; i++)
			{
				if (lower[i] > upper[i]) return null;
				ranges[i] = new Range(lower[i], upper[i]);
			}		
		}
		else
		{
			int[] ips = null;
			try {
				ips = Utils.parseIP(ip);
			}
			catch(FirewallException e)
			{
				return null;
			}
	
			for (int i=0; i<4; i++)
			{
				ranges[i] = new Range(ips[i], ips[i]);
			}
		}
		
		return new IPRange(ranges);
	}	
}
