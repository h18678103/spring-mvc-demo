package word;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class BadWordsUtil {
    public static final int WORDS_MAX_LENGTH = 10;
    //敏感词列表
    public static Map[] badWordsList = null;
    //敏感词索引
    public static Map<String, Integer> wordIndex = new HashMap();

    /*
    * 初始化敏感词库
    */
    public static void initbadWordsList() throws IOException {
        if (badWordsList == null) {
            badWordsList = new Map[WORDS_MAX_LENGTH];
            for (int i = 0; i < badWordsList.length; i++) {
                badWordsList[i] = new HashMap();
            }
        }
//敏感词词库所在目录,这里为txt文本,一个敏感词一行
        String path = BadWordsUtil.class.getClassLoader().getResource("/badWords.txt").getPath();
        System.out.println(path);
        List<String> words = FileUtils.readLines(new File(path), "UTF-8");
        for (String w : words) {
            if (StringUtils.isNotBlank(w)) {
//将敏感词按长度存入map
                badWordsList[w.length()].put(w.toLowerCase(), "");
                Integer index = wordIndex.get(w.substring(0, 1));
//生成敏感词索引,存入map
                if (index == null) {
                    index = 0;
                }
                int x = (int) Math.pow(2, w.length());
                index = (index | x);
                wordIndex.put(w.substring(0, 1), index);
            }
        }
    }

    /**
     * 检索敏感词
     *
     * @param content
     * @return
     */
    public static List searchBanWords(String content) {
        if (badWordsList == null) {
            try {
                initbadWordsList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        List result = new ArrayList();
        for (int i = 0; i < content.length(); i++) {
            Integer index = wordIndex.get(content.substring(i, i + 1));
            int p = 0;
            while ((index != null) && (index > 0)) {
                p++;
                index = index >> 1;
                String sub = "";
                if ((i + p) < (content.length() - 1)) {
                    sub = content.substring(i, i + p);
                } else {
                    sub = content.substring(i);
                }
                if (((index % 2) == 1) && badWordsList[p].containsKey(sub)) {
                    result.add(content.substring(i, i + p));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        String content = "含有敏感词的测试";
        BadWordsUtil.initbadWordsList();
        List<String> badWordList = BadWordsUtil.searchBanWords(content);
        if (badWordList.size() == 0) {
            System.out.println("没有找到敏感词!");
        } else {
            for (String s : badWordList) {
                System.out.println("找到敏感词:" + s);
            }
        }
    }
}
