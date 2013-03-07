package ltg.ps.phenomena;

import ltg.ps.PhenomenaServer;
import ltg.ps.api.phenomena.Phenomena;
import ltg.ps.server.ConfFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhenomenaDebugger { 
	
	private static Logger log = LoggerFactory.getLogger("PhenomenaDebugger.class");
	private static ConfFile configuration = new ConfFile();
	private static boolean isConfigured = false;
	
	public static void configure(String xmpp_username, String xmpp_password) {
		configuration.setDefaultProperties(xmpp_username, xmpp_password);
		// Set the debugger as configured
		isConfigured = true;
	}
	
	public static void debug(Class<? extends Phenomena> p) {
		if (!isConfigured) {
			log.error("Impossible to start debugger without configuring it. \n " +
					"Plase call PhenomenaDebugger.configure(String xmpp_username, String xmpp_password) first!");
			return;
		}
		log.info("Creating a new instinstance of Phenomena Server");
		PhenomenaServer.getInstance().start(configuration);
	}
	
	
	public static void debug(Class<? extends Phenomena> p, boolean online) {
		if (online) { 
			debug(p);
			return;
		}
		log.warn("Offline debugging is not yet supported");
	}

}
