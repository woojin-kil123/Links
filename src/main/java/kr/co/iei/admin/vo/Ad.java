package kr.co.iei.admin.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ad {
	private int adNo;
	private int inquiryNo;
	private String companyName;
	private String adPosition;
	private String adUrl;
	private Date expireDate;
	private int adClick;
}
