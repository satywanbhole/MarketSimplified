package com.springboot.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.model.Data;
import com.springboot.model.Meta;
import com.springboot.repository.DataRepository;
import com.springboot.repository.MetaRepository;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class MutualFundServiceImp implements MutualFundService {

	@Autowired
	public MetaRepository metaRepository;
	@Autowired
	public DataRepository dataRepository;
	@Autowired
	private final OkHttpClient httpClient = new OkHttpClient();

	@Override
	public String getSchemeDataByCode(String schemeCode) {
		try {
			Request request = new Request.Builder().url("https://api.mfapi.in/mf/" + schemeCode).build();

			Response response = httpClient.newCall(request).execute();
			String responseBody = response.body().string();

			JSONObject json = new JSONObject(responseBody);
			System.out.println(json);

			Meta metaObject = new Meta();
			Data dataObject = null;

			JSONObject meta = (JSONObject) json.get("meta");

			metaObject.setSchemeCategory(meta.get("scheme_category").toString());
			metaObject.setSchemeName(meta.get("scheme_name").toString());
			metaObject.setFundHouse(meta.get("fund_house").toString());
			metaObject.setSchemeCode(meta.getInt("scheme_code"));
			metaObject.setSchemType(meta.get("scheme_type").toString());
			

			metaRepository.save(metaObject);

			JSONArray dataArr = (JSONArray) json.get("data");

			for (int i = 0; i < dataArr.length(); i++) {
				dataObject = new Data();
				dataObject.setDate(((JSONObject) dataArr.get(i)).get("date").toString());
				dataObject.setNav(((JSONObject) dataArr.get(i)).get("nav").toString());				
				dataRepository.save(dataObject);
				
				// metaRepository.save(dataObject);

			}

			return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}

	}

	// For get all the schemeName data from database

	@Override
	public Meta getAllSchemeData(String schemeName) {

		return metaRepository.findBySchemeName(schemeName);
	}

	// For get all the SchemeId data from database method Implementation

	@Override
	public Map<String, String> getMutualFund(String schemeCode, String filter) {

		Meta metaObject = new Meta();
		try {
			LocalDate fromDate = LocalDate.now();
			LocalDate toDate = LocalDate.now();
			switch (filter) {
			case "1W":
				fromDate = fromDate.minusWeeks(1);
				break;
			case "1M":
				fromDate = fromDate.minusMonths(1);
				break;
			case "1Y":
				fromDate = fromDate.minusYears(1);
				break;
			case "5Y":
				fromDate = fromDate.minusYears(5);
				break;
			default:
				fromDate = fromDate.minusMonths(1);
				break;
			}

			Request request = new Request.Builder().url("https://api.mfapi.in/mf/" + schemeCode).build();

			Response response = httpClient.newCall(request).execute();
			String responseBody = response.body().string();

			JSONObject jsonObject = new JSONObject(responseBody);
			JSONArray data = jsonObject.getJSONArray("data");

			List<Data> dataList = new ArrayList<Data>();

			for (int i = 0; i < data.length(); i++) {
				JSONObject object = data.getJSONObject(i);
				LocalDate date = LocalDate.parse(object.getString("date").toString());

				if (!date.isBefore(fromDate) && !date.isAfter(toDate)) {

					Data dataObject = new Data();
					dataObject.setDate(date.toString());
					dataObject.setNav(object.getString("nav"));
					dataList.add(dataObject);
				}
			}

			JSONObject meta = (JSONObject) jsonObject.get("meta");

			metaObject.setFundHouse(meta.get("fund_house").toString());
			metaObject.setSchemeCode(meta.getInt("scheme_code"));
			metaObject.setSchemeName(meta.get("scheme_name").toString());

			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("Meta", metaObject.toString());
			map.put("data", dataList.toString());

			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

// ************************************************************************************************************************************

// DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
//           .parseCaseInsensitive().parseLenient()
//           .parseDefaulting(ChronoField.YEAR_OF_ERA, 2016L)
//           .appendPattern("[yyyy-MM-dd]")
//           .appendPattern("[M/dd/yyyy]")
//           .appendPattern("[M/d/yyyy]")
//           .appendPattern("[MM/dd/yyyy]")
//           .appendPattern("[MMM dd yyyy]");
//
//   DateTimeFormatter formatter2 = builder.toFormatter(Locale.ENGLISH);

//	// DateTimeFormatterBuilder provides custom way to create a
//   // formatter
//   // It is Case Insensitive, Nov , nov and NOV will be treated same
//   DateTimeFormatter f = new DateTimeFormatterBuilder().parseCaseInsensitive()
//           .append(DateTimeFormatter.ofPattern("yyyy-MMM-dd")).toFormatter();
//   try {
//       LocalDate datetime = LocalDate.parse("2019-DeC-22", f);
//       System.out.println(datetime); // 2019-12-22
//   } catch (DateTimeParseException e) {
//       System.out.println(e);
//   }
