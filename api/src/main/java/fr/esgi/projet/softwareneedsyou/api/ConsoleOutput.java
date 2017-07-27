/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api;

import java.io.PrintWriter;

/**
 * @author Tristan
 *
 */
public interface ConsoleOutput {
	/**
	 * 
	 * @param isErr
	 * @return
	 */
	PrintWriter getWriter(final boolean isErr);
	
	default PrintWriter getOutput() {
		return this.getWriter(false);
	}
	
	default PrintWriter getError() {
		return this.getWriter(true);
	}
}
