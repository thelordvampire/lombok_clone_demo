/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bao.getter.util;

/**
 *
 * @author bao
 */
public class StringUtils {
    
    public static String generateMethodName(String prefix, String fieldName) {
        String cap = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        String getMethodNameStr = prefix + cap;
        return getMethodNameStr;
    }
    
}
