define(function(){
	/**
	 * @annualRate 年利率
	 * @time 产品持有的时间，投资的期限
	 * @yearDays 一年的天数，银行计算以365为基准，p2p是根据标的类型来的
	 */
	function Calculator(annualRate,time,yearDays){
		this.annualRate = annualRate;
		this.time = time;
		this.yearDays = yearDays;
	}
	Calculator.prototype = {
		constructor:Calculator,
		calcProfit:function(amount){
			//TODO 校验输入金额
			var result = (amount * this.annualRate)* this.time / this.yearDays;
			return result.toFixed(2);
		}
	}
	return Calculator;
});