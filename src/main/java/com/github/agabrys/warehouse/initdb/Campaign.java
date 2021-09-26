package com.github.agabrys.warehouse.initdb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Campaign {

  @Id
  @GeneratedValue
  @Column(unique = true, nullable = false)
  private Integer id;

  @Column(length = 60, unique = true, nullable = false)
  private String name;

  public Campaign() {
    // do nothing
  }

  public Campaign(String name) {
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
