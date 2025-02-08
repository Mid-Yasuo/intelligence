package com.chunjiez.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 张春杰
 * @version 1.0.0
 * @date 2024/12/8
 */
public class RegexUtils {

    static Pattern VARIABLE = Pattern.compile("\\{\\{[a-zA-Z_\\d]+}}");

    public static List<String> getTargetVariables(String input, int limit) {
        List<String> variables = new ArrayList<>();
        Matcher matcher = VARIABLE.matcher(input);
        int count = 0;
        while (count < limit && matcher.find()) {
            variables.add(matcher.group());
            count++;
        }
        return variables;
    }

    public static String getTargetVariable(String input) {
        List<String> variables = getTargetVariables(input, 1);
        if (CollectionUtils.isEmpty(variables)) {
            return "";
        }
        return variables.get(0);
    }

    public static String removeBraces(String target){
        return target.replace("{{","").replace("}}","");
    }


    public static void main(String[] args) {
        List<String> variables = getTargetVariables("{{aaasdas}}1223{{asdkjsa122}}", 2);
        System.out.println("target = " + variables);
    }
}
