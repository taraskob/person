class Organization extends Storable {
    private String name;
    private String business;

    Organization() {
        name = "NAME";
        business = "BUSINESS";
    }

    Organization(String name, String business) {
        this.name = name;
        this.business = business;
    }

    Organization(Organization other) {
        this(other.getName(), other.getBusiness());
    }

   void setName(String name) {
        this.name = name;
    }
    void setBusiness(String business) {
        this.business = business;
    }
    String getName() {
        return name;
    }

    String getBusiness() {
        return business;
    }

    @Override
    public int compareTo(Object o) {
        Organization another = (Organization) o;
        int result = this.name.compareTo(another.name);
        if (result != 0) {
            return result;
        }
        result = this.business.compareTo(another.business);
        if (result != 0) {
            return result;
        }
        return 0;
    }
}
