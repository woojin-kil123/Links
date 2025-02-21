package kr.co.iei.etc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Controller
@RequestMapping(value = "/company")
public class CompanyController {
@ResponseBody
 @GetMapping(value = "/check")
 public Check check(String businessNo) {
	Check check = new Check();
	 String url ="https://bizno.net/api/fapi";
	 String  serviceKey="dGtkYWxzOTkwOUBuYXZlci5jb20g";
	 System.out.println(businessNo);
		try {
			String result =Jsoup.connect(url)
			.data("key",serviceKey)
			.data("q",businessNo)
			.data("type","json")
			.ignoreContentType(true)
			.get()
			.text();
		System.out.println(result);
		JsonObject object=(JsonObject)JsonParser.parseString(result);
		JsonArray items = object.get("items").getAsJsonArray();
		JsonObject company = items.get(0).getAsJsonObject();
		String companyName= company.get("company").getAsString();
		String companyNo= company.get("bno").getAsString();
		 check = new Check(companyName,companyNo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return check;
 }
 
}
