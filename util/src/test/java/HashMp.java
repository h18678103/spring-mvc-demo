import org.apache.commons.lang3.StringUtils;

/**
 * Author:yepei@meituan.com
 * Date:2017/7/5
 * Time:15:45
 * ------------------------------------
 * Desc:
 */
public class HashMp {
    private transient Node[] entries;//数组
    private transient float loadFactor;
    private transient int size;

    public static void main(String[] args) {
        HashMp map = new HashMp(2, 0.75f);
        map.put(3, "A");
        System.out.println(map);
        map.put(5, "B");
        map.put(7, "C");
        map.put(11, "D");
        map.put(9, "E");
        map.put(12, "F");
        map.put(17, "G");
        map.put(22, "H");
        map.put(24, "I");
        map.put(25, "I");
        System.out.println(map);

        String s1 = map.get(22);
        String s2 = map.get(25);
        String s3 = map.get(0);
    }

    public HashMp(int capacity, float loadFactor) {
        this.entries = new Node[capacity];
        this.loadFactor = loadFactor;
    }

    public HashMp() {
        this(16, 0.75f);
    }

    public String put(int key, String value) {
        Node n = new Node(key, value);
        //算Hash值,为简单起见，hash值
        int i = n.getIndex(entries.length);
        Integer thisKey = key;
        //查找是否有重复: hashCode相等，且equals方法相等的
        for (Node old = entries[i]; old != null; old = old.next) {
            Integer oldKey;
            //hash相同，且equals返回true，证明有重复值插入，直接替换掉旧值
            if (old.getHash() == n.getHash() && ((oldKey = old.key) == key || thisKey.equals(oldKey))) {
                String oldValue = old.value;
                old.value = value;//替换成新值
                return oldValue;//返回旧值
            }
        }
        //该key不存在，需要增加一个结点
        addEntry(n);
        return null;
    }

    public String get(int key) {
        int idx = new Node(key, null).getIndex(entries.length);
        Integer thisKey = key;
        //查找是否有重复: hashCode相等，且equals方法相等的
        for (Node old = entries[idx]; old != null; old = old.next) {
            Integer oldKey = old.key;
            if (thisKey == oldKey || thisKey.equals(oldKey)) {
                return old.value;
            }
        }
        return null;
    }

    private void addEntry(Node n) {
        int oldLen = entries.length;
        int targetIdx = n.getIndex(oldLen);
        n.next = entries[targetIdx];
        entries[targetIdx] = n;//插入到链首
        if (size++ >= oldLen * loadFactor) {
            resize(2 * oldLen);
        }
    }

    private void resize(int newCapacity) {
        Node[] old = entries;
        Node[] newMap = new Node[newCapacity];
        for (int j = 0; j < old.length; j++) {
            Node e = old[j];
            if (e == null) {
                continue;
            }
            old[j] = null;//老表中的元素置为Null

            //注意：该过程会将同一个bucket中的元素逆置，因为总是将元素插在了新表的头部。
            //但不是就地逆置，因为将元素逐个copy到了新的链表中
            do {
                Node next = e.next;//暂存尾链表(去掉head的链表)
                int i = e.getIndex(newCapacity);//得到将在新表中存储的位置
                e.next = newMap[i];//切断指针，重新指向新表的表头(可能为null)
                newMap[i] = e;//将e插入到新链表头部
                e = next;//重新将e赋值为尾链表的头，对尾链表继续“切断指针，插入新表头”
            } while (e != null);
        }
        entries = newMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        for (Node n : entries) {
            sb.append(i++).append("{").append(n).append("}, ");
        }
        return StringUtils.removeEnd(sb.toString(), ", ") + "]";
    }

    static class Node {
        int key;
        String value;
        Node next;

        Node(int key, String value) {
            this.key = key;
            this.value = value;
        }

        int getIndex(int tableLength) {
            return getHash() % tableLength;
        }

        //为方便起见，hash值直接取该对象的key
        int getHash() {
            return key;
        }

        @Override
        public String toString() {
            return "(" + key + "," + value + ")——>" + next;
        }
    }
}
