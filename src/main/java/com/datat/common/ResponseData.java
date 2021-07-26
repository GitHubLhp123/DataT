package com.datat.common;



import lombok.*;

import java.util.Date;

/**
 * @author: mlhp1
 * @date: 2021/7/26
 * @desc:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseData {
    private Object data;
    private Date date;
    private int code;
}
