package enums;



public enum FoodOrderStatusEnum implements EnumCode {

    XIADAN(0,"下单"),
    PEISONG(1,"配送"),
    SONGDA(2,"送达"),
    TUIHUO(3,"退货"),

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
