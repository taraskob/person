import java.io.IOException;
class Organization {
    private String name;
    String getName() {
        return name;
    }
    private String business;
    String getBusiness() {
        return business;
    }
    boolean compareData(Organization another_org) throws IOException {
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
