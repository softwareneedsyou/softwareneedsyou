package fr.esgi.projet.softwareneedsyou.api.compiler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
public final class CompilerLoader {
	@NonNull private Map<UUID, PluginCompiler> compilers = new HashMap<>();
	
	@Getter
	private final PluginLoader<PluginCompilerDeclare, PluginCompiler> compilersloader = new PluginLoader<PluginCompilerDeclare, PluginCompiler>(PluginCompilerDeclare.class) {
		@Override
		public void close() throws Exception {
			this.getPluginsLoaded().keySet().forEach(pcd -> {
				try {
					this.unload(pcd);
				} catch (PluginException | InvalidArgumentsException e) {
					e.printStackTrace();
				}
			});
		}

		/* (non-Javadoc)
		 * @see fr.esgi.projet.softwareneedsyou.api.spi.PluginLoader#load(fr.esgi.projet.softwareneedsyou.api.spi.PluginDescriptor)
		 */
		@Override
		public void load(final PluginCompilerDeclare plugin) throws PluginException {
			super.load(plugin);
			compilers.put(plugin.getID(), this.getPluginsLoaded().get(plugin));
			compilers.forEach((id, cmpl) -> {if(cmpl == null) compilers.remove(id);});
		}

		@Override
		protected Map<String, Object> initPluginParams(final PluginCompilerDeclare plugin) {
			return Collections.emptyMap();
		}
		
		/* (non-Javadoc)
		 * @see fr.esgi.projet.softwareneedsyou.api.spi.PluginLoader#unload(fr.esgi.projet.softwareneedsyou.api.spi.PluginDescriptor)
		 */
		@Override
		public void unload(final PluginCompilerDeclare plugin) throws PluginException, InvalidArgumentsException {
			System.out.println("// unload plugin "+plugin);
			CompilerLoader.this.compilers.remove(plugin);
			super.unload(plugin);
		}
	};

	public PluginCompiler load(@NonNull final UUID uid) throws PluginException {
		System.out.println("// ask compiler " + uid);
		if(!compilers.containsKey(uid)) {
			System.out.println("// loading compiler " + uid);
			PluginCompilerDeclare pcd = compilersloader.getImplementations().stream()
															.collect(Collectors.toMap(pc -> pc.getID(), pc -> pc)).get(uid);
			System.out.println(pcd);
			this.compilersloader.load(Optional.ofNullable(pcd).orElseThrow(RuntimeException::new));
		}
		return this.compilers.get(uid);
	}

}
