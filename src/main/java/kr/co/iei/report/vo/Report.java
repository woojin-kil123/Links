package kr.co.iei.report.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Report {
	private int writeNo;
	private int reporterMemberNo;
	private int reportedMemberNo;
	private String reportReason;
	private String reportYn;
}
