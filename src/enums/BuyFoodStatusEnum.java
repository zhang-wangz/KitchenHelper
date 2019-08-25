package enums;

public enum BuyFoodStatusEnum implements EnumCode{

    XIADAN(0,"�µ�"),
    ZAITU(1,"��;"),
    RUKU(2,"���"),
    TUIHUO(3,"�˻�")

    ;

    private Integer code;
    private String msg;

    BuyFoodStatusEnum(Integer code, String msg) {
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

    @Override
    public String getMsg() {
        return msg;
    }
}
