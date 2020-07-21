package cn.edu.ntu.projectname.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author zack <br>
 * @create 2020-07-21 23:57 <br>
 * @project project-name <br>
 */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class User {

  private Integer age;
  private LocalDateTime date;

  private String name;

  public User(Integer age, LocalDateTime date, String name) {
    this.age = age;
    this.date = date;
    this.name = name;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
