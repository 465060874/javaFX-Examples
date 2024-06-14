package com.demo.javafxexample.apps.studentinfo5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * 2 创建ViewModel
 * 接下来创建ViewModel来处理学生数据的逻辑。
 */
public class StudentViewModel {
    private final ObservableList<Student> students;

    public StudentViewModel() {
        students = FXCollections.observableArrayList();
        // 添加一些示例数据
        students.add(new Student("Alice", "Class A", "123 Main St"));
        students.add(new Student("Bob", "Class B", "456 Oak St"));
    }

    public ObservableList<Student> getStudents() {
        return students;
    }

    public void updateStudent(Student student, String name, String className, String address) {
        student.setName(name);
        student.setClassName(className);
        student.setAddress(address);
    }
}
