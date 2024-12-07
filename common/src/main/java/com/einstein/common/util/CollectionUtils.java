package com.einstein.common.util;

import java.util.Collection;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/9/6
 */
public class CollectionUtils {

    public static String join(Collection<String> list, String symbol) {
        StringBuilder builder = new StringBuilder();
        int index = 0;
        for (String s : list) {
            builder.append("'").append(s).append("'");
            index++;
            if (index < list.size()) {
                builder.append(symbol);
            }
        }
        return builder.toString();
    }

    public static boolean isEmpty(Collection<?> list) {
        return list == null || list.isEmpty();
    }
}
