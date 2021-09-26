package com.github.agabrys.warehouse.initdb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Datasource {

  @Id
  @GeneratedValue
  @Column(unique = true, nullable = false)
  private Integer id;

  @Column(length = 40, unique = true, nullable = false)
  private String name;

  public Datasource() {
    // do nothing
  }

  public Datasource(String name) {
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
