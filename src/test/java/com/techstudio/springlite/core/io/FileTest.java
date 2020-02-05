package com.techstudio.springlite.core.io;

import org.junit.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * @author lj
 * @date 2020/2/4
 */
public class FileTest {

    private String location = "F:\\workspace\\src\\spring-lite\\src\\test\\java\\" +
            "com\\techstudio\\springlite\\core\\io\\file-test.properties";

    private static String str = "file:/F:/workspace/src/spring-lite/src/test/java/com/techstudio/springlite/core/io/file-test.properties";

    @Test
    public void classLoaderFile() throws MalformedURLException {
        URL url1 = ClassLoader.getSystemResource("com/techstudio/springlite/core/io/file-test.properties");

        // 在classpath下查找，不能加绝对路径/，因为ClassLoader就是在根路径查找
        URL url2 = this.getClass().getClassLoader().getResource("com/techstudio/springlite/core/io/file-test.properties");

        // 相对：当前class路径下查找
        URL url3 = this.getClass().getResource("file-test.properties");
        // 绝对：classpath下查找
        URL url4 = this.getClass().getResource("/com/techstudio/springlite/core/io/file-test.properties");


        File file = new File(url2.getPath());
        System.out.println("file is exists:" + file.exists());
        URI uri = file.toURI();
        URL url = uri.toURL();
    }

    @Test
    public void outputStreamWrite() throws URISyntaxException, IOException {
        URL url = this.getClass().getResource("file-test.properties");
        byte[] bytes = new byte[]{1, 2, 3, 4};

        OutputStream outputStream = new FileOutputStream(new File(url.toURI()));
        outputStream.write(bytes);
        outputStream.close();
    }

    @Test
    public void inputStreamRead() throws URISyntaxException, IOException {
        URL url = this.getClass().getResource("file-test.properties");

        InputStream inputStream = new FileInputStream(new File(url.toURI()));
        // Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        StringBuilder sb = new StringBuilder();
        byte[] bytes = new byte[10];
        int len;
        while ((len = inputStream.read(bytes)) != -1) {
            sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
        }
        System.out.println(sb.toString());
        inputStream.close();
    }

    /**
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test
    public void write() throws URISyntaxException, IOException {
        URL url = this.getClass().getResource("file-test.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(new File(url.toURI()));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        bufferedWriter.write("1234\n");
        bufferedWriter.write("字符流写入123\n");
        // fileWriter.write(null);
        bufferedWriter.append(null);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    @Test
    public void read() throws URISyntaxException, IOException {
        URL url = this.getClass().getResource("file-test.properties");
        FileInputStream fileInputStream = new FileInputStream(new File(url.toURI()));
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder sb = new StringBuilder();
        char[] chars = new char[10];
        int len;
        while ((len = bufferedReader.read(chars)) != -1) {
            sb.append(chars, 0, len);
        }
        System.out.println(sb.toString());
    }

    /**
     * 统一资源标识符用于标示一个抽象或者物理资源
     * URI是以一种抽象的，高层次概念定义统一资源标识，而URL则是具体的资源标识的方式，URL是URI的一种形式
     * scheme如下
     * data：链接中直接包含经过BASE64编码的数据。
     * file：本地磁盘上的文件。
     * ftp：FTP服务器。
     * http：使用超文本传输协议。
     * mailto：电子邮件的地址。
     * magnet：可以通过对等网络(端对端P2P，如BitTorrent)下载的资源。
     * telnet：基于Telnet的服务的连接。
     * urn：统一资源名(Uniform Resource Name)
     * rmi
     * jar
     * jndi
     * doc
     * jdbc
     * 等等
     * URI的结构是 [scheme:]scheme-specific-part[#fragment] 和 [scheme:][//authority][path][?query][#fragment]
     */
    @Test
    public void uriTest() {
        URI uri;
        try {
            uri = new URI(str);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * 统一资源定位符用于标示网络资源的位置
     */
    public void urlTest() {

    }

}
