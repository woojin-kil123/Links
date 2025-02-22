package kr.co.iei.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Business {
	private String companyName;
	private String inquiryCategory;
	private String memberPhone;
	private String inquiryDate;
	private String inquiryProgress;
}
