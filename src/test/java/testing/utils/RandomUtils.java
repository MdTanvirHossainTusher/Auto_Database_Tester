package testing.utils;

import framework.utils.JsonDataUtils;

import java.util.Random;

public class RandomUtils {

    private static Random random = new Random();

    public static int randomSubscriptionPlan(){
        return random.nextInt(JsonDataUtils.getIntValueByKey("totalSubscriptionPlan"));
    }

}
