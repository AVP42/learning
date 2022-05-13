//按字典 wordList 完成从单词 beginWord 到单词 endWord 转化，一个表示此过程的 转换序列 是形式上像 beginWord -> s
//1 -> s2 -> ... -> sk 这样的单词序列，并满足： 
//
// 
// 
// 
// 每对相邻的单词之间仅有单个字母不同。 
// 转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单
//词。 
// sk == endWord 
// 
//
// 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的
// 最短转换序列 ，如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。 
//
// 
//
// 示例 1： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g","cog"]
//输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
//解释：存在 2 种最短的转换序列：
//"hit" -> "hot" -> "dot" -> "dog" -> "cog"
//"hit" -> "hot" -> "lot" -> "log" -> "cog"
// 
//
// 示例 2： 
//
// 
//输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","lo
//g"]
//输出：[]
//解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= beginWord.length <= 5 
// endWord.length == beginWord.length 
// 1 <= wordList.length <= 5000 
// wordList[i].length == beginWord.length 
// beginWord、endWord 和 wordList[i] 由小写英文字母组成 
// beginWord != endWord 
// wordList 中的所有单词 互不相同 
// 
// 
// 
// Related Topics 广度优先搜索 哈希表 字符串 回溯 
// 👍 570 👎 0


package com.avp42.leetcode.editor.cn;

import java.util.*;

/**
 * 126 单词接龙 II
 */
public class WordLadderIi{
    public static void main(String[] args) {
        Solution solution = new WordLadderIi().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<String>> ret = new ArrayList<>();
    List<String> buffer = new ArrayList<>();
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> words = new HashSet<>(wordList);
        if(!words.contains(endWord)) return Collections.emptyList();
        Map<String, Integer> steps = new HashMap<>();
        Map<String, List<String>> from = new HashMap<>();
        steps.put(beginWord,0);
        buffer.add(endWord);
        bfs(beginWord, endWord, words, steps, from);
        return ret;
    }

    private void dfs(String begin, String end, Map<String, List<String>> from, List<List<String>> ret, List<String> buffer) {
        System.out.println("end:" +end);
        if(begin.equals(end)){
            List<String> temp = new ArrayList<>(buffer);
            Collections.reverse(temp);
            ret.add(temp);
            return;
        }
        for(String word: from.get(end)){
            buffer.add(word);
            dfs(begin, word, from, ret, buffer);
            buffer.remove(buffer.size() - 1);
        }
    }

    private void bfs(String beginWord, String endWord, Set<String> words, Map<String, Integer> steps, Map<String, List<String>> from) {
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        boolean found = false;
        while(!q.isEmpty()){
            int size = q.size();
            while(size -- > 0){
                String word = q.poll();
                int step = steps.get(word);
                for(int i = 0 ;i < word.length(); i++){
                    for(int j = 0; j < 26; j ++){
                        String newWord = word.substring(0, i) + (char)(j +'a') + word.substring(i + 1);
                        if(!words.contains(newWord)) continue;
                        if (steps.containsKey(newWord)) {
                            // 为了找出所有的解，如果当前层遍历过newWord，那么也需要记录newWord是由word转变来
                            if(step + 1 == steps.get(newWord)){
                                from.get(newWord).add(word);
                            }
                            continue;
                        }
                        // System.out.println("newword:" + newWord);
                        from.putIfAbsent(newWord, new ArrayList<>());
                        from.get(newWord).add(word);
                        steps.put(newWord, steps.get(word) + 1);
                        if(newWord.equals(endWord)){
                            System.out.println("ss");
                            found = true;
                        }
                        q.offer(newWord);
                    }
                }
            }
            if(found){
                break;
            }
        }

        if(found){
            dfs(beginWord, endWord, from, ret, buffer);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}