package com.microservices.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Base64;

@Component
public class utils {

    public static BigDecimal decodeDecimal(String base64, int scale) {
        byte[] bytes = Base64.getDecoder().decode(base64);
        BigInteger bigInt = new BigInteger(bytes);
        return new BigDecimal(bigInt, scale);
    }

}
