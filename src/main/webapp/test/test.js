require(['helper','/finance-jlcres/static/require/common/constant.js','calculator'],
		function(helper,constant,calculator){
   var helper = new helper();
   console.log(helper);
   console.log(helper.get2pQuestionPage("B005"));
   console.log(constant);
   console.log(constant.BANK_ANNUAL_RATE);
   //银行收益计算器
   var calc = new calculator(constant.BANK_ANNUAL_RATE,constant.BANK_PROFIT_TIME,constant.BANK_PROFIT_TIME);
   console.log(calc.calcProfit(1000));
   //非标
   var bizCode = 'B005';
   //根据表的代码得到一年的天数
   var yearDays = helper.getYearDaysByBorrowBizCode(bizCode);
   var calc = new calculator(0.8,180,yearDays);
   console.log(calc.calcProfit(10000));
   //标的购买页面，获取购买协议文本和链接的代码调用
   var ProtocolInfo = helper.getBuyProtocolByBorrowBizCode(bizCode,173);
   console.log(ProtocolInfo.protocolText +"||"+ProtocolInfo.protocolUrl);
   
});