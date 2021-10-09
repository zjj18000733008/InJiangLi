package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author zhongjunjie
 * @Date: 2021/10/5
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private Long id;

    private Long fromId;

    private Long toId;

    private String content;

    /**
     * [ greet, normal ]
     */
    private String type;

}
