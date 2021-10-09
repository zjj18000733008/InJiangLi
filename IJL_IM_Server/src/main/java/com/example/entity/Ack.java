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
public class Ack {

    private Long msgId;



}
