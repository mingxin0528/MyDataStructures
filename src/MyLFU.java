import java.util.HashMap;
import java.util.LinkedHashSet;

public class MyLFU {
    HashMap<Integer, Integer> keyToVal;
    HashMap<Integer, Integer> keyToFreq;
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    int cap;
    int minFreq;

    public MyLFU(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        this.cap = capacity;
        this.minFreq = 0;
    }

    public void put(int key, int val) {
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, val);
            increaseFreq(key);
            return;
        }

        if (keyToVal.size() == cap) {
            removeMinFreqKey();
        }

        keyToVal.put(key, val);
        keyToFreq.put(key, 1);
        if (!freqToKeys.containsKey(1)) {
            freqToKeys.put(1, new LinkedHashSet<>());
        }
        freqToKeys.get(1).add(key);
        minFreq = 1;
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key)) {
            return -1;
        }
        increaseFreq(key);
        return keyToVal.get(key);
    }

    private void removeMinFreqKey() {
        LinkedHashSet<Integer> keyList = freqToKeys.get(minFreq);
        int deleteKey = keyList.iterator().next();
        keyList.remove(deleteKey);

        if (keyList.isEmpty()) {
            freqToKeys.remove(minFreq);
        }

        keyToVal.remove(deleteKey);
        keyToFreq.remove(deleteKey);
    }

    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        LinkedHashSet<Integer> keyList = freqToKeys.get(freq);
        keyList.remove(key);

        if (keyList.isEmpty()) {
            freqToKeys.remove(minFreq);
            if(freq == minFreq){
                minFreq++;
            }
        }

        freq++;

        if (!freqToKeys.containsKey(freq)) {
            freqToKeys.put(freq,new LinkedHashSet<>());
        }
        freqToKeys.get(freq).add(key);
        keyToFreq.put(key, keyToFreq.get(key) + 1);
    }

    public static void main(String[] args) {
        MyLFU lfu = new MyLFU(3);
        lfu.put(1,10);
        lfu.put(2,20);
        lfu.put(3,30);
        lfu.get(1);
        lfu.get(2);
        lfu.put(4,40);
        System.out.println(lfu.freqToKeys.get(1));
    }
}


