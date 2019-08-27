package enums;

public enum BrowSigEnums implements EnumCode{
    YILIULAN(0,"�����"),
    WEILIULAN(1,"δ���"),

    ;

    private Integer code;
    private String msg;

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return getMsg();
    }

    @Override
    public Integer getCode() {
        return code;
    }



    BrowSigEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
