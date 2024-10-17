package com.class05.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/10/13 20:51
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class student {
    private int id;
    private String name;
    private int age;
    private int class_id;
    private ClassPojo classPojo;
}
