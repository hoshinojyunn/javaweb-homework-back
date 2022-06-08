package com.hoshino.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * Description: 实体类 对应数据库group表
 */
public class Group {
    /**
     * groupId: 对应表的group_id
     * groupName: 对应表的group_name
     */
    private int groupId;
    private String groupName;
}
