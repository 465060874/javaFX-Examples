package com.wph;

import atlantafx.base.theme.PrimerLight;
import atlantafx.base.theme.Styles;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Section;
import com.dlsc.formsfx.model.structure.Field;
import com.dlsc.formsfx.model.structure.Form;
import com.dlsc.formsfx.model.structure.Group;
import com.dlsc.formsfx.view.renderer.FormRenderer;
import com.wph.domain.Category;
import com.wph.dto.KnowledgeEntryDTO;
import com.wph.view.KnowledgeEntryTableView;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;
//import org.kordamp.bootstrapfx.BootstrapFX;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AppUI extends Application {
    private Stage stage;
    private GridPane grid;
    private TreeView<CodeNamePair> menuTreeView;
    private TreeItem<CodeNamePair> rootItem;
    private TabPane tabPane;
    private Tab welcomeTab;
    private FormRenderer displayForm;
    private TableView<KnowledgeEntryDTO> knowledgeEntryTableView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Application.setUserAgentStylesheet("/AtlantaFX-themes/primer-light.css");
        stage = primaryStage;
        grid = new GridPane();
        grid.setPadding(new Insets(5, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);

        final TextField searchBox = new TextField();


        final Button addButton = new Button("添加目录");
        Hyperlink hyperlink = new Hyperlink("添加目录");
        hyperlink.setStyle("-fx-text-fill: blue; -fx-underline: true; -fx-cursor: hand;");
        GridPane.setMargin(hyperlink, new Insets(5, 0, 0, 0));

        hyperlink.setOnAction(event -> {
            addCatetory();
        });

        grid.add(hyperlink, 0, 0);


        buildCategoryTree();
        menuTreeView = new TreeView();
        menuTreeView.setRoot(rootItem);

        menuTreeView.setShowRoot(false);
//        menuTreeView.getStyleClass().add("samples-tree");
        menuTreeView.setMinWidth(200);
        menuTreeView.setMaxWidth(200);


        menuTreeView.setCellFactory(new Callback<>() {
            @Override
            public TreeCell<CodeNamePair> call(TreeView<CodeNamePair> param) {
                return new TreeCell<>() {
                    @Override
                    protected void updateItem(CodeNamePair item, boolean empty) {
                        System.out.println("updateItem");
                        super.updateItem(item, empty);

                        if (empty) {
                            setText("");
                        } else {
                            setText(item.getName());
                        }
                    }
                };
            }
        });

//        menuTreeView.getStyleClass().add(BootstrapFX.bootstrapFXStylesheet());

        menuTreeView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItem) -> {
            System.out.println(newItem);
            updateTab(newItem.getValue());
        });


        GridPane.setVgrow(menuTreeView, Priority.ALWAYS);
        grid.add(menuTreeView, 0, 1);

        // right hand side
        tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        tabPane.getStyleClass().add(TabPane.STYLE_CLASS_FLOATING);
//        tabPane.getSelectionModel().selectedItemProperty().addListener(o -> updateTab());

        Tab tab1 = new Tab("知识库", new Label("双击右边菜单将在这里显示其内容"));
        tab1.setClosable(false);
        tabPane.getTabs().add(tab1);
        GridPane.setHgrow(tabPane, Priority.ALWAYS);
        GridPane.setVgrow(tabPane, Priority.ALWAYS);
        grid.add(tabPane, 1, 0, 1, 2);

        // put it all together
        Scene scene = new Scene(grid);
