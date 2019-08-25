package enums;

public enum BuyFoodStatusEnum implements EnumCode{

    XIADAN(0,"下单"),
    ZAITU(1,"在途"),
    RUKU(2,"入库"),
    TUIHUO(3,"退货")

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
