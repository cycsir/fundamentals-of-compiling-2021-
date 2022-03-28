import java.util.ArrayList;

public class Function extends Type{
    public String name;
    public Type returnType;
    public ArrayList<Field> paramList;
    public final Kind kind = Kind.FUNCTION;

    public boolean isEquals(ArrayList<Type> argsList){
        if (paramList == null && argsList == null){
            return true;
        }
        if (paramList != null && argsList == null  ||  paramList == null && argsList !=null){
            return false;
        }
        if(argsList.size()!=paramList.size()){
            return false;
        }
        for(int i=0;i<argsList.size();i++){
            if(!paramList.get(i).type.equals(argsList.get(i))){
                return false;
            }
        }
        return true;
    }

}
