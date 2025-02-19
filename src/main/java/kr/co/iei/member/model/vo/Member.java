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
<<<<<<< HEAD
 private String memberPw;
 private String memberEmail;
 private String memberPhone;
 private String memberRole;

 private int warningLevel;

 private int waringLevel;

=======
 private String memberPW;
 private String memberEmail;
 private String memberPhone;
 private String memberRole;
 private int memberLevel;
>>>>>>> parent of f8e864b (2.18)
 private String del;
}