//        scene.getStylesheets().add(getClass().getResource("fxsampler.css").toExternalForm());
//        for (FXSamplerConfiguration fxsamplerConfiguration : configurationServiceLoader) {
//            String stylesheet = fxsamplerConfiguration.getSceneStylesheet();
//            if (stylesheet != null) {
//                scene.getStylesheets().add(stylesheet);
//            }
//        }
//        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
//        scene.getStylesheets().addAll(
//                BootstrapFX.bootstrapFXStylesheet(),
//                "org/kordamp/bootstrapfx/sampler/sampler.css",
//                "org/kordamp/bootstrapfx/sampler/xml-highlighting.css");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(600);

        // set width / height values to be 75% of users screen resolution
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setWidth(screenBounds.getWidth() * 0.75);
        primaryStage.setHeight(screenBounds.getHeight() * .75);

        primaryStage.setTitle("工作知识库");

        updateInfo();
        primaryStage.show();

        menuTreeView.requestFocus();

        primaryStage.setOnCloseRequest((event) -> {
            System.out.println("Closing Stage");
        });
    }

    private void addCatetory() {
        // 创建表单界面
        CodeNamePair codeNamePair = new CodeNamePair("","");
        SimpleStringProperty catetory = new SimpleStringProperty("");
//        com.dlsc.formsfx.model.structure.Field<String> nameField = Field.ofStringType("").label("Name").required("Name is required");

        Form newForm = Form.of(
                Group.of(
                        Field.ofStringType(codeNamePair.summerTimeZoneProperty()).id("category").bind(catetory)
                                .label("类别名")
                                .required("This field can’t be empty")
                )
        ).title("Login");

        FormRenderer displayForm = new FormRenderer(newForm);

//        GridPane form = new GridPane();
//        form.setHgap(10);
//        form.setVgap(5);
//
//        TextField Field = new TextField(person.getName());
//        TextField ageField = new TextField(Integer.toString(person.getAge()));
//        TextField addressField = new TextField(person.getAddress());
//
//        form.addRow(0, new Label("Name:"), nameField);
//        form.addRow(1, new Label("Age:"), ageField);
//        form.addRow(2, new Label("Address:"), addressField);

        // 创建对话框
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("添加类别");

        // 创建保存按钮
        Button saveButton = new Button("保存");
        saveButton.setOnAction(event -> {
//            person.setName(nameField.getText());
//            person.setAge(Integer.parseInt(ageField.getText()));
//            person.setAddress(addressField.getText());
            newForm.persist();
            //下面三种方法都可以获取到值，如果不调用persist,则第一种方式可以获取到值。
            System.out.println(newForm.getFields());
            System.out.println(codeNamePair);
            System.out.println(catetory);
            rootItem.getChildren().add(new TreeItem<>(new CodeNamePair("1111","aaaaa")));
            dialogStage.close();
        });

        // 创建取消按钮
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> dialogStage.close());

        HBox buttonBox = new HBox(10, saveButton, cancelButton);
        buttonBox.setPadding(new Insets(10));

        VBox dialogVBox = new VBox(10, displayForm, buttonBox);
        dialogVBox.setPadding(new Insets(20));

        Scene dialogScene = new Scene(dialogVBox);
        dialogStage.setScene(dialogScene);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.showAndWait();
    }


    private void buildCategoryTree() {
        rootItem = new TreeItem(new CodeNamePair("wph", "工作知识库"));


        List<Category> categories = Category.getCategories();
        for (Category category : categories) {
            TreeItem categoryItem = new TreeItem(category.getParent().copy());
            for (CodeNamePair child : category.getChildren()) {
                categoryItem.getChildren().add(new TreeItem(child.copy()));
            }
            rootItem.getChildren().add(categoryItem);
        }

    }

    private static final Map<String, String> LINK_MAPS = new HashMap<>();
    static {
        LINK_MAPS.put("jenkov", "https://jenkov.com/tutorials/javafx/index.html");
        LINK_MAPS.put("controlsfx", "https://github.com/controlsfx/controlsfx");
        LINK_MAPS.put("FormsFX", "https://github.com/dlsc-software-consulting-gmbh/FormsFX");
        LINK_MAPS.put("bootstrapfx", "https://github.com/kordamp/bootstrapfx");
        LINK_MAPS.put("AtlantaFX", "https://github.com/mkpaz/atlantafx");

    }

    private Map<String, Integer> linkWithIndex = new HashMap<>();
    private void updateTab(CodeNamePair menuItem) {
        if(LINK_MAPS.containsKey(menuItem.getCode())){
            try {
                int tabCount = tabPane.getTabs().size();
                if(linkWithIndex.containsKey(menuItem.getCode())){
                    tabPane.getSelectionModel().select(linkWithIndex.get(menuItem.getCode()));
//                    tabPane.getTabs().get(linkWithIndex.get(menuItem.getCode()));
                }else {
                    WebView webView = new WebView();

                    WebEngine webEngine = webView.getEngine();
                    webEngine.load(LINK_MAPS.get(menuItem.getCode()));
                    Tab tab = new Tab(menuItem.getName(), webView);
                    tab.setClosable(true);
                    tabPane.getTabs().add(tabCount, tab);
                    tabPane.getSelectionModel().select(tabCount);
                    linkWithIndex.put(menuItem.getCode(), tabCount);
                }
                // 打开指定的 URL
//                Desktop.getDesktop().browse(new URI(LINK_MAPS.get(menuItem.getCode())));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }

        VBox vBox = new VBox();
        VBox topBox = new VBox();
        topBox.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        topBox.setPadding(new Insets(10, 10, 10, 10));
        VBox bottomBox = new VBox();
        Pane pane = new Pane();
        topBox.prefWidthProperty().bind(pane.widthProperty());
        pane.setPadding(new Insets(10, 10, 10, 10));
//
//        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, new Insets(10, 10, 10, 10))));
        pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        topBox.setSpacing(20);     // 设置子节点间的间距
        Label label = new Label(menuItem.getName());
        label.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selectedText = label.getText();
                ClipboardContent content = new ClipboardContent();
                content.putString(selectedText);
                Clipboard.getSystemClipboard().setContent(content);
            }
        });

        HBox box = new HBox();
