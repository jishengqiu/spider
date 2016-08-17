define(function() { 
	
	function Helper(){
		
	};
	Helper.prototype = {
		constructor:Helper,
		/**
		 * @title 根据标的类型来的得到常见问题的跳转地址
		 * 
		 * @param borrowBizCode
		 * @returns {String}
		 */
		get2pQuestionPage :function(borrowBizCode) {
			// 默认盈多宝
			var htmlPath = "/finance-jlcres/more/qa/p2p/qa_ydb.html";

			if (borrowBizCode == 'B005') {
				htmlPath = "/finance-jlcres/more/qa/p2p/qa_dr.html";
			}
			// 盈富宝
			else if (borrowBizCode == 'B004') {
				htmlPath = "/finance-jlcres/more/qa/p2p/qa_yfub.html";
			}
			// 盈房宝
			else if (borrowBizCode == 'B003') {
				htmlPath = "/finance-jlcres/more/qa/p2p/qa_yfangb.html";
			}
			// 盈车宝
			else if (borrowBizCode == 'B002') {
				htmlPath = "/finance-jlcres/more/qa/p2p/qa_ycheb.html";
			}
			// 盈钱宝
			else if (borrowBizCode == 'B006') {
				htmlPath = "/finance-jlcres/more/qa/p2p/qa_yqb.html";
			}
			//盈新宝
			else if (borrowBizCode == 'B007') {
				htmlPath = "/finance-jlcres/more/qa/p2p/qa_yxinb.html";
			}
			//盈嘉宝
			else if (borrowBizCode == 'B008') {
				htmlPath = "/finance-jlcres/more/qa/p2p/qa_yjiab.html";
			}
			return htmlPath;

		},
		/**
		 * @title 根据标的业务类型来判断一年的总天数
		 * 
		 * @param borrowBizCode 标的业务类型
		 * @returns {Number}
		 */
		getYearDaysByBorrowBizCode : function(borrowBizCode){
			//默认是365天
			var days = 365;
			//点融
			if (borrowBizCode == 'B005') {
				days = 365.25;
				//盈钱宝
			}else if(borrowBizCode == 'B006'){
				days = 360;
			}
			return days;
		},
		/**
		 * 申购页面获取，购买协议文本和url
		 * 
		 * @param borrowBizCode  标的业务Id
		 * @param borrowId       标的Id
		 */
		getBuyProtocolByBorrowBizCode : function(borrowBizCode,borrowId){
			var protocolText = "《债权转让协议》";
			var protocolUrl = "";
			//盈钱宝
			if (borrowBizCode === "B006") {
				protocolText = "《委托合同》《用户服务条款》";
				protocolUrl = "/finance-jlcres/p2p/ydb/buy_agreement_yqianb.html";
				//点融
			} else if (borrowBizCode === "B005") {
				protocolText = "《团团赚协议》《客户确认书》《用户服务条款》";
				protocolUrl =  "/finance-jlcres/p2p/ydb/buy_agreement_dr.html" ;
			} else if (borrowBizCode === "B004") {
				//盈富宝
				protocolText = "《融资文件范本》《授权声明书》《投资咨询及管理服务协议》";
				protocolUrl =  "/finance-jlcres/p2p/ydb/buy_agreement_yfub.html";
			} else if (borrowBizCode === "B003") {
				//盈房宝
				protocolText = "《借款合同》";
				protocolUrl = "/finance-jlcres/p2p/ydb/buy_agreement_yfangb.html";
			} else if (borrowBizCode === "B002") {
				//盈车宝
				protocolUrl = "/finance-jlcres/p2p/ydb/buy_agreement_ycheb.html";
			} else if (borrowBizCode === "B008") {
				//盈嘉宝
				protocolText = "《客户确认书》《用户服务条款》";
				protocolUrl =  "/finance-jlcres/p2p/ydb/agreement/buy_agreement_yjiab.html";
			} else {
				protocolUrl = "/finance-jlcres/p2p/ydb/buy_agreement.html";
			}
			if(protocolUrl.length !==0){
				protocolUrl = protocolUrl+"?pid = "+ borrowId;
			}
			
			return {
				protocolText : protocolText,
				protocolUrl:protocolUrl
			}
		}
	}
	return Helper;
});
