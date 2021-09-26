package com.github.agabrys.warehouse.initdb;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Measurement {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "datasource_id", referencedColumnName = "name")
  private Datasource datasource;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "campaign_id", referencedColumnName = "name")
  private Campaign campaign;
  @Column(nullable = false)
  private Date daily;
  @Column(nullable = false)
  private Integer clicks;
  @Column(nullable = false)
  private Integer impressions;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Datasource getDatasource() {
    return datasource;
  }

  public void setDatasource(Datasource datasource) {
    this.datasource = datasource;
  }

  public Campaign getCampaign() {
    return campaign;
  }

  public void setCampaign(Campaign campaign) {
    this.campaign = campaign;
  }

  public Date getDaily() {
    return daily;
  }

  public void setDaily(Date daily) {
    this.daily = daily;
  }

  public Integer getClicks() {
    return clicks;
  }

  public void setClicks(Integer clicks) {
    this.clicks = clicks;
  }

  public Integer getImpressions() {
    return impressions;
  }

  public void setImpressions(Integer impressions) {
    this.impressions = impressions;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Measurement{");
    sb.append("id=").append(id);
    sb.append(", datasource=").append(datasource.getName());
    sb.append(", campaign=").append(campaign.getName());
    sb.append(", daily=").append(daily);
    sb.append(", clicks=").append(clicks);
    sb.append(", impressions=").append(impressions);
    sb.append('}');
    return sb.toString();
  }
}