//        box.setSpacing(20);
        box.setPadding(new Insets(10));
        Button button = new Button("复制");
        button.getStyleClass().setAll("btn","btn-danger");
        button.setOnAction(event -> {
            showEditForm(menuItem);
            bottomBox.getChildren().clear();
            bottomBox.getChildren().add(displayForm);
        });
        box.getChildren().addAll(label, button);

//        topBox.setPadding(new Insets(20, 20, 10, 10));
        topBox.getChildren().addAll(box);

        vBox.getChildren().addAll(topBox, bottomBox);
        tabPane.getTabs().get(0).setText(menuItem.getName());

        KnowledgeEntryTableView entryTableView = new KnowledgeEntryTableView();
        knowledgeEntryTableView = entryTableView.getTableView();
        Styles.toggleStyleClass(knowledgeEntryTableView, Styles.BORDERED);
        Styles.toggleStyleClass(knowledgeEntryTableView, Styles.STRIPED);
        StackPane root = new StackPane(knowledgeEntryTableView);
        tabPane.getTabs().get(0).setContent(root);
        tabPane.getSelectionModel().select(tabPane.getTabs().get(0));



    }


    private void updateInfo() {
        Form form = Form.of(
                Group.of(
                        Field.ofStringType("")
                                .label("Username"),
                        Field.ofStringType("")
                                .label("Password")
                                .required("This field can’t be empty")
                )
        ).title("Login");
        displayForm = new FormRenderer(form);
    }

    private void showEditForm(CodeNamePair menuItem) {
        // 创建表单界面
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(5);

        TextField nameField = new TextField(menuItem.getCode());
        TextField ageField = new TextField(menuItem.getName());
        TextField addressField = new TextField("content");

        form.addRow(0, new Label("Name:"), nameField);
        form.addRow(1, new Label("Age:"), ageField);
        form.addRow(2, new Label("Address:"), addressField);

        // 创建对话框
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setTitle("Edit Person");

        // 创建保存按钮
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> {
//            person.setName(nameField.getText());
//            person.setAge(Integer.parseInt(ageField.getText()));
//            person.setAddress(addressField.getText());
//            tableView.refresh(); // 更新 TableView
            dialogStage.close();
        });

        // 创建取消按钮
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> dialogStage.close());

        HBox buttonBox = new HBox(10, saveButton, cancelButton);
        buttonBox.setPadding(new Insets(10));

        VBox dialogVBox = new VBox(10, form, buttonBox);
        dialogVBox.setPadding(new Insets(20));

        Scene dialogScene = new Scene(dialogVBox);
        dialogStage.setScene(dialogScene);
        dialogStage.show();
    }
}
