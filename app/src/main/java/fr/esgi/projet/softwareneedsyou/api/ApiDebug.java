/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api;

import fr.esgi.projet.softwareneedsyou.App;
import fr.esgi.projet.softwareneedsyou.api.history.HistoryLoader;
import fr.esgi.projet.softwareneedsyou.api.spi.PluginException;
import lombok.NonNull;

/**
 * @author Blixel
 *
 */
public final class ApiDebug {

	/**
	 * 
	 */
	public ApiDebug() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		App.init();
		System.out.println("API debug :");
		System.out.println("¤ Plugin history :");
		@NonNull final HistoryLoader hl = new HistoryLoader();
		System.out.println("\t-- found :");
		hl.getPluginsDetected().forEach(System.out::println);
		System.out.println("\n\t-- loaded :");
		hl.getPluginsLoaded().forEach((k, v) -> {if(v!=null) System.out.println(k.getName());});
		hl.loadAllPlugins();
		System.out.println("\n\t-- histories :");
		hl.getHistories().forEach((p, clh) -> {System.out.println(p.getName()); clh.forEach(h -> System.out.println("\t" + h.getTitle()));});
		
		//System.out.println("\t-- Plugin ...");
	}

}
