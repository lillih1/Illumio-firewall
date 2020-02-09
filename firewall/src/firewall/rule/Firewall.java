package firewall.rule;

public interface Firewall {

	public boolean accept_packet(String direction, String protocol, int port, String ip);

}
