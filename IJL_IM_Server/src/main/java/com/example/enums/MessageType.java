package com.example.enums;

/**
 * Description:
 *
 * @author zhongjunjie
 * @Date: 2021/10/5
 */
public enum MessageType {

    GREET("greet"),NORMAL("normal")
    ;

    private String type;

    MessageType(String type){
        this.type=type;
    }

    public String value() {
        return type;
    }
}
