package com.highpay.zoom.spider.site.yirendai.app.model;

import java.util.List;

public class AccountInfoModel
{
  private String accountBalance;
  private String accumulatedIncome;
  private String investedAmountForExit;
  private String investingAmount;
  private String level;
  private String name;
  private List<ProductInfoModel> p2pserviceList;
  
  public String getAccountBalance()
  {
    return this.accountBalance;
  }
  
  public String getAccumulatedIncome()
  {
    return this.accumulatedIncome;
  }
  
  public String getInvestedAmountForExit()
  {
    return this.investedAmountForExit;
  }
  
  public String getInvestingAmount()
  {
    return this.investingAmount;
  }
  
  public String getLevel()
  {
    return this.level;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public List<ProductInfoModel> getP2pserviceList()
  {
    return this.p2pserviceList;
  }
  
  public void setAccountBalance(String paramString)
  {
    this.accountBalance = paramString;
  }
  
  public void setAccumulatedIncome(String paramString)
  {
    this.accumulatedIncome = paramString;
  }
  
  public void setInvestedAmountForExit(String paramString)
  {
    this.investedAmountForExit = paramString;
  }
  
  public void setInvestingAmount(String paramString)
  {
    this.investingAmount = paramString;
  }
  
  public void setLevel(String paramString)
  {
    this.level = paramString;
  }
  
  public void setName(String paramString)
  {
    this.name = paramString;
  }
  
  public void setP2pserviceList(List<ProductInfoModel> paramList)
  {
    this.p2pserviceList = paramList;
  }
}
