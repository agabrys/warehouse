package com.github.agabrys.warehouse.initdb;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

  @Autowired
  public JobBuilderFactory jobBuilderFactory;
  @Autowired
  public StepBuilderFactory stepBuilderFactory;

  @Value("${csv.file}")
  public String csvFile;

  @Bean
  public FlatFileItemReader<Measurement> reader() {
    return new FlatFileItemReaderBuilder<Measurement>()
      .name("measurementItemReader")
      .resource(new ClassPathResource(csvFile))
      .linesToSkip(1)
      .delimited()
      .names(MeasurementFieldSetMapper.NAMES.toArray(new String[0]))
      .fieldSetMapper(new MeasurementFieldSetMapper())
      .build();
  }

  @Bean
  public Job importData(JobCompletionNotificationListener listener, Step insertRecordsStep) {
    return jobBuilderFactory.get("importData")
      .incrementer(new RunIdIncrementer())
      .listener(listener)
      .flow(insertRecordsStep)
      .end()
      .build();
  }

  @Bean
  public Step insertRecordsStep(MeasurementItemWriter writer) {
    ItemWriter<? extends Object> writer2 = writer;
    return stepBuilderFactory.get("insertRecords")
      .chunk(100)
      .reader(reader())
      .writer((ItemWriter<? super Object>) writer2)
      .build();
  }
}
