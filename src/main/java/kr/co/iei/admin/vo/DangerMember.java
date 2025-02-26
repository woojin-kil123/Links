package kr.co.iei.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DangerMember {
	private String memberId;
	private String memberPhone;
	private String memberEmail;
	private int warningLevel;
	private int reported;
	private int falseReport;
}
