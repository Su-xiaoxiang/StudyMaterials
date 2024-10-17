package com.class05.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
/**
 * @UserName 程序员_Suxiaoxiang
 * @date 2024/10/13 20:52
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassPojo {
    private int id;
    private String class_name;
    private List<student> student;
}
