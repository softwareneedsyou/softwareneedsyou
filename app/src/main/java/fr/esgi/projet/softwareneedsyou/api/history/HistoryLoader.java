/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.history;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import fr.esgi.projet.softwareneedsyou.api.spi.InvalidArgumentsException;
import fr.esgi.projet.softwareneedsyou.api.spi.PluginException;
import fr.esgi.projet.softwareneedsyou.api.spi.PluginLoader;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author Tristan
 *
 */
@EqualsAndHashCode
@ToString
public final class HistoryLoader {
	@NonNull private Map<PluginHistoryDeclare, Collection<History>> histories = new HashMap<>();
	
	@Getter
	private final PluginLoader<PluginHistoryDeclare, PluginHistory> historiesloader = new PluginLoader<PluginHistoryDeclare, PluginHistory>(PluginHistoryDeclare.class) {
		@Override
		public void close() throws Exception {
		}

		@Override
		protected Map<String, Object> initPluginParams(final PluginHistoryDeclare plugin) {
			return Collections.emptyMap();
		}
		
		/* (non-Javadoc)
		 * @see fr.esgi.projet.softwareneedsyou.api.spi.PluginLoader#load(fr.esgi.projet.softwareneedsyou.api.spi.PluginDescriptor)
		 */
		@Override
		public void load(final PluginHistoryDeclare plugin) throws PluginException {
			super.load(plugin);
			Optional<PluginHistory> plugh = this.getPluginImpl(plugin);
			if(plugh.isPresent()) {
				HistoryLoader.this.histories.replace(plugin, plugh.get().getHistories());
			}
		}

		/* (non-Javadoc)
		 * @see fr.esgi.projet.softwareneedsyou.api.spi.PluginLoader#unload(fr.esgi.projet.softwareneedsyou.api.spi.PluginDescriptor)
		 */
		@Override
		public void unload(final PluginHistoryDeclare plugin) throws PluginException, InvalidArgumentsException {
			HistoryLoader.this.histories.remove(plugin);
			super.unload(plugin);
		}
	};

	/**
	 * @return the histories (loaded)
	 */
	public Map<PluginHistoryDeclare, Collection<History>> getHistories() {
		return Collections.unmodifiableMap(this.histories);
	}

	/**
	 * @return the plugins loaded
	 */
	public Map<PluginHistoryDeclare, PluginHistory> getPluginsLoaded() {
		return Collections.unmodifiableMap(this.historiesloader.getPluginsLoaded());
	}

	/**
	 * @return the plugins founnd
	 */
	public Set<PluginHistoryDeclare> getPluginsDetected() {
		return Collections.unmodifiableSet(this.historiesloader.getImplementations());
	}
	
	public void loadAllPlugins() {
		this.historiesloader.getImplementations().forEach(d -> {
			try {
				this.historiesloader.load(d);
			} catch (PluginException e) {
				e.printStackTrace();
			}
		});
	}
}
