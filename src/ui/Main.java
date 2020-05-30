package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import solution.Caesar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {
    //主场景
    BorderPane mainPane = new BorderPane();
    Scene scene = new Scene(mainPane, 700, 300);
    //凯撒密钥
    TextField caesarKeyTextField;
    //代换表密钥
    List<TextField> subTableKeyTextField = new ArrayList<>();
    //明文密文输入
    TextArea plainTextArea, cipherTextArea;
    //加密解密按钮
    Button encryptButton, decryptButton;
    //布局
    VBox keyPane = new VBox();//密钥全布局
    GridPane textPane = new GridPane();//明文密文
    HBox encDecButtonPane = new HBox();//加解密按钮

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("加密/解密工具");

        initKeyPane();
        initTextFieldPane();
        initDecodeButtonPane();

        //将左中右布局加入到主布局中
        mainPane.setTop(keyPane);
        mainPane.setCenter(textPane);
        mainPane.setBottom(encDecButtonPane);
    }

    /**
     * 设置输入密钥系列布局
     */
    private void initKeyPane() {
        //凯撒
        //设置输入密钥提示标签
        Label caesarKeyHintLabel = new Label("凯撒密码的密钥（0，1，2 ... 25）");
        //密钥输入框
        caesarKeyTextField = new TextField("0");
        //随机生成密钥的按钮
        Button caesarRandomKeyButton = new Button("随机生成");
        caesarRandomKeyButton.setOnAction(event -> getRandomOffset());
        //布局
        HBox caesarPane = new HBox();
        caesarPane.getChildren().add(caesarKeyHintLabel);
        caesarPane.getChildren().add(caesarKeyTextField);
        caesarPane.getChildren().add(caesarRandomKeyButton);

        //代换表
        Label tableHintLabel;
        tableHintLabel = new Label();
        tableHintLabel.setText("代换表");
        GridPane subTablePane = new GridPane();//代换表布局
        //初始化表内容
        Label label;
        TextField textField;
        char ch;
        //小写
        for (int i = 0; i < 26 + 26 + 10; i++) {
            int index;
            if (i < 26) {
                ch = 'a';
                index = i;
            } else if (i < 26 + 26) {
                ch = 'A';
                index = i - 26;
            } else {
                ch = '0';
                index = i - 26 - 26;
            }
            ch = (char) (ch + index);
            String str = String.valueOf(ch);
            label = new Label(str);
            textField = new TextField(str);
            subTableKeyTextField.add(textField);
            int n = i / 26 * 2;
            int m = i % 26;
            subTablePane.add(label, m, n);
            subTablePane.add(subTableKeyTextField.get(i), m, n + 1);
        }

        //布局
        keyPane.getChildren().add(caesarPane);
        keyPane.getChildren().add(tableHintLabel);
        keyPane.getChildren().add(subTablePane);
    }

    /**
     * 设置输入明文密文系列布局
     */
    private void initTextFieldPane() {
        //明文密文输入提示
        Label leftLabel = new Label("明文");
        Label rightLabel = new Label("密文");
        //文本框
        plainTextArea = new TextArea("abc");
        cipherTextArea = new TextArea();
        //布局
        textPane.add(leftLabel, 0, 0);
        textPane.add(rightLabel, 1, 0);
        textPane.add(plainTextArea, 0, 1);
        textPane.add(cipherTextArea, 1, 1);
    }

    /**
     * 设置加解密按钮布局
     */
    private void initDecodeButtonPane() {
        //加密解密的按钮
        encryptButton = new Button();
        decryptButton = new Button();
        encryptButton.setText("→→\t加密\t→→");
        decryptButton.setText("←←\t解密\t←←");
        setEncBtn(encryptButton, caesarKeyTextField, cipherTextArea, plainTextArea);
        setDecBtn(decryptButton, caesarKeyTextField, cipherTextArea, plainTextArea);
        //布局
        encDecButtonPane.getChildren().add(encryptButton);
        encDecButtonPane.getChildren().add(decryptButton);
    }

    /**
     * 设置加密动作
     *
     * @param encButton      要设置为“加密”的按钮
     * @param keyTextArea    密钥的文本框
     * @param cipherTextArea 密文的文本框
     * @param plainTextArea  明文的文本框
     */
    private void setEncBtn(Button encButton, TextField keyTextArea, TextArea cipherTextArea, TextArea plainTextArea) {
        encButton.setOnAction(event -> {
            int tempInt = Integer.parseInt(keyTextArea.getText());
            int key = Math.abs(tempInt) % 26;
            String keyStr = String.valueOf(key);
            keyTextArea.setText(keyStr);
            String plainText = plainTextArea.getText();
            String cipherText = new Caesar(key).encrypt(plainText);//凯撒密码
            cipherTextArea.setText(cipherText);
            caesarSetTable(key);
        });
    }

    /**
     * 设置解密动作
     *
     * @param decButton      要设置为“解密”的按钮
     * @param keyTextArea    密钥的文本框
     * @param cipherTextArea 密文的文本框
     * @param plainTextArea  明文的文本框
     */
    private void setDecBtn(Button decButton, TextField keyTextArea, TextArea cipherTextArea, TextArea plainTextArea) {
        decButton.setOnAction(event -> {
            int tempInt = Integer.parseInt(keyTextArea.getText());
            int offset = Math.abs(tempInt) % 26;
            String keyStr = String.valueOf(offset);
            keyTextArea.setText(keyStr);
            String cipherText = cipherTextArea.getText();
            String plainText = new Caesar(offset).decrypt(cipherText);//凯撒密码
            plainTextArea.setText(plainText);
            caesarSetTable(offset);
        });
    }

    /**
     * 随机生成密钥
     */
    private void getRandomOffset() {
        //生成随机数
        Random rand = new Random();
        int key = rand.nextInt(25);
        //给凯撒密码的文本框赋值
        caesarKeyTextField.setText(String.valueOf(key));
        //给代换表赋值
        caesarSetTable(key);
    }

    /**
     * 用凯撒密码设置代换表
     */
    private void caesarSetTable(int key) {
        for (int i = 0; i < 26 + 26 + 10; i++) {
            char nCh;
            int index = i;
            if (i < 26) {
                index = (index + key) % 26;
                nCh = (char) ((int) 'a' + index);
            } else if (i < 26 + 26) {
                index = (index - 26 + key) % 26;
                nCh = (char) ((int) 'A' + index);
            } else {
                index = (index - 26 - 26 + key) % 10;
                nCh = (char) ((int) '0' + index);
            }
            String newText = String.valueOf(nCh);
            subTableKeyTextField.get(i).setText(newText);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
