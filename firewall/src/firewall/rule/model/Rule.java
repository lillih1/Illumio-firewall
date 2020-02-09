package firewall.rule.model;

public class Rule {
	private String direction;
	private String protocol;
	private Range portRange;
	private IPRange ipRange;
	
	public Rule(String direction, String protocol, Range portRange, IPRange ipRange) {
		this.direction = direction;
		this.protocol = protocol;
		this.portRange = portRange;
		this.ipRange = ipRange;
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public Range getPortRange() {
		return portRange;
	}
	public void setPortRange(Range portRange) {
		this.portRange = portRange;
	}
	public IPRange getIpRange() {
		return ipRange;
	}
	public void setIpRange(IPRange ipRange) {
		this.ipRange = ipRange;
	}
}
