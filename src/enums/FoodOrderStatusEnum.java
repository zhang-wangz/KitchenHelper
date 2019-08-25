package enums;



public enum FoodOrderStatusEnum implements EnumCode {

    XIADAN(0,"�µ�"),
    PEISONG(1,"����"),
    SONGDA(2,"�ʹ�"),
    TUIHUO(3,"�˻�"),

            ;

    private Integer code;
    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }



    FoodOrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

}
