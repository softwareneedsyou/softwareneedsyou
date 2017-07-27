package fr.esgi.projet.softwareneedsyou.controllers;

import fr.esgi.projet.softwareneedsyou.callbacks.PluginCallback;
import fr.esgi.projet.softwareneedsyou.models.DataModel;
import fr.esgi.projet.softwareneedsyou.models.PluginModel;
import fr.esgi.projet.softwareneedsyou.webApi.WebApiRequest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class StoreController {
    @FXML
    public TableColumn<PluginModel, Integer> idTableColumn;
    @FXML
    public TableColumn<PluginModel, String> nameTableColumn;
    @FXML
    public TableColumn<PluginModel, String> typeTableColumn;
    public Button downloadButton;
    public Label pluginNameText;
    @FXML
    AnchorPane storeAnchorPane;
    @FXML
    TableView pluginsTableView;
    @FXML
    private WebView pluginDescriptionWebView;
    private ObservableList<PluginModel> pluginsObservableList;
    private StringProperty pluginNameProperty;
    private StringProperty pluginDescriptionProperty;
    private StringProperty pluginTypeProperty;
    private DataModel model;
    private PluginCallback pc = new PluginCallback();

    @FXML
    public void initialize() {
        pluginsObservableList = FXCollections.observableArrayList();
        pluginNameProperty = new SimpleStringProperty();
        pluginDescriptionProperty = new SimpleStringProperty();
        pluginTypeProperty = new SimpleStringProperty();
        WebEngine we = pluginDescriptionWebView.getEngine();

        pluginDescriptionProperty.addListener((observableValue, s, t1) -> we.loadContent(observableValue.getValue()));
        pluginNameProperty.addListener((observableValue, s, t1) -> pluginNameText.setText(observableValue.getValue()));

        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
    }

    @FXML
    public void handleOnRowClickAction(MouseEvent e) {
        if(pluginsTableView.getFocusModel().getFocusedItem() != null){
            PluginModel currentPlugin = (PluginModel) pluginsTableView.getFocusModel().getFocusedItem();
            model.setCurrentPlugin(currentPlugin);
            pluginNameProperty.setValue(currentPlugin.getName());
            pluginDescriptionProperty.setValue(currentPlugin.getDescription());
            downloadButton.setVisible(true);
        }
    }

    public void initModel(DataModel model) {
        if (this.model != null) {
            throw new IllegalStateException("There can only be one model");
        }
        this.model = model;

        WebApiRequest.getPlugins(pc.getPlugins(model));

        pluginsObservableList.addListener((ListChangeListener.Change<? extends PluginModel> change) -> {
            pluginsTableView.setItems(change.getList());
        });
        pluginsObservableList.setAll(model.getPlugins());
    }

    public void handleRefreshAction(ActionEvent actionEvent) {
        WebApiRequest.getPlugins(pc.getPlugins(model));
        pluginsObservableList.setAll(model.getPlugins());
    }
}
