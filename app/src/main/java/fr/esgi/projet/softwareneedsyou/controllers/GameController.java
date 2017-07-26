/**
 * 
 */
package fr.esgi.projet.softwareneedsyou.controllers;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
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
	@FXML private WebView codeEditor;
	@FXML private TextFlow descriptionContent;
	@FXML private TextArea console;
	@FXML private TableView<?> tests;
	//protected final static Path base = SharedParams.AppParamsFolder.resolve("editor");

	/**
	 * 
	 */
	public GameController() {
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		WebEngine engine = this.codeEditor.getEngine();
		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
			@Override
		    public void changed(ObservableValue<? extends State> observable,
		            State oldValue, State newValue) { 
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
		}
		//codeEditor.set
		/*try {
			engine.loadContent(replaceGetDeps(Files.readAllLines(Paths.get(this.getClass().getClassLoader().getResource("GamePane_editor.html").toURI()), StandardCharsets.UTF_8)
												.stream().collect(Collectors.joining()),
								"lib/codemirror.js", "lib/codemirror.css", "mode/javascript/javascript.js"));
			//mode/javascript/javascript.js
		} catch (final IOException | URISyntaxException e) {
			e.printStackTrace();
			engine.loadContent("<b style=\"color: red;\">Error while charging ...</b>\n<pre>\n"+e.toString()+"\n</pre>");
		}*/
	}

	public void initModel(DataModel model) {
		;
	}

	private String code = "";
	@RequiredArgsConstructor
	public class JSBridge {
		@NonNull private final WebEngine webEngine;
		@SuppressWarnings("unused")
		public boolean log(final String code) {
			GameController.this.code = code;
			return true;
		}
	}
	
	@FXML
	public void handleTestAction(final ActionEvent event) {
		this.console.setText(this.code);
	}
}
