/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api;

import java.io.OutputStream;

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
	OutputStream getStream(final boolean isErr);
	
	default OutputStream getOutput() {
		return this.getStream(false);
	}
	
	default OutputStream getError() {
		return this.getStream(true);
	}
}
