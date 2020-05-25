package ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import solution.Caesar;

import java.util.Random;

public class Main extends Application {
    public int offset=7;


    GridPane pane = new GridPane();
    Scene scene = new Scene(pane, 600, 250);
    Label leftLabel = new Label("加密内容(只能用于英语字母):");
    Label rightLabel = new Label("解密内容(只能用于英语字母):");
    TextArea leftText = new TextArea();
    TextArea rightText = new TextArea();
    Button encBtn = new Button();
    Button decBtn = new Button();
    Button leftPaste = new Button();
    Button leftCopy = new Button();
    Button rightPaste = new Button();
    Button rightCopy = new Button();
    GridPane leftPane = new GridPane();
    GridPane leftBottomPane = new GridPane();
    GridPane centerPane = new GridPane();
    GridPane rightPane = new GridPane();
    GridPane rightBottomPane = new GridPane();
    Label offsetLabel = new Label("                  密钥(0,1,2...25):");
    TextField offsetText = new TextField();
    Button offsetButton = new Button("随机生成");

    Caesar caesar = new Caesar(offset);

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("加密/解密工具");

        encBtn.setText("→→\n加密\n→→");
        encBtn.setMinSize(50,80);
        decBtn.setText("←←\n解密\n←←");
        decBtn.setMinSize(50,80);

        leftPaste.setText("粘贴到此处");
        leftPaste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                leftText.clear();
                leftText.paste();
            }
        });
        leftCopy.setText("从此处复制");
        leftCopy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                leftText.selectAll();
                leftText.copy();
            }
        });

        rightPaste.setText("粘贴到此处");
        rightPaste.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rightText.clear();
                rightText.paste();
            }
        });
        rightCopy.setText("从此处复制");
        rightCopy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                rightText.selectAll();
                rightText.copy();
            }
        });

        leftPane.setHgap(20);
        leftPane.setPadding(new Insets(20,10,20,5));
        leftBottomPane.setHgap(20);
        leftBottomPane.add(leftPaste,0,0);
        leftBottomPane.add(leftCopy,1,0);
        leftPane.add(leftLabel,0,0);
        leftPane.add(leftText,0,1);
        leftPane.add(leftBottomPane,0,2);



        centerPane.setPadding(new Insets(30,5,30,5));
        centerPane.setVgap(20);


        centerPane.add(encBtn,0,0);
        centerPane.add(decBtn,0,1);


        rightPane.setHgap(20);
        rightPane.setPadding(new Insets(20,5,20,10));
        rightBottomPane.setHgap(20);
        rightBottomPane.add(rightPaste,0,0);
        rightBottomPane.add(rightCopy,1,0);
        rightPane.add(rightLabel,0,0);
        rightPane.add(rightText,0,1);
        rightPane.add(rightBottomPane,0,2);


        offsetText.setText(String.valueOf(offset));
        pane.add(offsetLabel,0,0);
        pane.add(offsetText,1,0);
        pane.add(offsetButton,2,0);
        pane.add(leftPane,0,1);
        pane.add(centerPane,1,1);
        pane.add(rightPane,2,1);

        offsetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getRandomOffset();
                offsetText.setText(String.valueOf(offset));
            }
        });

        leftBottomPane.setAlignment(Pos.CENTER);
        rightBottomPane.setAlignment(Pos.CENTER);

        encBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                offset = Math.abs(Integer.parseInt(offsetText.getText())) % 26;
                offsetText.setText(String.valueOf(offset));
                rightText.setText(caesar.encrypt(leftText.getText()));
            }
        });

        decBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                offset = Math.abs(Integer.parseInt(offsetText.getText())) % 26;
                offsetText.setText(String.valueOf(offset));
                leftText.setText(caesar.decrypt(rightText.getText()));
            }
        });
    }

    void getRandomOffset()
    {
        Random rand = new Random();
        offset=rand.nextInt(26);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
