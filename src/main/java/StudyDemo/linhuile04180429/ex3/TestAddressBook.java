package ex3;

public class TestAddressBook {
    public static void main(String[] args){
        AddressBook zhangsan_addr = new AddressBook();
        String name = "zhangsan";
        String address = "jluzh";
        String tel = "041804";
        zhangsan_addr.setName(name);
        zhangsan_addr.setAddress(address);
        zhangsan_addr.setTel(tel);
        zhangsan_addr.getAllInfo();
        zhangsan_addr.setTel("123456");
        tel = zhangsan_addr.getTel();
        System.out.println("修改后的电话："+tel);
    }
}
