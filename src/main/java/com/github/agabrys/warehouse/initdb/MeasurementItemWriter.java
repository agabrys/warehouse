package com.github.agabrys.warehouse.initdb;

import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeasurementItemWriter implements ItemWriter<Measurement> {

  private static final Logger LOGGER = LoggerFactory.getLogger(MeasurementItemWriter.class);

  @Autowired
  private MeasurementInserter inserter;

  @Override
  public void write(List<? extends Measurement> items) {
    LOGGER.debug("Saving {} items...", items.size());
    for (Measurement item : items) {
      inserter.insert(item);
    }
  }
}
