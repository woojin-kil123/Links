function commentList(movieId) {
	console.log(movieId);
	$.ajax({
		url : "/comment/oneMovieComments",
		data : {"contentNo":"m"+movieId},
		success : function(comments){
			if(comments.length==0){
				$(".nocomment-msg").show();				
			}
			const carouselInner = $("#commentCarousel .carousel-inner");
			let slideIndex = 0;
	       for (let i = 0; i < comments.length; i += 4) {
				$(".nocomment-msg").hide();	
	           const commentSlice = comments.slice(i, i + 4);
	           let isActive = slideIndex === 0 ? "active" : "";
	           const slide = $("<div>").addClass(`carousel-item ${isActive}`);
	           const row = $("<div>").addClass("d-flex justify-content-between");
	           $.each(commentSlice, function (index, comment) {
	              const card = $("<div>").addClass("comment-card").css("cursor","pointer");
				  card.on("click",function(){
					location.href="/comment/commentView?commentNo="+comment.commentNo;
				   });
	              const header = $("<div>").addClass("comment-header");
	              const userInfo = $("<div>").addClass("comment-user");
	              const userImg = $("<img>").attr("src", "/image/userimg.png");
	              const memberId = $("<span>").text(comment.memberId);

	              userInfo.append(userImg, memberId);
	              const title = $("<span>").addClass("comment-title").text(comment.movieTitle);
	              header.append(userInfo, title);
	              const rating = $("<div>").addClass("comment-rating");
	              for (let j = 0; j < 5; j++) {
	                   const star = $("<span>").addClass("star").text(j < comment.starPoint ? "★" : " ");
	                   rating.append(star);
	              }
	              const body = $("<div>").addClass("comment-body").text(comment.commentContent);
	              const footer = $("<div>").addClass("comment-footer");
				  footer.append(rating);
	              card.append(header, body, footer);
	              row.append(card);
	           });
	           slide.append(row);
	           carouselInner.append(slide);
	           slideIndex++;
			}
			$("#comment-count").text(comments.length);
			if(comments.length>4){
				$(".container>.more-comments").show();
				$(".more-comments").on("click", function () {
			       location.href = "/comment/mCommentMemberList";
				});
			}
		},
		error : function(){
				console.log("error");
		}				
	});
}

function makeSlide(obj1,obj2){
	const movies = obj1;
	const carouselContent = obj2;
    let slideIndex = 0;
    let indexNo= 1;
    for (let i = 0; i < movies.length; i += 5) {
        const moviesSlice = movies.slice(i, i + 5);
        const isActive = slideIndex === 0 ? "active" : "";
        const slide = $("<div>").addClass(`carousel-item ${isActive}`);
        const row = $("<div>").addClass("row d-flex justify-content-evenly");

        
        $.each(moviesSlice, function (index, movie) {
            const col = $("<div>").addClass("col-md-2 mx-1");
            const imgSrc = "https://image.tmdb.org/t/p/w342"+movie.posterPath;
            const card = $("<div>").addClass("card");
            const id = movie.movieId;
            card.on('click',function(){
            	location.href = "/api/movieDetail?movieId="+id;
            });
            const rankBadge = $("<div>").addClass("rank-badge").text(indexNo++);
			const img = $("<img>").addClass("card-img-top").attr("src", imgSrc)
			if(movie.posterPath==null){
					img.attr("src", "/image/No_Image.jpg")
				}
            const imgDiv = $("<div>").addClass("img-div").append(img);
            const cardBody = $("<div>").addClass("card-body");
            const title = $("<div>").addClass("card-title").text(movie.title);
            const info = $("<div>").addClass("card-info").text(movie.releaseDate);
            const genreInfo = $("<div>").addClass("genre-info");
			if((movie.genreIds).length==0){
				genreInfo.text("");
			}						
            $.each(movie.genreIds, function(index, genre){
            	const p = $("<p>").append(genre);
            	genreInfo.append(p);
            });
            cardBody.append(title, info, genreInfo);
            card.append(rankBadge, imgDiv, cardBody);
            col.append(card);
            row.append(col);
        });
        slide.append(row);
        carouselContent.append(slide);
        slideIndex++;
    }
}
function loadAd(position){
	let requestUrl = '/admin/adUrl';
	    
	    $.ajax({
	        url: requestUrl,              
	        type: 'GET',
	        data: {"position" : position},  
	        dataType: 'text',
	        success: function(adUrl) {
	            $(".ad-banner img").attr("src", "/image/"+adUrl).css("cursor","pointer").on("click",function(){
					$.ajax({
						url : "/admin/plusAdClick",
						data : {"adPosition":position},
						success : function(){
							location.href = "https://kh-academy.co.kr/main/main.kh";
						}
					})
				});
	        },
	        error: function(error) {
	            console.log("Error: " + error);
	        }
	    });

}
	    
	    
