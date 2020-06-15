package ui;

import java.io.*;

public class FileReadWrite {

    /**
     * 传入txt路径读取txt文件
     *
     * @param txtPath 文件路径
     * @return 返回读取到的内容
     */
    public static String readTxt(String txtPath) {
        StringBuilder str = new StringBuilder();
        File file = new File(txtPath);
        Reader reader;
        try {
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    str.append((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(str);
    }


    /**
     * 使用FileOutputStream来写入txt文件
     *
     * @param txtPath txt文件路径
     * @param content 需要写入的文本
     */
    public static void writeTxt(String txtPath, String content) {
        FileOutputStream fileOutputStream;
        File file = new File(txtPath);
        try {
            if (!file.exists()) {
                //判断文件是否存在，如果不存在就新建一个txt
                boolean b = file.createNewFile();
                if (!b) throw new Exception();
            }
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(content.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
