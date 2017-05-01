package com.kaiba.demo.structure.skiplist;

/**
 * Created by kaiba on 2017/5/1.
 */
import java.util.*;

public class SkipList {
    public SkipListEntry head; // 顶层的第一个元素
    public SkipListEntry tail; // 顶层的最后一个元素

    public int n; // 跳跃表中的元素个数

    public int h; // 跳跃表的高度
    public Random r; // 投掷硬币

    public SkipList() // 默认构造函数...
    {
        SkipListEntry p1, p2;

        p1 = new SkipListEntry(SkipListEntry.negInf, null);
        p2 = new SkipListEntry(SkipListEntry.posInf, null);

        head = p1;
        tail = p2;

        p1.right = p2;
        p2.left = p1;

        n = 0;
        h = 0;
        r = new Random();
    }

    /** 返回 包含的元素个数 */
    public int size() {
        return n;
    }

    /** 跳跃表是否为空 */
    public boolean isEmpty() {
        return (n == 0);
    }

    //在最下面一层，找到要插入的位置前面的那个key
    public SkipListEntry findEntry(String k) {
        SkipListEntry p;
        p = head;

        while (true) {
            /**
             * 一直向右找，例: k=34.
             * 10 ---> 20 ---> 30 ---> 40 ^ | p 会在30处停止
             * --------------------------------------------
             ***/
            while (p.right.key != SkipListEntry.posInf
                    && p.right.key.compareTo(k) <= 0) {
                p = p.right;
                //	System.out.println(">>>> " + p.key);
            }
            // 如果还有下一层，就到下一层继续查找
            if (p.down != null) {
                p = p.down;
                //System.out.println("vvvv " + p.key);
            } else
                break; // 到了最下面一层 就停止查找
        }

        return (p); // p.key <= k
    }

    /** 返回和key绑定的值 */
    public Integer get(String k) {
        SkipListEntry p;

        p = findEntry(k);

        if (k.equals(p.getKey()))
            return (p.value);
        else
            return (null);
    }

    /** 放一个key-value到跳跃表中, 替换原有的并返回 */
    public Integer put(String k, Integer v) {
        SkipListEntry maxEntryInLevelZero, newEntryInCurrentLevel;
        int currentLevel;

        maxEntryInLevelZero = findEntry(k);

        if (k.equals(maxEntryInLevelZero.getKey())) {
            Integer old = maxEntryInLevelZero.value;
            maxEntryInLevelZero.value = v;
            return (old);
        }

        //在Level 0层插入Entry
        newEntryInCurrentLevel = new SkipListEntry(k, v);
        newEntryInCurrentLevel.left = maxEntryInLevelZero;
        newEntryInCurrentLevel.right = maxEntryInLevelZero.right;
        maxEntryInLevelZero.right.left = newEntryInCurrentLevel;
        maxEntryInLevelZero.right = newEntryInCurrentLevel;

        currentLevel = 0; // 当前层 level = 0

        while (r.nextDouble() < 0.5) {

            //如果超出了高度，需要重新建一个顶层
            if (currentLevel >= h) {
                SkipListEntry upLevelHead, upLevelTail;

                h = h + 1;
                upLevelHead = new SkipListEntry(SkipListEntry.negInf, null);
                upLevelTail = new SkipListEntry(SkipListEntry.posInf, null);

                upLevelHead.right = upLevelTail;
                upLevelHead.down = head;

                upLevelTail.left = upLevelHead;
                upLevelTail.down = tail;

                head.up = upLevelHead;
                tail.up = upLevelTail;

                head = upLevelHead;
                tail = upLevelTail;
            }

            //找到currentLevle的前一个Entry
            while (maxEntryInLevelZero.up == null) {
                maxEntryInLevelZero = maxEntryInLevelZero.left;
            }
            maxEntryInLevelZero = maxEntryInLevelZero.up;

            SkipListEntry upLevelNewEntry;

            upLevelNewEntry = new SkipListEntry(k, null);
            upLevelNewEntry.left = maxEntryInLevelZero;
            upLevelNewEntry.right = maxEntryInLevelZero.right;
            upLevelNewEntry.down = newEntryInCurrentLevel;

            maxEntryInLevelZero.right.left = upLevelNewEntry;
            maxEntryInLevelZero.right = upLevelNewEntry;
            newEntryInCurrentLevel.up = upLevelNewEntry;

            newEntryInCurrentLevel = upLevelNewEntry; // 进行下一层迭代
            currentLevel = currentLevel + 1; // 当前层 +1

        }
        n = n + 1;

        return (null); // No old value
    }

    public Integer remove(String k) {
        SkipListEntry maxEntryInLevelZero = findEntry(k);
        if (k.equals(maxEntryInLevelZero.getKey())) {
            Integer value = maxEntryInLevelZero.value;

            maxEntryInLevelZero.left.right = maxEntryInLevelZero.right;
            maxEntryInLevelZero.right.left = maxEntryInLevelZero.left;

            maxEntryInLevelZero = maxEntryInLevelZero.up;
            while(maxEntryInLevelZero != null){
                //该层只剩下头尾2Entry和要删除的Entry，则直接删除该层
                if(SkipListEntry.negInf.equals(maxEntryInLevelZero.left.getKey())
                        && SkipListEntry.posInf.equals(maxEntryInLevelZero.right.getKey())){
                    head = maxEntryInLevelZero.left.down;
                    tail = maxEntryInLevelZero.right.down;
                }else{
                    maxEntryInLevelZero.left.right = maxEntryInLevelZero.right;
                    maxEntryInLevelZero.right.left = maxEntryInLevelZero.left;
                }
                maxEntryInLevelZero.down.up = null;
                maxEntryInLevelZero = maxEntryInLevelZero.up;
            }
            return (value);
        }
        return (null);  //not found
    }

    public void printHorizontal() {
        String s = "";
        int i;
        SkipListEntry p;

        p = head;

        while (p.down != null) {
            p = p.down;
        }

        i = 0;
        while (p != null) {
            p.pos = i++;
            p = p.right;
        }

        p = head;
        while (p != null) {
            s = getOneRow(p);
            System.out.println(s);

            p = p.down;
        }
    }

    //用了打印测试
    public String getOneRow(SkipListEntry p) {
        String s;
        int a, b, i;

        a = 0;

        s = "" + p.key;
        p = p.right;

        while (p != null) {
            SkipListEntry q;

            q = p;
            while (q.down != null)
                q = q.down;
            b = q.pos;

            s = s + " <-";

            for (i = a + 1; i < b; i++)
                s = s + "--------";

            s = s + "> " + p.key;

            a = b;

            p = p.right;
        }

        return (s);
    }

    //用了打印测试
    public void printVertical() {
        String s = "";
        SkipListEntry p;
        p = head;
        while (p.down != null)
            p = p.down;

        while (p != null) {
            s = getOneColumn(p);
            System.out.println(s);

            p = p.right;
        }
    }
    //用了打印测试
    public String getOneColumn(SkipListEntry p) {
        String s = "";
        while (p != null) {
            s = s + " " + p.key;
            p = p.up;
        }
        return (s);
    }
}