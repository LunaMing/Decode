package ui;

import javafx.application.Application;
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
    //主场景
    GridPane mainPane = new GridPane();
    Scene scene = new Scene(mainPane, 600, 600);

    //中间加密解密按钮
    Button encBtn, decBtn;
    GridPane centerPane;

    //密钥生成按钮
    Label caesarKeyHintLabel;
    TextField caesarKeyTextField;
    Button caesarRandomKeyButton;
    GridPane keyPane;

    //明文密文输入
    Label leftLabel, rightLabel;
    GridPane leftPane, rightPane;
    TextArea leftText, rightText;

    /**
     * 设置输入密钥系列布局
     */
    private void initKeyPane() {
        //设置输入密钥提示标签，右对齐
        caesarKeyHintLabel = new Label();
        caesarKeyHintLabel.setMaxWidth(Double.MAX_VALUE);
        caesarKeyHintLabel.setAlignment(Pos.CENTER_RIGHT);
        caesarKeyHintLabel.setText("凯撒密码的密钥（0，1，2 ... 25）：");

        //密钥输入框
        caesarKeyTextField = new TextField("1");

        //随机生成密钥的按钮
        caesarRandomKeyButton = new Button("随机生成");
        caesarRandomKeyButton.setOnAction(event -> getRandomOffset(caesarKeyTextField));

        //密钥系列加入到主布局
        keyPane = new GridPane();
        keyPane.add(caesarKeyHintLabel, 0, 0);
        keyPane.add(caesarKeyTextField, 0, 1);
        keyPane.add(caesarRandomKeyButton, 0, 2);
    }

    /**
     * 设置输入明文密文系列布局
     */
    private void initTextFieldPane() {
        //明文密文输入提示
        String plainTextHint = "明文";
        String cipherTextHint = "密文";
        String inputHintLabel_back = "（仅限英文字母）：";
        leftLabel = new Label(plainTextHint + inputHintLabel_back);
        rightLabel = new Label(cipherTextHint + inputHintLabel_back);

        //文本框
        leftText = new TextArea("abc");
        rightText = new TextArea();

        //左边布局
        leftPane = new GridPane();
        leftPane.setPadding(new Insets(20, 10, 20, 5));
        //右边布局
        rightPane = new GridPane();
        rightPane.setPadding(new Insets(20, 5, 20, 10));

        setAsidePane(leftPane, leftLabel, leftText);
        setAsidePane(rightPane, rightLabel, rightText);
    }

    /**
     * 设置中间按钮布局
     */
    private void initCenterPane() {
        //中间加密解密的按钮
        encBtn = new Button();
        decBtn = new Button();
        setEncBtn(encBtn, caesarKeyTextField, rightText, leftText);
        setDecBtn(decBtn, caesarKeyTextField, rightText, leftText);

        //中间布局
        centerPane = new GridPane();
        centerPane.setPadding(new Insets(30, 5, 30, 5));
        centerPane.setVgap(20);
        centerPane.add(encBtn, 0, 0);
        centerPane.add(decBtn, 0, 1);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("加密/解密工具");

        initKeyPane();
        initTextFieldPane();
        initCenterPane();

        //将左中右布局加入到主布局中
        mainPane.add(keyPane,0,0);
        mainPane.add(leftPane, 0, 1);
        mainPane.add(centerPane, 1, 1);
        mainPane.add(rightPane, 2, 1);
    }

    /**
     * 设置输入明文密文的布局
     *
     * @param pane     设置的布局
     * @param label    文本标签，指示文本框应该填写的内容
     * @param textArea 文本框
     */
    private void setAsidePane(GridPane pane, Label label, TextArea textArea) {
        pane.setHgap(20);
        pane.add(label, 0, 0);
        pane.add(textArea, 0, 1);
    }

    /**
     * 设置加密按钮
     *
     * @param encButton      要设置为“加密”的按钮
     * @param keyTextArea    密钥的文本框
     * @param cipherTextArea 密文的文本框
     * @param plainTextArea  明文的文本框
     */
    private void setEncBtn(Button encButton, TextField keyTextArea, TextArea cipherTextArea, TextArea plainTextArea) {
        encButton.setText("→→\n加密\n→→");
        encButton.setMinSize(50, 80);

        encButton.setOnAction(event -> {
            int tempInt = Integer.parseInt(keyTextArea.getText());
            int key = Math.abs(tempInt) % 26;
            String keyStr = String.valueOf(key);
            keyTextArea.setText(keyStr);
            String plainText = plainTextArea.getText();
            String cipherText = new Caesar(key).encrypt(plainText);//凯撒密码
            cipherTextArea.setText(cipherText);
        });
    }

    /**
     * 设置解密按钮
     *
     * @param decButton      要设置为“解密”的按钮
     * @param keyTextArea    密钥的文本框
     * @param cipherTextArea 密文的文本框
     * @param plainTextArea  明文的文本框
     */
    private void setDecBtn(Button decButton, TextField keyTextArea, TextArea cipherTextArea, TextArea plainTextArea) {
        decButton.setText("←←\n解密\n←←");
        decButton.setMinSize(50, 80);

        decButton.setOnAction(event -> {
            int tempInt = Integer.parseInt(keyTextArea.getText());
            int offset = Math.abs(tempInt) % 26;
            String keyStr = String.valueOf(offset);
            keyTextArea.setText(keyStr);
            String cipherText = cipherTextArea.getText();
            String plainText = new Caesar(offset).decrypt(cipherText);//凯撒密码
            plainTextArea.setText(plainText);
        });
    }

    /**
     * 随机生成密钥
     *
     * @param offsetText 密钥放置的文本框
     */
    private void getRandomOffset(TextField offsetText) {
        Random rand = new Random();
        int myOffsetKey = rand.nextInt(26);
        offsetText.setText(String.valueOf(myOffsetKey));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
