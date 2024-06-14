package com.demo.javafxexample.apps.studentinfo6;

//Main Application (StudentApplication.java):
/**
 * 这个学生管理系统使用了MVVM模式,将数据(Model)、视图(View)和视图模型(ViewModel)分离。
 *
 * Student类是模型,表示学生的数据。
 * StudentViewModel类是视图模型,处理学生数据的逻辑操作。
 * StudentView类是视图,负责显示学生数据和与用户交互。
 * 在StudentView类中,我们创建了一个TableView来显示学生数据,并为每一行添加了一个"Edit"按钮。点击"Edit"按钮会弹出一个对话框,显示对应学生的详细信息,并允许用户进行编辑。点击对话框中的"Save"按钮会调用StudentViewModel的updateStudent方法更新学生数据,并立即反映在表格中。
 *
 * 最后,在StudentApplication类中,我们创建了StudentViewModel和StudentView的实例,并将视图设置为主舞台的场景。
 *
 * 这样,我们就使用JavaFX的MVVM模式完成了一个简单的学生管理系统。
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class StudentApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        StudentViewModel viewModel = new StudentViewModel();
        StudentView view = new StudentView(viewModel);

        primaryStage.setTitle("Student Management System");
        primaryStage.setScene(view.getScene());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
