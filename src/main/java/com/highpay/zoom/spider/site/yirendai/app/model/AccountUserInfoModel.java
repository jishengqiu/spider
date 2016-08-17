package com.highpay.zoom.spider.site.yirendai.app.model;

public class AccountUserInfoModel
{
	private String accountBalance;
	private String accountStatus;
	private String accumulatedIncome;
	private String email;
	private String investedAmountForExit;
	private String investingAmount;
	private String level;
	private String mobileNo;
	private String name;
	private String token;
	private String accountType;	
	private String passportId;

	public String getPassportId() {
		return passportId;
	}

	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountBalance() {
		return accountBalance;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public String getAccumulatedIncome() {
		return accumulatedIncome;
	}

	public String getEmail() {
		return email;
	}

	public String getInvestedAmountForExit() {
		return investedAmountForExit;
	}

	public String getInvestingAmount() {
		return investingAmount;
	}

	public String getLevel() {
		return level;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public String getName() {
		return name;
	}

	public String getToken() {
		return token;
	}

	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public void setAccumulatedIncome(String accumulatedIncome) {
		this.accumulatedIncome = accumulatedIncome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setInvestedAmountForExit(String investedAmountForExit) {
		this.investedAmountForExit = investedAmountForExit;
	}

	public void setInvestingAmount(String investingAmount) {
		this.investingAmount = investingAmount;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "AccountUserInfoModel [accountBalance=" + accountBalance
				+ ", accountStatus=" + accountStatus + ", accumulatedIncome="
				+ accumulatedIncome + ", email=" + email
				+ ", investedAmountForExit=" + investedAmountForExit
				+ ", investingAmount=" + investingAmount + ", level=" + level
				+ ", mobileNo=" + mobileNo + ", name=" + name + ", token="
				+ token + "]";
	}
}
