package ui;

import algorithm.Frequency;
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
import algorithm.SubstitutionTable;

import java.util.*;

public class Main extends Application {
    //主场景
    BorderPane mainPane = new BorderPane();
    Scene scene = new Scene(mainPane, 700, 700);
    //凯撒密钥
    TextField caesarKeyTextField;
    //代换表密钥
    List<Label> subTableKeyLabel = new ArrayList<>();
    List<TextField> subTableKeyTextField = new ArrayList<>();
    //代换表
    SubstitutionTable subTable;
    //明文密文输入
    TextArea plainTextArea, cipherTextArea;
    //布局
    VBox keyPane = new VBox();//密钥全布局
    GridPane textPane = new GridPane();//明文密文
    HBox encDecButtonPane = new HBox();//加解密按钮

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle("凯撒密码、代换表、频率分析");

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
        Label caesarHintLabel = new Label("*** 凯撒密码 ***");
        //设置输入密钥提示标签
        Label caesarKeyHintLabel = new Label("密钥");
        //密钥输入框
        caesarKeyTextField = new TextField("0");
        //随机生成密钥的按钮
        Button caesarRandomKeyButton = new Button("随机生成密钥");
        caesarRandomKeyButton.setOnAction(event -> nextRandomKey());
        //布局
        HBox caesarPane = new HBox();
        caesarPane.getChildren().addAll(caesarKeyHintLabel, caesarKeyTextField, caesarRandomKeyButton);
        caesarPane.setSpacing(5);

