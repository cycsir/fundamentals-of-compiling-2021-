/**
 * 函数参数/结构体成员
 * */
public class Field {
    public String name;
    public Type type;

    public boolean isEquals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;

        if (type == null){
            return (field.type == null);
        }
        return type.isEquals(field.type);
    }

}


