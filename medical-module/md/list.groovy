/**
 * Created by Administrator on 2018/3/13.
 */
def execute(def req,def resp){
    def rs=[:];
    List info = new ArrayList();
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    info.add(new MdInfoDO("lyb",18,"18762036036"))
    rs['info'] = info
    return rs;
}

class MdInfoDO implements Serializable{
    private String name;
    private Integer age;
    private String mobile;

    MdInfoDO(String name, Integer age, String mobile) {
        this.name = name
        this.age = age
        this.mobile = mobile
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    Integer getAge() {
        return age
    }

    void setAge(Integer age) {
        this.age = age
    }

    String getMobile() {
        return mobile
    }

    void setMobile(String mobile) {
        this.mobile = mobile
    }

    @Override
    public String toString() {
        return "mdInfoDO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}