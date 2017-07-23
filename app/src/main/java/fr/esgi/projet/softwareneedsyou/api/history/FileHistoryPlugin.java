/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.api.history;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.xml.sax.SAXException;

import fr.esgi.projet.softwareneedsyou.SharedParams;
import lombok.NonNull;

/**
 * @author Tristan
 *
 */
public class FileHistoryPlugin implements PluginHistory {
	/**
	 * Sous-dossier où se trouve les histoires (fichiers "simples")
	 */
	private static final Path AppHistoriesFolder = SharedParams.AppParamsFolder.resolve("history");
	
	/**
	 * Extension des histoires
	 */
	public final static String HistoryExtension = ".history";
	
	/**
	 * Propriété(s) ZipFs
	 */
	private final static Map<String, String> zip_properties = new LinkedHashMap<>();
	{
		zip_properties.put("create", Boolean.toString(false));
		zip_properties.put("encoding", "utf-8");
	}

	/**
	 * Toutes les histoires trouvées.
	 * Stockée dans un path pour évité de les rechargées à chaque fois.
	 */
	private final Map<Path, History> histories = new HashMap<>();

	/**
	 * 
	 */
	public FileHistoryPlugin() {
		if(Files.notExists(AppHistoriesFolder)) {
			System.out.println("// create folder plugin");
			try {
				Files.createDirectory(AppHistoriesFolder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.AutoCloseable#close()
	 */
	@Override
	public void close() throws Exception {
	}

	/* (non-Javadoc)
	 * @see fr.esgi.projet.softwareneedsyou.api.history.PluginHistory#getHistories()
	 */
	@Override
	public Collection<History> getHistories() {
		return loadHistories(AppHistoriesFolder);
	}

	private final Collection<History> loadHistories(@NonNull final String folder) {
		return loadHistories(URI.create(folder));
	}
	
	private final Collection<History> loadHistories(@NonNull final URI folder) {
		return loadHistories(Paths.get(folder));
	}
	
	private final Collection<History> loadHistories(@NonNull final Path folder) {
		try(DirectoryStream<Path> dirStream = Files.newDirectoryStream(folder, pf -> pf.endsWith(HistoryExtension))) {
			dirStream.forEach(p -> this.histories.computeIfAbsent(p, f -> readHistories(f)));
			this.histories.forEach((p, h) -> {if(Objects.isNull(h)) this.histories.remove(p);});
			return this.histories.values();
		} catch (IOException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}
	
	private final static History readHistories(@NonNull final String file) {
		return readHistory(URI.create(file));
	}
	
	private final static History readHistories(@NonNull Path file) {
		return readHistory(file.toUri());
	}
	
	private final static History readHistory(@NonNull final URI file) {
		try(final FileSystem zipfs = FileSystems.newFileSystem(file, zip_properties)) {
			@NonNull final Path path = zipfs.getPath("/index.xml");
			try(@NonNull final InputStream root = Files.newInputStream(path, StandardOpenOption.READ)) {
				validateXML(root);
			} catch (SAXException e) {
				e.printStackTrace();
			}
			try(@NonNull final InputStream root = Files.newInputStream(path, StandardOpenOption.READ)) {
				return parseHistory(zipfs, root);
			}
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Parse le fichier une fois validé
	 * @param in
	 * @throws IOException 
	 */
	private final static History parseHistory(@NonNull final FileSystem fs, @NonNull final InputStream input) throws IOException {
		@NonNull final Document doc = Jsoup.parse(input, "utf-8", "", Parser.xmlParser());
		@NonNull final Element history = doc.select("history").first();
		return createHistoryWithNonNullCheck(UUID.fromString(history.attr("compiler")),
				history.getElementsByTag("title").first().val(),
											Optional.ofNullable(loadContentNode(null, history.getElementsByTag("description").first())),
											LLevelFromLevels(fs, history.select("levels").first()));
	}
	
	private final static History createHistoryWithNonNullCheck(@NonNull final UUID compiler, @NonNull final String title,
																@NonNull Optional<String> description, @NonNull final List<Level> levels) {
		return new History() {
			//private final List<Level> llevels = Arrays.asList(levels);
			
			@Override
			public String getTitle() {
				return title;
			}
			
			@Override
			public Optional<String> getResume() {
				return description;
			}
			
			@Override
			public Collection<Level> getLevels() {
				return levels;
			}
			
			@Override
			public UUID getCompiler() {
				return compiler;
			}
		};
	}
	
	private final static List<Level> LLevelFromLevels(@NonNull final FileSystem fs, @NonNull final Element levels) {
		return levels.select("level").parallelStream().map(l -> createLevelWithNonNullCheck(l.attr("name"),
																							loadContentNode(fs, l.getElementsByTag("desciption").first()),
																							Optional.ofNullable(loadContentNode(fs, l.getElementsByTag("resume").first())),
																							fs.getPath(l.select("tests").first().attr("file")),
																							new HashSet<Test>(LTestFromTests(l.select("tests").first()))))
														.collect(Collectors.toList());
	}
	private final static Level createLevelWithNonNullCheck(@NonNull final String name, @NonNull final String description,
															@NonNull final Optional<String> resume, @NonNull final Path test,
															@NonNull final Set<Test> tests) {
		return new Level() {
			//private final Set<Test> ltests = new HashSet<>(Arrays.asList(tests));
			
			@Override
			public String getTitle() {
				return name;
			}
			
			@Override
			public Set<Test> getTests() {
				return tests;
			}
			
			@Override
			public Optional<String> getResume() {
				return resume;
			}
			
			@Override
			public Path getFileTest() {
				return test;
			}
			
			@Override
			public String getContent() {
				return description;
			}
		};
	}
	
	private final static List<Test> LTestFromTests(@NonNull final Element tests) {
		return tests.select("test").parallelStream().map(t -> createTestWithNonNullCheck(Short.valueOf(t.attr("id")),
																						t.attr("name"),
																						Optional.ofNullable(t.attr("resume"))))
													.collect(Collectors.toList());
	}
	private final static Test createTestWithNonNullCheck(final short id, @NonNull final String name, @NonNull final Optional<String> resume) {
		return new Test() {
			@Override
			public Optional<String> getResume() {
				return resume;
			}
			
			@Override
			public String getName() {
				return name;
			}
			
			@Override
			public short getId() {
				return id;
			}
		};
	}
	
	public final static String loadContentNode(@NonNull final FileSystem fs, @NonNull final Element node) {
		switch(node.attr("type")) {
			case "text":
				return node.val();
			case "file":
			try {
				return Files.lines(fs.getPath(node.val()), StandardCharsets.UTF_8).collect(Collectors.joining("\n"));
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
			default:
				//throw new IOException(fs.toString() + " : Type non reconnu");
				return null;
		}
	}
	
	private final static void validateXML(@NonNull final InputStream in) throws SAXException, IOException {
		@NonNull final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		@NonNull final Schema schema = factory.newSchema(History.class.getResource("HistorySchema.xsd"));
		@NonNull final Validator validator = schema.newValidator();
		validator.validate(new StreamSource(in));
	}
	
	/**
	 * ouvre un filsystem zip, et un inputstream qui fermera le filesystem lorsqu'il se formera.
	 * @param fs
	 * @param subpath
	 * @return
	 * @throws IOException 
	 */
	private final static InputStream autoCloseFileSystem(@NonNull final Path fs, @NonNull final Path subpath) throws IOException {
		@NonNull final FileSystem zipfs = FileSystems.newFileSystem(fs.toUri(), zip_properties);
		@NonNull final InputStream is = new FilterInputStream(Files.newInputStream(subpath, StandardOpenOption.READ)) {
			/* (non-Javadoc)
			 * @see java.io.FilterInputStream#close()
			 */
			@Override
			public void close() throws IOException {
				zipfs.close();
				super.close();
			}
		};
		return is;
	}
}
