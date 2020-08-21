package com.lxxz.oasis.ouroasis.Util;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nicksun
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResponseJson {

    boolean translate() default false;

    Location location() default Location.UNDEFINED;

    ResponseEncode encode() default ResponseEncode.DEFAULT;

    public enum Location {
        UNDEFINED, DATA, MESSAGE
    }

    public enum ResponseEncode {

        /**
         * 默认，以EncodingJsonHttpMessageConverterWrapper为准
         */
        DEFAULT,
        /**
         * 纯文本传输
         */
        TEXT,

        /**
         * base64编码传输
         */
        BASE64
    }

}
