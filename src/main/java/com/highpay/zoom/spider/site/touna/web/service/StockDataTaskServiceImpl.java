package com.highpay.zoom.spider.site.touna.web.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.highpay.zoom.spider.utils.http.HttpGetUtils;
import com.highpay.zoom.spider.utils.json.JsonMapper;

@Service("spiderFundDataTaskService")
public class StockDataTaskServiceImpl implements StockDataTaskService {
	// 配置您申请的KEY
	private static final String APPKEY = "e3859bc958228e06727584c7608d8171";

	private static final String SUCCESS_FLAG = "0";
	// pagesize ==80
	private static final String SZ_URL = "http://web.juhe.cn:8080/finance/stock/szall?key=" + APPKEY + "&type=4&page=";// 深圳请求接口地址

	private static final String SH_URL = "http://web.juhe.cn:8080/finance/stock/shall?key=" + APPKEY + "&type=4&page=";// 上海请求接口地址

	@Override
	public void spiderStockData() {
		getStock(SH_URL);
		getStock(SZ_URL);
	}

	private int getPageCount(String url) {
		String res = HttpGetUtils.sendGet(url).getResponseBody();
		int pageCount = 0;
		JsonMapper mapper = JsonMapper.nonDefaultMapper();
		Stock stock = mapper.fromJson(res, Stock.class);
		if (SUCCESS_FLAG.equals(stock.error_code)) {
			int totalCount = stock.result.totalCount;
			int pagesize = stock.result.num;
			// 计算pageCount
			pageCount = (totalCount - 1) / pagesize + 1;
		}
		return pageCount;
	}

	// 深圳股市,上海股市列表
	private void getStock(String url) {
		// 获取共有多少页
		int pageCount = getPageCount(url);
		if (pageCount < 1)
			return;
		JsonMapper mapper = JsonMapper.nonDefaultMapper();
		for (int i = 1; i <= pageCount; i++) {
			// 循环去取其他页的数据
			String url_page = url + i;
			String pageResult =HttpGetUtils.sendGet(url_page).getResponseBody();
			Stock stock = mapper.fromJson(pageResult, Stock.class);
			if (SUCCESS_FLAG.equals(stock.error_code)) {
				// 保存
				List<Data> datas = stock.result.data;
			}
		}
	}
	public static void main(String[] args) {
		new StockDataTaskServiceImpl().spiderStockData();
	}
	static class Stock{
		private String error_code;
		private String reason;
		private Result result;

		public String getError_code() {
			return error_code;
		}

		public String getReason() {
			return reason;
		}

		public Result getResult() {
			return result;
		}

		public void setError_code(String error_code) {
			this.error_code = error_code;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		public void setResult(Result result) {
			this.result = result;
		}

		@Override
		public String toString() {
			return "Stock [error_code=" + error_code + ", reason=" + reason + ", result=" + result + "]";
		}
	}

	static class Result {
		private int totalCount;
		private int page;
		private int num;
		private List<Data> data;

		public int getTotalCount() {
			return totalCount;
		}

		public int getPage() {
			return page;
		}

		public int getNum() {
			return num;
		}

		public List<Data> getData() {
			return data;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public void setNum(int num) {
			this.num = num;
		}

		public void setData(List<Data> data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return "Result [totalCount=" + totalCount + ", page=" + page + ", num=" + num + ", data=" + data + "]";
		}
	}

	static class Data {
		private String symbol;/* 代码 */
		private String name;/* 名称 */
		private BigDecimal trade;/* 最新价 */
		private String code;/* 简码 */

		public String getSymbol() {
			return symbol;
		}

		public String getName() {
			return name;
		}

		public BigDecimal getTrade() {
			return trade;
		}

		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setTrade(BigDecimal trade) {
			this.trade = trade;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		@Override
		public String toString() {
			return "Data [symbol=" + symbol + ", name=" + name + ", trade=" + trade + ", code=" + code + "]";
		}
	}



}
