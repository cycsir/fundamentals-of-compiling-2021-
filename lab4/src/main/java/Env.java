public class Env {
    public HashTable table;
    protected Env prev;

    public Env(Env p){
        int hashTableSize = 32;
        table = new HashTable(hashTableSize);
        prev = p;
    }

    public void put(String name, Type type){
        HashNode node = new HashNode(name);
        node.type = type;
        table.appendNode(node);
    }

    public HashNode get(String s){
        for (Env env = this; env != null; env=env.prev){
            HashNode found = (HashNode) (env.table.getNode(s));
            if (found != null){
                return found;
            }
        }
        return null;
    }
}
