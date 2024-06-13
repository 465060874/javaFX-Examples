module com.demo.javafxexample {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires de.flapdoodle.embed.mongo;
    requires de.flapdoodle.embed.process;

    requires java.desktop;
    requires org.apache.commons.io;
    requires org.fxmisc.richtext;
    requires atlantafx.base;
    requires org.kordamp.ikonli.material2;
    requires org.kordamp.ikonli.fontawesome5;
    requires jdk.jsobject;
    requires undertow.core;
//    requires org.eclipse.jetty.server;
//    requires jakarta.servlet;
//    requires org.eclipse.jetty.servlet;

    opens com.demo.javafxexample to javafx.controls, javafx.fxml, javafx.base,javafx.media;
    exports com.demo.javafxexample;
    exports com.demo.javafxexample.treeview;
    exports com.demo.javafxexample.tableview;
    exports com.demo.javafxexample.apps.studentinfo1;
    exports com.demo.javafxexample.apps.studentinfo2;
    exports com.demo.javafxexample.apps.bkcourse.mvvmapp;
    exports com.demo.javafxexample.apps.bkcourse.modelchangeapp;
    exports com.demo.javafxexample.apps.bkcourse.bindingsapp;
    exports com.demo.javafxexample.apps.bkcourse.dialogapp;
    exports com.demo.javafxexample.htmleditor;
    exports com.demo.javafxexample.layout;
    exports com.demo.javafxexample.database;
    opens com.wph.domain to javafx.controls;
    exports com.wph;
    exports com.wph.domain;
    exports com.wph.view;
    exports com.wph.business;
    opens com.wph.dto to javafx.controls, javafx.fxml, javafx.base;
    exports org.kordamp.bootstrapfx.sampler;
    exports com.demo.javafxexample.text;
    exports com.demo.javafxexample.apps.richeditor;
    exports com.demo.javafxexample.webview;
    exports com.demo.javafxexample.apps.bkcourse.tvlistapp.s1.finish;
    exports com.demo.javafxexample.apps.bkcourse.visualcueapp;
    opens com.demo.javafxexample.apps.bkcourse.tvlistapp.s1.finish to javafx.controls, javafx.fxml, javafx.base;
}