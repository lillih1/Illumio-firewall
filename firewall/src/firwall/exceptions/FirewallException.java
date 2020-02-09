package firwall.exceptions;

public class FirewallException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public FirewallException(String msg) {
		super(msg);
	}

    public FirewallException(String msg, Throwable e) {
        super(msg, e);
    }
}
