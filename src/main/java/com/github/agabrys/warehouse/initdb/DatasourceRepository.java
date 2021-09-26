package com.github.agabrys.warehouse.initdb;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DatasourceRepository extends CrudRepository<Datasource, Integer> {

  @Query("SELECT d FROM Datasource d WHERE d.name = :name")
  Datasource findByName(@Param("name") String name);
}
