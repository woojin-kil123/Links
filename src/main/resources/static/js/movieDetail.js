const movieId = [[${movie.movieId}]];
const memberId = [[${session.member?.memberId}]];
//내가준 별점 불러오기
$("[name=rating]").on("click",function(){
	if(memberId==null){
		location.href="/member/loginFrm";
	}else{
		const value = $(this).val();
		$(".rating-input").text(value);
		$(".rating").click();
	}
});
//작업해
$("#like").on("click",function(){
	if($(this).hasClass("liking")){
		//좋아요 누르는 작업해야함
		deleteContentLike();
		$(this).removeClass("liking");
	}else{
		insertContentLike();
		$(this).addClass("liking");
	}
});		
function insertContentLike(){
	$.ajax({
		url : "/contents/insertContentLike",
		data : {"contentNo":"m"+movieId, "memberId" : memberId},
		success: function(data){
		}
	})			
}
function deleteContentLike(){
	$.ajax({
		url : "/contents/deleteContentLike",
		data : {"contentNo":"m"+movieId, "memberId" : memberId},
		success: function(data){
		}
	})			
}
$(".rating-submit").on("click",function(){
	const value = $(".rating-input").text();
	$.ajax({
		url : "/contents/insertRating",
		type : "get",
		data : {"starpoint":value, "memberId":memberId, "contentNo": "m"+movieId},
		success : function(data){
			selectMemberStar();
			selectMovie();
		}
	})
});

$(".comment-submit").on("click",function(){
	const commentContent = $(this).parent().prev().find("#content").val();
	console.log(commentContent);
	 
	$.ajax({
		url : "/comment/insertComment",
		type : "post",
		data : {"contentNo" : "m"+movieId, "memberId":memberId,"commentContent" : commentContent},
		success: function(data){
			if(data>0){
				//console.log("comment 입력 결과 : "+ data);
			}
		}
	});			
})
function selectMovie(){
	 $.ajax({
			url : "/contents/selectMovie",
			type : "get",
			data : {"movieId":movieId},
			success: function(data){
				console.log("movie 조회결과 : "+ data);
				if(data==""){
					console.log("null결과 : "+ data);
					const movieTitle = [[${movie.title}]];
					const posterPath = [[${movie.posterPath}]];
					$.ajax({
						url : "/contents/insertMovie",
						type : "post",
						data : {"movieId":movieId, "movieTitle" : movieTitle, "posterPath" : posterPath},
						success: function(data){
							
						},
						error : function(){
							console.log("insert error");						
						}
					})
				}else{
					/*
					private String  movieAvgPoint;
					private String moviePlatform;
					private int linkClick;
					*/
					if(data.movieAvgPoint==null){
						$("#avg-star-input").text("아직 평가가 없어요 ㅠㅠ");
					}else{
						$("#avg-star-input").text(data.movieAvgPoint).addClass("fs-2");
					}
					$("#link").attr("href",data.moviePlatform);
				}
			},
			error : function(){
				console.log("select error");						
			}
		});
 };
	function selectMemberInfo(){
		if(memberId!=null){
			$.ajax({
				url : "/contents/selectMemberStar",
				data :{"memberId" : memberId, "contentNo" : "m"+movieId},
				success : function(data){
					console.log(data);
					if(data>0){
						$(".star-rating").removeClass("star-hover");
						$(".star-rating>input+label").removeAttr("for");
						for(let i=0; i<data; i++){
							$("[name=rating]").eq(4-i).prop("checked",true);
						}
						$(".rating").removeClass("rating");
						$("#mystar-check").css("display","block");
					}
				}
			});
			console.log(memberId);
			$.ajax({
				url : "/contents/selectMemberLike",
				data :{"memberId" : memberId, "contentNo" : "m"+movieId},
				success : function(data){
					console.log(data);
					if(data>0){
						$("#like").addClass("liking");
					}
				}
			})
		}
	}
$(".toLogin").on("click",function(){
		location.href= "/member/loginFrm";
});

selectMovie();
selectMemberInfo();
