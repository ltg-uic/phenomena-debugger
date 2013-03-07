package ltg.ps.phenomena;

import ltg.ps.PhenomenaServer;
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
	
	public static void debug(String phenomenaJarFile) {
		if (!isConfigured) {
			log.error("Impossible to start debugger without configuring it. \n " +
					"Plase call PhenomenaDebugger.configure(String xmpp_username, String xmpp_password) first!");
			return;
		}
		log.info("Creating a new instinstance of Phenomena Server");
		PhenomenaServer.getInstance().start(phenomenaJarFile, configuration);
	}
	
	
	public static void debug(String phenomenaJarFile, boolean online) {
		if (online) { 
			debug(phenomenaJarFile);
			return;
		}
		log.warn("Offline debugging is not yet supported");
	}

}