        //代换表
        Label tableHintLabel;
        tableHintLabel = new Label();
        tableHintLabel.setText("*** 代换表 ***");
        GridPane subTablePane = new GridPane();//代换表布局
        //初始化表内容
        Label label;
        TextField textField;
        char ch;
        //同时初始化标签和文本框
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
            subTableKeyLabel.add(label);
            subTableKeyTextField.add(textField);
            int n = i / 26 * 2;
            int m = i % 26;
            subTablePane.add(subTableKeyLabel.get(i), m, n);
            subTablePane.add(subTableKeyTextField.get(i), m, n + 1);
        }

        //代换表加密解密按钮
        Button encryptButton = new Button(" 块加密 →→→ ");
        Button decryptButton = new Button(" ←←← 块解密 ");
        encryptButton.setOnAction(event -> encryptAction(cipherTextArea, plainTextArea));
        decryptButton.setOnAction(event -> decryptAction(cipherTextArea, plainTextArea));
        //流加密解密按钮
        Button encButton = new Button(" 流加密 →→→ ");
        Button decButton = new Button(" ←←← 流解密 ");
        //布局
        HBox buttonPane = new HBox();
        buttonPane.getChildren().addAll(encryptButton, decryptButton, encButton, decButton);

        //布局
        keyPane.getChildren().addAll(caesarHintLabel, caesarPane, tableHintLabel, subTablePane, buttonPane);
        keyPane.setSpacing(10);
    }

    /**
     * 设置输入明文密文系列布局
     */
    private void initTextFieldPane() {
        //明文密文输入提示
        Label leftLabel = new Label("明文");
        Label rightLabel = new Label("密文");
        //文本框
        plainTextArea = new TextArea("abcDEF");
        cipherTextArea = new TextArea();
        //频率分析
        Label plainFreqResult = new Label("（请点击按钮开始分析）");
        Label cipherFreqResult = new Label("（请点击按钮开始分析）");
        Button plainFreqButton = new Button("明文频率分析");
        Button cipherFreqButton = new Button("密文频率分析");
        plainFreqButton.setOnAction(event -> {
            String plainText = plainTextArea.getText();
            if (plainText.isEmpty()) {
                //如果明文是空的就不用分析了
                plainFreqResult.setText("（请输入明文内容）");
            } else {
                //分析
                TreeMap<Character, Double> map = Frequency.countFrequency(plainText);
                List<Character> list = Frequency.sort(plainText);
                //将分析结果做成string
                StringBuilder result = new StringBuilder();
                for (Character key : list) {
                    result.append("【");
                    result.append(key);
                    result.append("】：");
                    result.append(map.get(key));
                    result.append("\n");
                }
                //放到label输出
                plainFreqResult.setText(String.valueOf(result));
            }
        });
        cipherFreqButton.setOnAction(event -> {
            String cipherText = cipherTextArea.getText();
            if (cipherText.isEmpty()) {
                cipherFreqResult.setText("（请输入密文内容）");
            } else {
                //分析
                TreeMap<Character, Double> map;
                map = Frequency.countFrequency(cipherText);
                List<Character> list = Frequency.sort(cipherText);
                //list->string
                StringBuilder result = new StringBuilder();
                for (Character key : list) {
                    result.append("【");
                    result.append(key);
                    result.append("】：");
                    result.append(map.get(key));
                    result.append("\n");
                }
                //放到label输出
                cipherFreqResult.setText(String.valueOf(result));
            }
        });
        //布局
        textPane.add(leftLabel, 0, 0);
        textPane.add(rightLabel, 1, 0);
        textPane.add(plainTextArea, 0, 1);
        textPane.add(cipherTextArea, 1, 1);
        textPane.add(plainFreqButton, 0, 2);
        textPane.add(cipherFreqButton, 1, 2);
        textPane.add(plainFreqResult, 0, 3);
        textPane.add(cipherFreqResult, 1, 3);
    }

    /**
     * 设置底部按钮布局
     */
    private void initDecodeButtonPane() {

        //代换表按钮
        Button keyInputButton = new Button("导入代换表");
        Button keyOutputButton = new Button("导出代换表");
        String pathStr = "res/key.txt";//代换表文件路径
        keyInputButton.setOnAction(event -> inputKeyTable(pathStr));
        keyOutputButton.setOnAction(event -> outputKeyTable(pathStr));
        //导入明文密文按钮
        Button plainTextInputButton = new Button("导入明文");
        Button plainTextOutputButton = new Button("导出明文");
        Button cipherTextInputButton = new Button("导入密文");
        Button cipherTextOutputButton = new Button("导出密文");
        String plainPathStr = "res/plain.txt";//明文文件路径
        String cipherPathStr = "res/cipher.txt";//密文文件路径
        plainTextInputButton.setOnAction(event -> {
            String s = FileReadWrite.readTxt(plainPathStr);
            plainTextArea.setText(s);
        });
        plainTextOutputButton.setOnAction(event -> {
            String s = plainTextArea.getText();
            FileReadWrite.writeTxt(plainPathStr, s);
        });
        cipherTextInputButton.setOnAction(event -> {
            String s = FileReadWrite.readTxt(cipherPathStr);
            cipherTextArea.setText(s);
        });
        cipherTextOutputButton.setOnAction(event -> {
            String s = cipherTextArea.getText();
            FileReadWrite.writeTxt(cipherPathStr, s);
        });

        //布局
        encDecButtonPane.getChildren().addAll(plainTextInputButton, plainTextOutputButton,
                cipherTextInputButton, cipherTextOutputButton,
                keyInputButton, keyOutputButton);
        encDecButtonPane.setSpacing(20);//设置按钮间距
    }

    /**
     * 导入代换表
     *
     * @param pathStr 文件路径，如果不存在会报错
     */
    private void inputKeyTable(String pathStr) {
        //txt->string
        String s = FileReadWrite.readTxt(pathStr);
        //string->table
        if (s != null) {
            for (int i = 0; i < s.length(); i++) {
                String ch = String.valueOf(s.charAt(i));
                if (i < 26 + 26 + 10) {
                    subTableKeyTextField.get(i).setText(ch);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * 导出代换表
     *
     * @param pathStr 文件路径，如果不存在会创建
     */
    private void outputKeyTable(String pathStr) {
        //代换表->string
        StringBuilder keyStr = new StringBuilder();
        for (TextField textField : subTableKeyTextField) {
            keyStr.append(textField.getText());
        }
        //string->txt
        FileReadWrite.writeTxt(pathStr, keyStr.toString());
    }

    /**
     * 初始化代换表
     * 就是把UI上面的密钥传给代换表的算法类。
     */
    private void initSubTable() {
        HashMap<Character, Character> hashMap = new HashMap<>();
        String s;
        char k, v;
        for (int i = 0; i < 26 + 26 + 10; i++) {
            s = subTableKeyLabel.get(i).getText();
            k = s.charAt(0);
            s = subTableKeyTextField.get(i).getText();
            v = s.charAt(0);
            hashMap.put(k, v);
        }
        subTable = new SubstitutionTable(hashMap);
    }

    /**
     * 设置加密动作
     *
     * @param cipherTextArea 密文的文本框
     * @param plainTextArea  明文的文本框
     */
    private void encryptAction(TextArea cipherTextArea, TextArea plainTextArea) {
        //获取明文
        String plainText = plainTextArea.getText();
        //初始化代换表
        initSubTable();
        //加密
        String cipherText = subTable.encrypt(plainText);
        cipherTextArea.setText(cipherText);
    }

    /**
     * 设置解密动作
     *
     * @param cipherTextArea 密文的文本框
     * @param plainTextArea  明文的文本框
     */
    private void decryptAction(TextArea cipherTextArea, TextArea plainTextArea) {
        //获取密文
        String cipherText = cipherTextArea.getText();
        //初始化代换表
        initSubTable();
        //解密
        String plainText = subTable.decrypt(cipherText);
        plainTextArea.setText(plainText);
    }

    /**
     * 随机生成密钥
     */
    private void nextRandomKey() {
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
        initSubTable();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
