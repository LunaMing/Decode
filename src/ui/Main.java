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
    Scene scene = new Scene(mainPane, 600, 250);

    //中间加密解密按钮
    Button encBtn, decBtn;
    GridPane centerPane;

    //随机密钥
    Label keyHintLabel;
    TextField keyTextField;
    Button randomKeyButton;

    //明文密文输入
    Label leftLabel, rightLabel;
    GridPane leftPane, rightPane;
    Button leftPaste, leftCopy, rightPaste, rightCopy;
    TextArea leftText, rightText;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("加密/解密工具");

        //设置输入密钥提示标签，右对齐
        keyHintLabel = new Label();
        keyHintLabel.setMaxWidth(Double.MAX_VALUE);
        keyHintLabel.setAlignment(Pos.CENTER_RIGHT);
        keyHintLabel.setText("密钥（0，1，2 ... 25）：");
        mainPane.add(keyHintLabel, 0, 0);
        
        //密钥输入框
        keyTextField = new TextField("1");
        mainPane.add(keyTextField, 1, 0);

        //随机生成密钥的按钮
        randomKeyButton = new Button("随机生成");
        randomKeyButton.setOnAction(event -> getRandomOffset(keyTextField));
        mainPane.add(randomKeyButton, 2, 0);

        //左右文本框和按钮
        leftPane = new GridPane();
        rightPane = new GridPane();
        leftPaste = new Button();
        leftCopy = new Button();
        rightPaste = new Button();
        rightCopy = new Button();
        leftText = new TextArea("a");
        rightText = new TextArea();
        String plainTextHint = "加";
        String cipherTextHint = "解";
        String inputHintLabel_front = "请输入";
        String inputHintLabel_back = "密内容（仅限英文字母）：";
        leftLabel = new Label(inputHintLabel_front + plainTextHint + inputHintLabel_back);
        rightLabel = new Label(inputHintLabel_front + cipherTextHint + inputHintLabel_back);
        setPasteButton(leftPaste, leftText);
        setPasteButton(rightPaste, rightText);
        setCopyButton(leftCopy, leftText);
        setCopyButton(rightCopy, rightText);
        leftPane.setPadding(new Insets(20, 10, 20, 5));
        rightPane.setPadding(new Insets(20, 5, 20, 10));
        setAsidePane(leftPane, leftPaste, leftCopy, leftLabel, leftText);
        setAsidePane(rightPane, rightPaste, rightCopy, rightLabel, rightText);

        mainPane.add(leftPane, 0, 1);
        mainPane.add(rightPane, 2, 1);

        //中间加密解密的按钮
        encBtn = new Button();
        decBtn = new Button();
        setEncBtn(encBtn, keyTextField, rightText, leftText);
        setDecBtn(decBtn, keyTextField, rightText, leftText);

        centerPane = new GridPane();
        centerPane.setPadding(new Insets(30, 5, 30, 5));
        centerPane.setVgap(20);
        centerPane.add(encBtn, 0, 0);
        centerPane.add(decBtn, 0, 1);

        mainPane.add(centerPane, 1, 1);
    }

    /**
     * 设置输入明文密文的布局
     *
     * @param pane        设置的布局
     * @param pasteButton 粘贴按钮
     * @param copyButton  复制按钮
     * @param label       文本标签，指示文本框应该填写的内容
     * @param textArea    文本框
     */
    private void setAsidePane(GridPane pane, Button pasteButton, Button copyButton, Label label, TextArea textArea) {

        GridPane bottomPane = new GridPane();
        bottomPane.setHgap(20);
        bottomPane.add(pasteButton, 0, 0);
        bottomPane.add(copyButton, 1, 0);
        bottomPane.setAlignment(Pos.CENTER);

        pane.setHgap(20);
        pane.add(label, 0, 0);
        pane.add(textArea, 0, 1);
        pane.add(bottomPane, 0, 2);
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
            int offset = Math.abs(tempInt) % 26;
            String keyStr = String.valueOf(offset);
            keyTextArea.setText(keyStr);
            String plainText = plainTextArea.getText();
            String cipherText = new Caesar(offset).encrypt(plainText);//凯撒密码
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
     * 设置粘贴按钮
     *
     * @param pasteButton 要设置为“粘贴”的按钮
     * @param textarea    要绑定的文本框，设置后完成后，点击按钮，会将文本粘贴到这个文本框里面
     */
    private void setPasteButton(Button pasteButton, TextArea textarea) {
        pasteButton.setText("粘贴到此处");
        pasteButton.setOnAction(event -> {
            textarea.clear();
            textarea.paste();
        });
    }

    /**
     * 设置复制按钮
     *
     * @param copyButton 要设置为“复制”的按钮
     * @param textarea   要绑定的文本框，设置后完成后，点击按钮，会将这个文本框里面的文字复制出来
     */
    private void setCopyButton(Button copyButton, TextArea textarea) {
        copyButton.setText("从此处复制");
        copyButton.setOnAction(event -> {
            textarea.selectAll();
            textarea.copy();
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
