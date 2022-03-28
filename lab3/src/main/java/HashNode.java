import java.util.Objects;

public class HashNode {
    String name = null;
    Type type;
    HashNode next;
    boolean hasNext;

    public HashNode(String name){
        this.name = name;
        this.hasNext = false;
    }

    public void addNext(HashNode hashNode){
        if (!this.hasNext){
            this.next = hashNode;
            this.hasNext = true;
        }
        else {
            next.addNext(hashNode);
        }
    }

    public HashNode getNext(String name){
        if (!this.hasNext) {
            if (Objects.equals(next.name, name)){
                return next;
            }
            else {
                return next.getNext(name);
            }
        }
        else return null;
    }
}
