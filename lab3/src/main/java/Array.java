public class Array extends Type{
    public String name;
    private final Kind kind = Kind.ARRAY;
    public Type baseElementType;
    public int LBNum;
    private int elementNum;

    private Field elementList;

    public Array(Type def_specifier, int parseInt) {
        this.baseElementType = def_specifier;
        this.elementNum = parseInt;
    }

    public Array(){

    }
}
