package com.github.agabrys.warehouse.initdb;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignRepository extends CrudRepository<Campaign, Integer> {

  @Query("SELECT c FROM Campaign c WHERE c.name = :name")
  Campaign findByName(@Param("name") String name);
}
