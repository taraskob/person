class Organization extends Storable {
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
    synchronized public int compareTo(Object o) {
        Organization another = (Organization) o;
       int result=this.name.compareTo(another.name);
       if (result != 0) {
           return result;
       }
       result=this.business.compareTo(another.business);
       if (result != 0) {
           return result;
       }
        return 0;
    }
}
