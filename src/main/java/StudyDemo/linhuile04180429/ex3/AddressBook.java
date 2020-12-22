package ex3;

public class AddressBook {
    private String name;
    private String address;
    private String tel;


    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getTel() {
        return tel;
    }
    public void getAllInfo(){
        System.out.println("姓名："+getName());
        System.out.println("地址："+getAddress());
        System.out.println("电话："+getTel());
    }
}
