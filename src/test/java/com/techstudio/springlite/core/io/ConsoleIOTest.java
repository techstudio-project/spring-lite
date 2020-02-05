package com.techstudio.springlite.core.io;

import java.io.*;

/**
 * @author lj
 * @date 2020/2/4
 */
public class ConsoleIOTest {

    public static void main(String[] args) throws IOException {
        outTest();
    }

    /**
     * 读取单个字符
     *
     * @throws IOException
     */
    public static void readCharTest() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符：");
        int i = bufferedReader.read();
        char c = (char) i;
        System.out.println("输入字符为：" + c);
    }

    /**
     * 读取字符数组
     *
     * @throws IOException
     */
    public static void readCharArrayTest() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("输入字符：");
        char[] chars = new char[10];
        int i = bufferedReader.read(chars);
        System.out.println("输入字符为：" + new String(chars));
    }

    /**
     * 读取一行字符
     *
     * @throws IOException
     */
    public static void readLineTest() throws IOException {
        InputStream in = System.in;// BufferedInputStream
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        System.out.println("输入字符：");
        String s = bufferedReader.readLine();
        System.out.println("输入字符为：" + s);
    }

    /**
     * 模拟 System.out.println()过程
     *
     * @throws IOException
     */
    public static void outTest() throws IOException {
        PrintStream printStream = System.out;
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(printStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write("BufferedWriter test\n");
        bufferedWriter.flush();

        PrintWriter p = new PrintWriter(outputStreamWriter);
        p.write("PrintWriter test");
        p.flush();
    }

}
