package com.chirp.community.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class ConvertRequestToMap {
    public static Map<String, String> convertRequestToMap(HttpServletRequest request) {
        Map<String, String> keysAndValues = new HashMap<>();

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            String paramValueString = String.join(", ", paramValues);
            keysAndValues.put(paramName, paramValueString);
        }

        return keysAndValues;
    }
}
