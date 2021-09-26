package com.github.agabrys.warehouse.initdb;

import java.sql.ResultSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

  private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      LOGGER.info("Import CSV data to database has been completed");
      LOGGER.info("Inserted:");
      var records = jdbcTemplate.queryForObject("SELECT count(*) FROM measurement", Long.class);
      LOGGER.info("  - records: {}", records);
      var datasource = jdbcTemplate.queryForObject("SELECT count(*) FROM datasource", Integer.class);
      LOGGER.info("  - unique datasource: {}", datasource);
      var campaign = jdbcTemplate.queryForObject("SELECT count(*) FROM campaign", Integer.class);
      LOGGER.info("  - unique campaign: {}", campaign);
    }
  }
}
