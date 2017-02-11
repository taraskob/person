import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
class Organization {
    private String name;
    private String business;
    String getName() {
        return name;
    }
    String getBusiness() {
        return business;
    }
    void setOrganization (String[] linefields) throws ParseException, IllegalAccessException {
        Class c=this.getClass();
        Field[] setFields = this.getClass().getDeclaredFields();
        int i=0;
        if (linefields.length >= setFields.length){
            for (Field field : setFields){
                     field.set(this, linefields[i]);
                 i++;
            }
        }
    }
    boolean compareOrg(Organization another_org) throws IOException {
        if (!myEquals(this.getName(), another_org.getName())) {
            return false;
        }
        if (!myEquals(this.getBusiness(), another_org.getBusiness())) {
            return false;
        }
        return true;
    }
    private boolean myEquals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }
}
