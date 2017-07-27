package fr.esgi.projet.softwareneedsyou.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import fr.esgi.projet.softwareneedsyou.api.ConsoleOutput;
import fr.esgi.projet.softwareneedsyou.api.compiler.CompilerException;
import fr.esgi.projet.softwareneedsyou.api.compiler.CompilerLoader;
import fr.esgi.projet.softwareneedsyou.api.compiler.PluginCompiler;
import fr.esgi.projet.softwareneedsyou.api.compiler.ResultCompiler;
import fr.esgi.projet.softwareneedsyou.api.history.Chapter;
import fr.esgi.projet.softwareneedsyou.api.history.Story;
import fr.esgi.projet.softwareneedsyou.api.history.Test;
import fr.esgi.projet.softwareneedsyou.api.spi.PluginException;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import lombok.NonNull;

public class GameController implements Initializable {
	@FXML private TextArea codeEditor;
	@FXML private TextFlow descriptionContent;
	@FXML private TextArea console;
	@FXML private TableView<Test> tests;
	private DataModel model;
	private StringProperty descriptionProperty;
	private StringProperty titleProperty;
	private Text description;
	private Text title;
	private Story story;
	@NonNull private final CompilerLoader cl = new CompilerLoader();
	private PluginCompiler compiler;
	private Chapter chapter;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle){
		descriptionProperty = new SimpleStringProperty();
		titleProperty = new SimpleStringProperty();
		description = new Text("");
		title = new Text("");
		title = new Text("");
		descriptionContent.getChildren().addAll(title, description);
		descriptionProperty.addListener((observableValue, s, t1) -> description.setText(observableValue.getValue()));
		titleProperty.addListener((observableValue, s, t1) -> title.setText(observableValue.getValue()));
	}


	public void initGame(Chapter chapter, Story story){
		this.story = story;
		this.chapter = chapter;
		titleProperty.setValue(story.getTitle() + '\n');
		descriptionProperty.setValue(story.getContent());
		console.clear();
		codeEditor.clear();
		this.tests.getItems().addAll(this.story.getTests());

		this.cl.getCompilersloader().getImplementations().forEach(System.out::println);
		try {
			this.compiler = this.cl.load(this.chapter.getCompiler());
		} catch (PluginException e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Compiler not available");
			alert.setHeaderText(e.getLocalizedMessage());
			alert.setContentText(e.toString());
			alert.show();
		}
	}

	public void initModel(DataModel model) {
		if(this.model != null){
			throw new IllegalStateException("There can only be one model.");
		}
		this.model = model;
	}
	
	@FXML
	public void handleTestAction(final ActionEvent event) {
		this.console.setText(this.codeEditor.getText());
		System.out.println(this.compiler);
		try(InputStream inTest = new ByteArrayInputStream(this.story.getFileTest())) {
			ResultCompiler result = this.compiler.compileAndTest(this.codeEditor.getText(), inTest, new ConsoleOutput() {
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
			this.console.setText("\n Compile success : "+ result.isCompileSuccess() +"\n");
			this.console.appendText("Tests :\n");
			result.getTestsResults().forEach((id, t) -> this.console.appendText("\t"+id+" = "+t.getState()+" : "+t.getDetail()+"\n"));
			//this.tests.getItems().clear();
			//result.getTestsResults().forEach((id, t) -> this.tests.getItems().get(id));
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
