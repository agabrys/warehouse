package com.github.agabrys.warehouse.initdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeasurementInserter {

  @Autowired
  private MeasurementRepository measurementRepository;
  @Autowired
  private DatasourceRepository datasourceRepository;
  @Autowired
  private CampaignRepository campaignRepository;

  public void insert(Measurement item) {
    var datasourceName = item.getDatasource().getName();
    var datasource = datasourceRepository.findByName(datasourceName);
    if (datasource == null) {
      datasourceRepository.save(item.getDatasource());
    } else {
      item.setDatasource(datasource);
    }

    var campaignName = item.getCampaign().getName();
    var campaign = campaignRepository.findByName(campaignName);
    if (campaign == null) {
      campaignRepository.save(item.getCampaign());
    } else {
      item.setCampaign(campaign);
    }

    measurementRepository.save(item);
  }
}
