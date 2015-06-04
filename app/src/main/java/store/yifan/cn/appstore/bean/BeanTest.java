package store.yifan.cn.appstore.bean;

import java.util.List;

/**
 * Author: Jwf(feijia101@gmail.com) <br\>
 * Date: 2015-05-29 16:43<br\>
 * Version: 1.0<br\>
 * Desc:<br\>
 * Revise:<br\>
 */
public class BeanTest {

    /**
     * height : 140cm
     * age : 15
     * name : 王五
     * hobby : [{"name":"billiards","code":"1"},{"name":"computerGame","code":"2"}]
     * gender : man
     * addr : {"province":"fujian","code":"300000","city":"quanzhou"}
     */
    private String height;
    private int age;
    private String name;
    private List<HobbyEntity> hobby;
    private String gender;
    private AddrEntity addr;

    public void setHeight(String height) {
        this.height = height;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHobby(List<HobbyEntity> hobby) {
        this.hobby = hobby;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddr(AddrEntity addr) {
        this.addr = addr;
    }

    public String getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public List<HobbyEntity> getHobby() {
        return hobby;
    }

    public String getGender() {
        return gender;
    }

    public AddrEntity getAddr() {
        return addr;
    }

    public class HobbyEntity {
        /**
         * name : billiards
         * code : 1
         */
        private String name;
        private String code;

        public void setName(String name) {
            this.name = name;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public String getCode() {
            return code;
        }
    }

    public class AddrEntity {
        /**
         * province : fujian
         * code : 300000
         * city : quanzhou
         */
        private String province;
        private String code;
        private String city;

        public void setProvince(String province) {
            this.province = province;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getProvince() {
            return province;
        }

        public String getCode() {
            return code;
        }

        public String getCity() {
            return city;
        }
    }
}
