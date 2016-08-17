package com.highpay.zoom.spider.site.yirendai.app.model;

import java.io.Serializable;

public class ProductInfoModel implements Serializable
{
  private static final long serialVersionUID = 1L;
  private String canApply;
  private String days;
  private String exitNextOpt;
  private String exitType;
  private String expectedAllIncomeAmount;
  private String expectedIncome;
  private String expectedIncomeAmount;
  private String expectedIncomeAmountNow;
  private String finOrderNo;
  private String finOrderStatus;
  private String frozenDate;
  private String frozenTime;
  private String incomeAmount;
  private String investAmount;
  private String investDate;
  private boolean isReminder;
  private String nowDate;
  private String p2pserviceName;
  private String p2pserviceNo;
  private String productId;
  private String productSubId;
  private String productType;
  private String startCalcDate;
  
  public static long getSerialversionuid()
  {
    return 1L;
  }
  
  public String getCanApply()
  {
    return this.canApply;
  }
  
  public String getDays()
  {
    return this.days;
  }
  
  public String getExitNextOpt()
  {
    return this.exitNextOpt;
  }
  
  public String getExitType()
  {
    return this.exitType;
  }
  
  public String getExpectedAllIncomeAmount()
  {
    return this.expectedAllIncomeAmount;
  }
  
  public String getExpectedIncome()
  {
    return this.expectedIncome;
  }
  
  public String getExpectedIncomeAmount()
  {
    return this.expectedIncomeAmount;
  }
  
  public String getExpectedIncomeAmountNow()
  {
    return this.expectedIncomeAmountNow;
  }
  
  public String getFinOrderNo()
  {
    return this.finOrderNo;
  }
  
  public String getFinOrderStatus()
  {
    return this.finOrderStatus;
  }
  
  public String getFrozenDate()
  {
    return this.frozenDate;
  }
  
  public String getFrozenTime()
  {
    return this.frozenTime;
  }
  
  public String getIncomeAmount()
  {
    return this.incomeAmount;
  }
  
  public String getInvestAmount()
  {
    return this.investAmount;
  }
  
  public String getInvestDate()
  {
    return this.investDate;
  }
  
  public String getNowDate()
  {
    return this.nowDate;
  }
  
  public String getP2pserviceName()
  {
    return this.p2pserviceName;
  }
  
  public String getP2pserviceNo()
  {
    return this.p2pserviceNo;
  }
  
  public String getProductId()
  {
    return this.productId;
  }
  
  public String getProductSubId()
  {
    return this.productSubId;
  }
  
  public String getProductType()
  {
    return this.productType;
  }
  
  public String getStartCalcDate()
  {
    return this.startCalcDate;
  }
  
  public boolean isReminder()
  {
    return this.isReminder;
  }
  
  public void setCanApply(String paramString)
  {
    this.canApply = paramString;
  }
  
  public void setDays(String paramString)
  {
    this.days = paramString;
  }
  
  public void setExitNextOpt(String paramString)
  {
    this.exitNextOpt = paramString;
  }
  
  public void setExitType(String paramString)
  {
    this.exitType = paramString;
  }
  
  public void setExpectedAllIncomeAmount(String paramString)
  {
    this.expectedAllIncomeAmount = paramString;
  }
  
  public void setExpectedIncome(String paramString)
  {
    this.expectedIncome = paramString;
  }
  
  public void setExpectedIncomeAmount(String paramString)
  {
    this.expectedIncomeAmount = paramString;
  }
  
  public void setExpectedIncomeAmountNow(String paramString)
  {
    this.expectedIncomeAmountNow = paramString;
  }
  
  public void setFinOrderNo(String paramString)
  {
    this.finOrderNo = paramString;
  }
  
  public void setFinOrderStatus(String paramString)
  {
    this.finOrderStatus = paramString;
  }
  
  public void setFrozenDate(String paramString)
  {
    this.frozenDate = paramString;
  }
  
  public void setFrozenTime(String paramString)
  {
    this.frozenTime = paramString;
  }
  
  public void setIncomeAmount(String paramString)
  {
    this.incomeAmount = paramString;
  }
  
  public void setInvestAmount(String paramString)
  {
    this.investAmount = paramString;
  }
  
  public void setInvestDate(String paramString)
  {
    this.investDate = paramString;
  }
  
  public void setNowDate(String paramString)
  {
    this.nowDate = paramString;
  }
  
  public void setP2pserviceName(String paramString)
  {
    this.p2pserviceName = paramString;
  }
  
  public void setP2pserviceNo(String paramString)
  {
    this.p2pserviceNo = paramString;
  }
  
  public void setProductId(String paramString)
  {
    this.productId = paramString;
  }
  
  public void setProductSubId(String paramString)
  {
    this.productSubId = paramString;
  }
  
  public void setProductType(String paramString)
  {
    this.productType = paramString;
  }
  
  public void setReminder(boolean paramBoolean)
  {
    this.isReminder = paramBoolean;
  }
  
  public void setStartCalcDate(String paramString)
  {
    this.startCalcDate = paramString;
  }
  
  public String toString()
  {
    return "ProductInfo [productId=" + this.productId + ", productSubId=" + this.productSubId + ", p2pserviceName=" + this.p2pserviceName + ", p2pserviceNo=" + this.p2pserviceNo + ", expectedIncome=" + this.expectedIncome + ", frozenDate=" + this.frozenDate + ", investDate=" + this.investDate + ", investAmount=" + this.investAmount + ", startCalcDate=" + this.startCalcDate + ", incomeAmount=" + this.incomeAmount + ", expectedIncomeAmount=" + this.expectedIncomeAmount + ", expectedAllIncomeAmount=" + this.expectedAllIncomeAmount + ", finOrderNo=" + this.finOrderNo + ", finOrderStatus=" + this.finOrderStatus + ", frozenTime=" + this.frozenTime + ", exitType=" + this.exitType + ", days=" + this.days + ", exitNextOpt=" + this.exitNextOpt + ", canApply=" + this.canApply + ", nowDate=" + this.nowDate + ", isReminder=" + this.isReminder + "]";
  }
}
