package com.demo.javafxexample.database;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.process.runtime.Network;

public class JavaFXEmbeddedMongoDBApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

    }
//
//    private MongodExecutable mongodExecutable;
//    private MongodProcess mongod;
//    private MongoClient mongoClient;
//    private MongoDatabase database;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        // 启动嵌入式MongoDB
//        startEmbeddedMongoDB();
//
//        // 连接到MongoDB
//        mongoClient = MongoClients.create("mongodb://127.0.0.1:27017");
//        database = mongoClient.getDatabase("testdb");
//        MongoCollection<Document> collection = database.getCollection("testcollection");
//
//        // 插入示例数据
//        collection.insertOne(new Document("name", "JavaFX").append("type", "Application").append("count", 1));
//        collection.insertOne(new Document("name", "MongoDB").append("type", "Database").append("count", 2));
//        collection.insertOne(new Document("name", "Embedded").append("type", "Feature").append("count", 3));
//
//        // 查询数据并显示在UI中
//        ListView<String> listView = new ListView<>();
//        collection.find().forEach(doc -> listView.getItems().add(doc.toJson()));
//
//        // 设置JavaFX界面
//        VBox root = new VBox();
//        root.getChildren().addAll(new Label("Data from MongoDB:"), listView);
//        Scene scene = new Scene(root, 400, 300);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("JavaFX with Embedded MongoDB");
//        primaryStage.show();
//    }
//
//    @Override
//    public void stop() throws Exception {
//        super.stop();
//        // 关闭MongoDB客户端
//        mongoClient.close();
//        // 停止嵌入式MongoDB
//        stopEmbeddedMongoDB();
//    }
//
//    private void startEmbeddedMongoDB() throws Exception {
//        MongodStarter starter = MongodStarter.getDefaultInstance();
//        MongodConfig mongodConfig = MongodConfig.builder()
//                .version(de.flapdoodle.embed.mongo.distribution.Version.Main.PRODUCTION)
//                .net(new Net(27017, Network.localhostIsIPv6()))
//                .build();
//
//        mongodExecutable = starter.prepare(mongodConfig);
//        mongod = mongodExecutable.start();
//    }
//
//    private void stopEmbeddedMongoDB() {
//        if (mongod != null) {
//            mongod.stop();
//        }
//        if (mongodExecutable != null) {
//            mongodExecutable.stop();
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
}
