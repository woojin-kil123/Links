function commentList(movieId) {
	console.log(movieId);
	$.ajax({
		url : "/comment/oneMovieComment",
		data : {"contentNo":"m"+movieId},
		success : function(comments){
			console.log(comments);
			if(comments.length>0){
			   let carouselInner = $("#commentCarousel .carousel-inner");
		       let slideIndex = 0;
	
		       for (let i = 0; i < comments.length; i += 4) {
		           let commentSlice = comments.slice(i, i + 4);
		           let isActive = slideIndex === 0 ? "active" : "";
		           let slide = $("<div>").addClass(`carousel-item ${isActive}`);
		           let row = $("<div>").addClass("d-flex justify-content-between");
	
		           $.each(commentSlice, function (index, comment) {
		               let card = $("<div>").addClass("comment-card");
	
		               let header = $("<div>").addClass("comment-header");
		               let userInfo = $("<div>").addClass("comment-user");
		               let userImg = $("<img>").attr("src", "/image/userimg.png");
		               let memberId = $("<span>").text(comment.memberId);
	
		               userInfo.append(userImg, memberId);
		               let title = $("<span>").addClass("comment-title").text(comment.contentTitle);
		               header.append(userInfo, title);
	
					   
					   
		               let rating = $("<div>").addClass("comment-rating");
		               for (let j = 0; j < 5; j++) {
		                   let star = $("<span>").addClass("star").text(j < comment.rating ? "★" : "☆");
		                   rating.append(star);
		               }
	
		               let body = $("<div>").addClass("comment-body").text(comment.commentContent);
		               let footer = $("<div>").addClass("comment-footer");
		               let like = $("<span>").append($("<i>").addClass("icon 👍"), comment.likeCount);
		               let dislike = $("<span>").append($("<i>").addClass("icon 💔"), comment.isLike);
		               
		               footer.append(like, dislike);
		               card.append(header, rating, body, footer);
		               row.append(card);
		           });
	
		           slide.append(row);
		           carouselInner.append(slide);
		           slideIndex++;
				}
				$("#comment-count").text(comments.length);
				if(comments.length>4){
					$(".container>.more-comments").css("display","inline-block");
					$(".more-comments").on("click", function () {
				       window.location.href = "/comment/mCommentMemberList";
					});
				}
			}
		}
	});
}

function makeSlide(obj1,obj2){
		const movies = obj1;
		const carouselContent = obj2;
        let slideIndex = 0;
        let indexNo= 1;
        for (let i = 0; i < movies.length; i += 5) {
            let moviesSlice = movies.slice(i, i + 5);
            let isActive = slideIndex === 0 ? "active" : "";
            let slide = $("<div>").addClass(`carousel-item ${isActive}`);
            let row = $("<div>").addClass("row d-flex justify-content-center");

            
            $.each(moviesSlice, function (index, movie) {
                let col = $("<div>").addClass("col-md-2 mx-1");

                const imgSrc = "https://image.tmdb.org/t/p/w342"+movie.posterPath;
                let card = $("<div>").addClass("card");
                const id = movie.movieId;
                card.on('click',function(){
                	location.href = "/api/movieDetail?movieId="+id;
                });
                let rankBadge = $("<div>").addClass("rank-badge").text(indexNo++);
                let img = $("<img>").addClass("card-img-top").attr("src", imgSrc).attr("alt", "this.src=/image/No_Image.jpg");
                let imgDiv = $("<div>").addClass("img-div").append(img);

                let cardBody = $("<div>").addClass("card-body");
                let title = $("<div>").addClass("card-title").text(movie.title);
                let info = $("<div>").addClass("card-info").text(movie.releaseDate);
                
                let rating = $("<div>").addClass("rating");
                //let ratingText = $("<span>").text(movie.rating);
                //rating.append(star);
                let genreInfo = $("<div>").addClass("genre-info");						
                $.each(movie.genreIds, function(index, genre){
                	const p = $("<p>").append(genre);
                	genreInfo.append(p);
                });
                // 카드 조립
                cardBody.append(title, info, rating, genreInfo);
                card.append(rankBadge, imgDiv, cardBody);
                col.append(card);
                row.append(col);
            });
            slide.append(row);
            carouselContent.append(slide);
            slideIndex++;
        }
	}


