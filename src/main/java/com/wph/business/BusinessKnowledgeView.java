package com.wph.business;

import com.demo.javafxexample.apps.bkcourse.mvvmapp.EmploymentRequestViewModel;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

public class BusinessKnowledgeView extends StackPane {
    TableView<BusinessKnowledgeViewModel> tableView = new TableView<>();
    TableColumn<BusinessKnowledgeViewModel, String> titleColumn = new TableColumn<>("title");
    TableColumn<BusinessKnowledgeViewModel, String> shortDescriptionColumn = new TableColumn<>("shortDescription");
    TableColumn<BusinessKnowledgeViewModel, Void> actionColumn = new TableColumn<>("Action");
    private BusinessKnowledgeViewModel viewModel;

    public BusinessKnowledgeView() {

        createView();
//        bindViewModel();
    }

    private void createView() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        shortDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("shortDescription"));
        shortDescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
//        shortDescriptionColumn.setCellFactory(new Callback<TableColumn<KnowledgeEntryDTO, String>, TableCell<KnowledgeEntryDTO, String>>() {
//            @Override
//            public TableCell<KnowledgeEntryDTO, String> call(TableColumn<KnowledgeEntryDTO, String> param) {
//                return new KnowledgeEntryTableView.CopyableTableCell();
//            }
//        });

        actionColumn.setCellFactory(param -> new TableCell<>() {
            //            private final Button editButton = new Button("Edit");
            private final Hyperlink editButton = new Hyperlink("编辑");

            {
//                editButton.getStyleClass().setAll("btn","btn-info");
                //editButton.setMnemonicParsing(true);

                editButton.setOnAction(event -> {
                    viewModel = getTableView().getItems().get(getIndex());
                    //KnowledgeEntryDTO entryDTO = getTableView().getItems().get(getIndex());
                    //showEditForm(entryDTO, getTableView());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(editButton);
                }
            }
        });

        // 添加列到 TableView
        tableView.getColumns().addAll(titleColumn, shortDescriptionColumn, actionColumn);

        // 添加数据到 TableView
        tableView.getItems().addAll(BusinessKnowledgeViewModel.businessKnowledgeViewModels());
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.getChildren().addAll(tableView);
    }
}
