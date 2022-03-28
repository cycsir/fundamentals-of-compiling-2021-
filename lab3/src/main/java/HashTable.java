import java.util.ArrayList;

public class HashTable {
    int Hash_Table_SIZE;
    ArrayList<HashNode> list;

    private int getHashIndex(String name){
        int val = 0, i;
        for (char c : name.toCharArray()){
            val = (val << 2)+(int) c;
            if ((i = (val & ~Hash_Table_SIZE)) != 0){
                val = (val ^ (i >> 12)) & Hash_Table_SIZE;
            }
        }
        return val;
    }

    public int appendNode(HashNode hashNode){
        int index = -1;
        index = getHashIndex(hashNode.name);
        HashNode firstNode = list.get(index);
        if (firstNode.name == null){
            list.set(index, hashNode);
        }else {
            firstNode.addNext(hashNode);
        }
        return index;
    }

    public HashNode getNode(String name){
        int index = getHashIndex(name);
        HashNode firstNode = list.get(index);
        if (firstNode.name == null){
            return null;
        }else if (firstNode.name.equals(name)){
            return firstNode;
        }else {
            return firstNode.getNext(name);
        }
    }
    public HashTable(int hash_Table_SIZE){
        this.Hash_Table_SIZE = hash_Table_SIZE;
        list = new ArrayList<>(Hash_Table_SIZE);
    }
}
