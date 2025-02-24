package kr.co.iei.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Inquiry {
	private int inquiryNo;
	private int memberNo;
	private String memberId;
	private String companyName;
	private String memberPhone;
	private String memberEmail;
	private String CompanyNo;
	private String inquiryCategory;
	private String inquiryContent;
	private String inquiryDate;
	private int inquiryProgress;
}
