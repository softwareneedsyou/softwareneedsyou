/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import fr.esgi.projet.softwareneedsyou.api.ConsoleOutput;
import fr.esgi.projet.softwareneedsyou.api.compiler.CompilerException;
import fr.esgi.projet.softwareneedsyou.api.compiler.CompilerLoader;
import fr.esgi.projet.softwareneedsyou.api.compiler.PluginCompiler;
import fr.esgi.projet.softwareneedsyou.api.history.Chapter;
import fr.esgi.projet.softwareneedsyou.api.history.HistoryLoader;
import fr.esgi.projet.softwareneedsyou.api.history.Story;
import fr.esgi.projet.softwareneedsyou.api.spi.PluginException;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.TextFlow;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import netscape.javascript.JSObject;

/**
 * @author Tristan
 *
 */
public class GameController implements Initializable {
	@FXML private /*WebView*/TextArea codeEditor;
	@FXML private TextFlow descriptionContent;
	@FXML private TextArea console;
	@FXML private TableView<?> tests;
	//protected final static Path base = SharedParams.AppParamsFolder.resolve("editor");
	
	@NonNull private final HistoryLoader hl = new HistoryLoader();
	private Chapter chap;
	private Story story;
	@NonNull private final CompilerLoader cl = new CompilerLoader();
	private PluginCompiler compiler;

	/**
	 * 
	 */
	public GameController() {
		this.hl.loadAllPlugins();
		List<Chapter> chs = new ArrayList<>();
		this.hl.getHistories().values().forEach(c -> chs.addAll(c));
		this.chap = chs.get(0);
		this.story = this.chap.getStories().iterator().next();
		//
		this.cl.getCompilersloader().getImplementations().forEach(System.out::println);
		try {
			this.compiler = this.cl.load(this.chap.getCompiler());
		} catch (PluginException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Compiler not available");
			alert.setHeaderText(e.getLocalizedMessage());
			alert.setContentText(e.toString());
			alert.show();
		}
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*WebEngine engine = this.codeEditor.getEngine();
		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
			public void changed(ObservableValue<? extends State> observable, State oldValue, State newValue) { 
				JSObject jsobj = (JSObject) engine.executeScript("window");
				jsobj.setMember("console", new JSBridge(engine));  
			}
		});
		try {
			engine.setJavaScriptEnabled(true);
			engine.load(this.getClass().getClassLoader().getResource("GamePane_editor.html").toURI().toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("WebEngine editor");
			error.setHeaderText(e.getLocalizedMessage());
			error.setContentText(e.toString());
			error.show();
		}*/
	}

	public void initModel(DataModel model) {
		;
	}

	/*private String code = "";
	@RequiredArgsConstructor
	public class JSBridge {
		@NonNull private final WebEngine webEngine;
		public boolean log(final String code) {
			System.out.println("-------------\n"+code+"\n-----------------\n");
			GameController.this.code = code;
			return true;
		}
	}*/
	
	@FXML
	public void handleTestAction(final ActionEvent event) {
		this.console.setText(this.codeEditor.getText());
		System.out.println(this.compiler);
		try(InputStream inTest = new ByteArrayInputStream(this.story.getFileTest())) {
			this.compiler.compileAndTest(this.codeEditor.getText(), inTest, new ConsoleOutput() {
				@NonNull private final PrintWriter out = new PrintWriter(new Writer() {
					@Override
					public void write(char[] cbuf, int off, int len) throws IOException {
						GameController.this.console.appendText(String.valueOf(cbuf, off, off));
					}
					@Override
					public void flush() throws IOException { ; }
					@Override
					public void close() throws IOException { ; }
				});
				@Override
				public PrintWriter getWriter(boolean isErr) {
					return this.out;
				}
			});
		} catch (IOException | CompilerException e) {
			Arrays.asList(e.getStackTrace()).forEach(ste -> System.out.println(ste.toString()));
			e.printStackTrace();
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Compiler editor");
			error.setHeaderText(e.getLocalizedMessage());
			error.setContentText(e.toString());
			error.show();
		}
	}
}
