//package util;
//
//import org.zucc.springbootsample.enums.EnumCode;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class EnumUtils {
//
//    public static <T extends EnumCode> T getByCode (Integer code, Class<T> enumclass){
//        for(T each : enumclass.getEnumConstants()){
//            if(code.equals(each.getCode())){
//                return each;
//            }
//        }
//        return  null;
//    }
//
//    public static <T extends EnumCode> T getByMsg (String  msginfo, Class<T> enumclass){
//        for(T each : enumclass.getEnumConstants()){
//            if(msginfo.equals(each.getMsg())){
//                return each;
//            }
//        }
//        return  null;
//    }
//
//    public static <T extends EnumCode> List<T> EnumsList (Class<T> enumclass){
//        List<T> TEnumList = new ArrayList<>();
//        for(T each:enumclass.getEnumConstants()){
//            TEnumList.add(each);
//        }
//        return  TEnumList;
//    }
//}
