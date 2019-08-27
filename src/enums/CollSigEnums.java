package enums;

public enum  CollSigEnums implements EnumCode{

    WEISHOUCANG(0,"Œ¥ ’≤ÿ"),
    YISHOUCANG(1,"“— ’≤ÿ"),

    ;
    private Integer code;
    private String msg;

    @Override
    public String getMsg() {
        return msg;
    }

    CollSigEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return getMsg();
    }

    @Override
    public Integer getCode() {
        return code;
    }

}
