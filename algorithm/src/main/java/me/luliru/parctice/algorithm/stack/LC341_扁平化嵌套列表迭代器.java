package me.luliru.parctice.algorithm.stack;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * LC341_扁平化嵌套列表迭代器
 * Created by luliru on 2021/3/23.
 */
public class LC341_扁平化嵌套列表迭代器 {
}

class NestedInteger {
    public boolean isInteger() {
        return false;
    }

    public Integer getInteger() {
        return null;
    }

    public List<NestedInteger> getList() {
        return null;
    }
}

class NestedIterator implements Iterator<Integer> {

    // 是否已经查找到下一个有效元素？有3种情况为false
    // 1、已经使用过了
    // 2、没查找过
    // 3、已经找不到了
    private boolean finded;
    private LinkedList<PointAndList> pointStack;

    public NestedIterator(List<NestedInteger> nestedList) {
        pointStack = new LinkedList<>();
        pointStack.push(new PointAndList(-1, nestedList));
    }

    @Override
    public Integer next() {
        // 如果没有查找过，则开始查找
        if (!finded) {
            hasNext();
        }
        // 查找之后没有找到，则返回null
        if (!finded) {
            return null;
        }

        finded = false;
        PointAndList ele = pointStack.peek();
        return ele.list.get(ele.point).getInteger();
    }

    @Override
    public boolean hasNext() {
        if (finded) {
            return true;
        }

        // 通过hasNext找到下一个有效元素，next()直接返回该元素
        while (!pointStack.isEmpty()) {
            PointAndList ele = pointStack.peek();
            ++ele.point;

            int point = ele.point;
            List<NestedInteger> list = ele.list;

            // 当前层级已经遍历完了
            if (point == list.size()) {
                pointStack.pop();
                // 继续下一轮
                continue;
            }

            // 当前层级没有遍历完
            NestedInteger nested = list.get(point);
            if (nested.isInteger()) {
                finded = true;
                return true;
            } else {
                // 特殊处理空List
                if (nested.getList().isEmpty()) {
                    continue;
                }
                pointStack.push(new PointAndList(-1, nested.getList()));
            }
        }
        return false;
    }

    static class PointAndList {
        Integer point;
        List<NestedInteger> list;

        public PointAndList(Integer point, List<NestedInteger> list) {
            this.point = point;
            this.list = list;
        }
    }
}
