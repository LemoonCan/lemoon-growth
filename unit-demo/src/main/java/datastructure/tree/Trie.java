package datastructure.tree;

/**
 * 前缀树
 * https://leetcode-cn.com/problems/implement-trie-prefix-tree/
 *
 * @author lee
 * @since 2022/1/5
 */
public class Trie {
    Trie[] children;
    boolean end;

    public Trie() {
        this.children = new Trie[26];
    }

    /**
     * 26叉树
     *
     * @param word
     */
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';

            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }

            node = node.children[index];
        }
        node.end = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.end;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}
