import java.util.ArrayList;

public class Structure extends Type{
    public String name;
    public ArrayList<Field> memberList;
    public Kind kind = Kind.STRUCTURE;

    public Field getField(String fieldName){
        for(Field f:memberList){
            if(f.name!=null&&f.name.equals(fieldName)){
                return f;
            }
        }
        return null;
    }
    public boolean isEquals(Structure structure){
        if (memberList == null && structure.memberList == null){

            return true;
        }
        if (memberList != null && structure.memberList == null
                || memberList == null && structure.memberList != null){

            return false;
        }
        if (memberList.size() != structure.memberList.size()){
//            System.err.println(""+memberList.size()+" "+structure.memberList.size());
            return false;
        }
        for (int i = 0; i < memberList.size(); i++){
            Field field1 = memberList.get(i);
            Field field2 = structure.memberList.get(i);
//            System.err.println(""+field1.type.kind+field2.type.kind);

            if (field1 == null && field2 == null){
                return true;
            }
            if (field1 != null && field2 == null || field1 == null && field2 != null){
                return false;
            }
            // field1 不为空
            if (!field1.isEquals(field2)){
                return false;
            }
        }
        return true;
    }


    public Structure(){
        this.kind = Kind.STRUCTURE;
    }
}
