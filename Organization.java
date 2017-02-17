import java.io.IOException;
import java.lang.reflect.Field;
class Organization implements Comparable {
    private String name;
    private String business;
    Organization(){
    name="NAME";
    business="BUSINESS";}
    void setName(String name) {
        this.name = name;
    }
    String getName() {
        return name;
    }
    void setBusiness(String business) {
        this.business = business;
    }
    String getBusiness() {
        return business;
    }
   @Override
    public int compareTo(Object o) {
        Organization another = (Organization) o;
        Field[] compareFields = o.getClass().getDeclaredFields();
        int result;
        for (Field field : compareFields) {
            result = getName().compareTo(another.getName());
            if (result != 0) {
                return result;
            }
        }
        return 0;
    }
}
