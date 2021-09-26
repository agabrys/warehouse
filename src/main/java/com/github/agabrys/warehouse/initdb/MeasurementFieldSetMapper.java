package com.github.agabrys.warehouse.initdb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

public class MeasurementFieldSetMapper implements FieldSetMapper<Measurement> {

  public static final List<String> NAMES = List.of("Datasource", "Campaign", "Daily", "Clicks", "Impressions");

  @Override
  public Measurement mapFieldSet(FieldSet fieldSet) {
    try {
      return convert(fieldSet.getValues());
    } catch (ParseException e) {
      throw new IllegalArgumentException(e);
    }
  }

  protected Measurement convert(String[] values) throws ParseException {
    var measurement = new Measurement();
    measurement.setDatasource(new Datasource(values[0]));
    measurement.setCampaign(new Campaign(values[1]));
    measurement.setDaily(new SimpleDateFormat("MM/dd/yy").parse(values[2]));
    measurement.setClicks(Integer.valueOf(values[3]));
    measurement.setImpressions(Integer.valueOf(values[4]));
    return measurement;
  }
}
