package indi.simon.learning.leetcode.ant;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class ReadFile {

    private static List<File> fileList;
    private static List<FileWriter> writerList;
    private static Map<Character, ComparableChar> characterLongMap;

    private static final int TOP_N = 10;

    public static void main(String[] args) throws IOException {

        File inputFile = new File("C:\\Users\\zhuoc\\Desktop\\test\\testSourceFile.txt");

        characterLongMap = new HashMap<>();
        int splitFileCount = 50;

        //新建50个文件用于存储所有的单词
        fileList = new ArrayList<>();
        for (int i = 0; i < splitFileCount; i++) {
            fileList.add(new File("C:\\Users\\zhuoc\\Desktop\\test\\file_" + i));
        }

        //新建同样数量的IO FileWriter，缓存起来，用于在统计词频时不需要重复创建writer，节省时间
        writerList = new ArrayList<>();
        for (File file : fileList) {
            writerList.add(new FileWriter(file, true));
        }

        //此方法执行完，返回的是行数，并且已经将所有单词hash到对应的文件，将所有的字符计数完毕存入了map
        int lineCountRes = splitLine(inputFile);

        //将一步创建的writer关闭掉
        for (FileWriter writer : writerList) {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }

        //对字符map排序得到字符的前十，entryList的前十位即为出现次数排名前十的字符
        List<Map.Entry<Character, ComparableChar>> entryList = new ArrayList<>(characterLongMap.entrySet());
        List<ComparableChar> charList = entryList.stream().map(Map.Entry::getValue).sorted((o1, o2) -> Integer.compare(o2.count, o1.count)).collect(Collectors.toList());
        charList = charList.subList(0, 10);

        //下面对50个文件里面的单词开始计数，计数结果取top N个单词及其出现频次，直接回写覆盖文件
        for (File singleFile : fileList) {
            countWordSingleFile(singleFile);
        }

        //开始merge文件，从第二个文件开始，依次merge进第一个文件
        for (int i = 1; i < fileList.size(); i++) {
            mergeTwoFile(fileList.get(0), fileList.get(i));
        }

        //文件中的即为频次top N的单词
        List<ComparableWord> topList = parseFile(fileList.get(0));

    }

    private static int splitLine(File file) {
        FileInputStream in = null;
        int lineCount = 0;
        try {
            in = new FileInputStream(file);
            byte[] tmpArr = new byte[50];
            String lastSentence = "";
            while (in.read(tmpArr) != -1) {
                //本次截取的字符串
                String str = new String(tmpArr, StandardCharsets.UTF_8);
                //清空缓存字节数组
                Arrays.fill(tmpArr, (byte) 0);
                if (str.contains("\n")) {
                    //本次截取里面包含换行符，需要将每一行拎出来
                    String[] strArr = str.split("\n");
                    for (int i = 0; i < strArr.length; i++) {
                        if (i == 0) {
                            //本次截取的第一行
                            //行数加一
                            lineCount++;
                            //与上一次截取的最后一行连起来处理
                            dealWithOneLine(lastSentence + strArr[i]);
                            if (strArr.length == 1 && str.endsWith("\n")) {
                                lastSentence = "";
                            }
                        } else if (i == strArr.length - 1) {
                            //本次截取的最后一行
                            if (str.endsWith("\n")) {
                                //行数加一
                                lineCount++;
                                //最后一行置为空字符串
                                lastSentence = "";
                                //执行操作
                                dealWithOneLine(lastSentence + strArr[i]);
                            } else {
                                //一般情况，最后一个字符不是换行符，行数不增加，也不执行操作，交给下一次截取的第一行来合并处理。
                                lastSentence = strArr[i];
                            }
                        } else {
                            //本次截取的中间某行
                            //行数加一
                            lineCount++;
                            //执行操作
                            dealWithOneLine(strArr[i]);
                        }
                    }
                } else {
                    //本次截取不包含换行符，仅仅将本次的截取与上次截取的最后一行连起来
                    lastSentence = lastSentence + str;
                }
            }
            //处理最后一行
            dealWithOneLine(lastSentence);
            lineCount++;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lineCount;
    }

    private static void dealWithOneLine(String singleLine) {

        singleLine = singleLine.replaceAll("\r", "");
        singleLine = singleLine.replaceAll("\\.", "");
        singleLine = singleLine.replaceAll(":", "");
        singleLine = singleLine.replaceAll(",", "");
        String[] words = singleLine.split(" ");


        //将每个单词hash之后，根据自己的归属写入对应文件，以换行符分隔
        for (String singleWord : words) {
            if ("".equals(singleWord)) {
                continue;
            }
            int hash = Math.abs(singleWord.hashCode());
            int fileIndex = hash % 50;
            FileWriter writer = null;
            try {
                writer = writerList.get(fileIndex);
                writer.write((singleWord + "\n"));
            } catch (IOException e) {
                //记日志
            }
            //数单词中的字符
            countChar(singleWord);
        }
    }

    private static void countChar(String word) {
        char[] charArr = word.toCharArray();
        for (char c : charArr) {
            //如果不是英文字母则跳过
            if (!isEnglishChar(c)) {
                continue;
            }
            ComparableChar comparableChar = characterLongMap.get(c);
            if (comparableChar == null) {
                comparableChar = new ComparableChar(c);
                characterLongMap.put(c, comparableChar);
            } else {
                comparableChar.count = comparableChar.count + 1;
            }
        }

    }

    private static boolean isEnglishChar(char c) {
        return (c >= 65 && c <= 90) || (c >= 97 && c <= 122);
    }

    private static void countWordSingleFile(File file) {

        //记录单词的hash字典表
        Map<String, ComparableWord> map = new HashMap<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String singleLine = null;
            while ((singleLine = reader.readLine()) != null) {
                ComparableWord word = map.get(singleLine);
                if (word == null) {
                    word = new ComparableWord(singleLine);
                    map.put(singleLine, word);
                } else {
                    word.count = word.count + 1;
                }
            }
        } catch (IOException e) {
            //记日志
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //记日志
                }
            }
        }

        //开始对字典表中的单词按频次排序，取前top N
        List<Map.Entry<String, ComparableWord>> entryList = new ArrayList<>(map.entrySet());
        List<ComparableWord> comparableWordList = entryList.stream().map(Map.Entry::getValue).sorted((o1, o2) -> Integer.compare(o2.count, o1.count)).collect(Collectors.toList());

        writeTopToFile(file, comparableWordList);
    }

    private static void mergeTwoFile(File file1, File file2) {
        //将两个文件中的单词放入一个list
        List<ComparableWord> fileList = parseFile(file1);
        fileList.addAll(parseFile(file2));

        //对list中排序
        fileList.sort((o1, o2) -> Integer.compare(o2.count, o1.count));

        //将前一百个回写file1
        writeTopToFile(file1, fileList);
    }

    private static void writeTopToFile(File file, List<ComparableWord> comparableWordList) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            for (int i = 0; i < TOP_N; i++) {
                //有可能该文件内单词个数都不够TOP_N个
                if (i >= comparableWordList.size()) {
                    break;
                }
                fileOutputStream.write((comparableWordList.get(i).word + "," + comparableWordList.get(i).count + "\n").getBytes());
            }
            fileOutputStream.flush();
        } catch (IOException e) {
            //记日志
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    //记日志
                }
            }
        }
    }

    private static List<ComparableWord> parseFile(File file) {
        List<ComparableWord> list = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String singleLine = null;
            while ((singleLine = reader.readLine()) != null) {
                String[] arr = singleLine.split(",");
                ComparableWord comparableWord = new ComparableWord(arr[0]);
                comparableWord.count = Integer.parseInt(arr[1]);
                list.add(comparableWord);
            }
        } catch (IOException e) {
            //记日志
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //记日志
                }
            }
        }
        return list;
    }

    static class Comparable {
        int count;
    }

    static class ComparableWord extends Comparable {

        public ComparableWord(String word) {
            this.word = word;
            super.count = 1;
        }

        public String word;
    }

    static class ComparableChar extends Comparable {

        public ComparableChar(char element) {
            this.element = element;
            super.count = 1;
        }

        public char element;
    }

}
