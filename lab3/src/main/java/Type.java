public class Type {
    public Kind kind;
    public boolean isRightValue = false;
    public String name;
   //  public String name;

   public boolean isEquals(Object object) {
       if (this == object) return true;
       if (object == null || getClass() != object.getClass()) return false;
       Type type = (Type) object;
       return type.kind==kind;


   }
}
