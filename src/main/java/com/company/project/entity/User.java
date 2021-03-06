package com.company.project.entity;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

  private static final long serialVersionUID = -2470913076446177390L;
  private Integer uId;
  private String name;
  private Integer age;
  // JSON.toJSONString(user)的时候默认是用 字段名 --> name --> alternateNames
  //  @JSONField(name = "testFieldName",alternateNames = "testFieldAlternateName")
  private String testField;
  /**
   *  注意这里不要使用 isStudent 这样的boolean字段。避免序列化/反序列化找不到该字段
   *  因为 isStudent 和 student 的 get set方法都是一样的
   */
  private boolean studentIs;

  @JSONField(format = "yyyy-MM-dd HH:mm:ss") //JSON.toJSONString(user)的时候按照指定格式，格式化时间;
  private Date birthday;

}
