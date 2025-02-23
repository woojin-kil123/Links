package kr.co.iei.admin.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stats {
	private int memberCount;
	private int adClickCount;
	private int linkCount;
	private int reportCount;
}
