package kr.co.iei.member.model.vo;

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
 private String memberPw;
 private String memberEmail;
 private String memberPhone;
 private String memberRole;
 private int warningLevel;
 private String del;
 private String delDate;
}
