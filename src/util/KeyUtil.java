package util;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     * @return
     * synchronized 表示多线程锁  避免同一时间多个Id一致。锁住后同一时间只能产生一个
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return  System.currentTimeMillis() + String.valueOf(number);
    }


    public static void main(String[] args) {
        System.out.println(KeyUtil.getUniqueKey());
    }
}
