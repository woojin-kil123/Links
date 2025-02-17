package kr.co.iei.member.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
 private int memberNo;
 private String memberName;
 private String memberId;
 private String memberPW;
 private String memberEmail;
 private String memberPhone;
 private String memberRole;
 private int memberLevel;
 private String del;
}
