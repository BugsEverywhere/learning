package indi.simon.learning.test;



import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * 这个工具类是用于将maven jar目录下的_remote.repositories文件中的内容修改好，目的是让
 * 这个jar本地已存在的情况下，不会去远端更新该jar，一般就是从某某公司离职之后，带出来的代码
 * 如果看不了了就要用这个修正一下maven的该文件内容
 */
public class DealRomoteRepositry {

    public static void main(String[] args) throws IOException {

        String basePath = "C:\\Users\\Simons\\.m2\\repository";
        editFileRecur(basePath);

    }


    private static void editFileRecur(String path) throws IOException {
        File baseDir = new File(path);
        File[] children = baseDir.listFiles();
        if (children == null) {
            return;
        }
        for (File singleFile : children) {
            if ("_remote.repositories".equals(singleFile.getName())) {
                editFile(singleFile);
                return;
            }
            if (singleFile.isDirectory()) {
                editFileRecur(singleFile.getAbsolutePath());
            }
        }
    }

    private static void editFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder stringBuilder = new StringBuilder();

        String singleLine = null;
        while ((singleLine = reader.readLine()) != null) {
            if(singleLine.contains("artifactory-central")){
                String[] strArr = singleLine.split("artifactory-central");
                singleLine = strArr[0] + "aliyunmaven=\n";
            }
//            if(singleLine.contains("bytedance")){
//                String[] strArr = singleLine.split("bytedance");
//                singleLine = strArr[0] + "aliyunmaven=\n";
//            }
            stringBuilder.append(singleLine);
        }

        IOUtils.closeQuietly(reader);

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(stringBuilder.toString());
        IOUtils.closeQuietly(writer);
    }

}
