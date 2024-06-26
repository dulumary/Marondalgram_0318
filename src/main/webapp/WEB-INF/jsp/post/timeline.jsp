<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>타임라인</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
<link rel="stylesheet" href="/static/css/style.css" type="text/css">

</head>
<body>

	<div id="wrap">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		<section class="d-flex justify-content-center">
			<!-- 전체 layout -->
			<div class="main-contents">
				<!-- 입력 상자 -->
				<div class="input-box border rounded">
					<textarea rows="4" class="form-control border-0" id="contentsInput"></textarea>
					<div class="d-flex justify-content-between p-2">
						<i class="bi bi-image big-font" id="imageIcon"></i>
						<input type="file" id="fileInput" class="d-none">
						<button type="button" class="btn btn-info btn-sm" id="saveBtn">입력</button>
					</div>
					
				</div>
				<!-- /입력 상자 -->
				
				<!-- 타임라인 -->
				<div class="timeline my-4">
					<c:forEach var="post" items="${postList }">
					<!-- 카드 -->
					<div class="card my-3">
						<div class="d-flex justify-content-between p-2">
							<div>${post.userLoginId }</div>
							<%-- 로그인한 사용자가 작성한 게시글일 경우  --%>
							<c:if test="${userId eq post.userId }">
							<i class="bi bi-three-dots-vertical more-btn" data-post-id="${post.id }"  data-toggle="modal" data-target="#moreModal"></i>
							</c:if>
						</div>
						<div>
							<img class="w-100" src="${post.imagePath }">
						</div>
						<div class="p-2">
							<c:choose>
								<c:when test="${post.like }">
									<i class="bi bi-heart-fill text-danger unlik-icon" data-post-id="${post.id }" ></i>
								</c:when>
								<c:otherwise>
									<i class="bi bi-heart like-icon" data-post-id="${post.id }"></i>
								</c:otherwise>
							</c:choose>
							
							좋아요 ${post.likeCount }개
						</div>
						
						<div class="p-2">
							<b>${post.userLoginId }</b> ${post.contents }
						</div>
						<!-- 댓글 목록 -->
						<div class="comment-box">
							<div class="pl-2">댓글</div>
							<div class="p-2">
								<c:forEach var="comment" items="${post.commentList }">
								<div><b>${comment.userLoginId }</b> ${comment.contents }</div>
								</c:forEach>
							</div>
							<div class="d-flex justify-content-between">
								<input type="text" class="form-control col-10" id="commentInput${post.id }">
								<button type="button" class="btn btn-info col-2 comment-btn" data-post-id="${post.id }">게시</button>
							</div>
						</div>
					</div>
					<!-- /카드 -->
					</c:forEach>
				</div>
				<!-- /타임라인 -->
			
			</div>
			<!-- /전체 layout -->
			
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp" />
	</div>


<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#moreModal">
  Launch demo modal
</button>

<!-- Modal -->
<div class="modal fade" id="moreModal">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      
      <div class="modal-body text-center">
        <a href="#" id="deleteBtn" > 삭제하기 </a>
      </div>
     
    </div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js" integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+" crossorigin="anonymous"></script>

<script>
	$(document).ready(function() {
		
		$("#deleteBtn").on("click", function() {
			
			let id = $(this).data("post-id");
			
			$.ajax({
				type:"delete"
				, url:"/post/delete"
				, data:{"id":id}
				, success:function(data) {
					if(data.result == "success") {
						location.reload();
					} else {
						alert("삭제 실패");
					}
				}
				, error:function() {
					alert("삭제 에러");
				}
			});
		});
		
		
		$(".more-btn").on("click", function() {
			let id = $(this).data("post-id");
			
			// 삭제 버튼에 data-post-id에 해당하는 postId 값을 저장
			$("#deleteBtn").data("post-id", id);
			
			
			
		});
		
		$(".unlik-icon").on("click", function() {
			
			let id = $(this).data("post-id");
			
			$.ajax({
				type:"delete"
				, url:"/post/unlike"
				, data:{"postId":id}
				, success:function(data) {
					if(data.result == "success") {
						location.reload();
					} else {
						alert("좋아요 취소 실패");
					}
				}
				, error:function() {
					alert("좋아요 취소 에러");
				}
				
			});
			
			
		});
		
		
		$(".comment-btn").on("click", function() {
			
			let id = $(this).data("post-id");
			
			// 버튼 옆에 있는 인풋태그 객체화
			// 1. 이벤트가 발생한 버튼 태그 옆에 있는 태그를 개체화 
			
			// let comment = $(this).prev().val();
			
			// 2. 버튼태그가 가진 데이터를 이용해서 input 태그에 id 부여 
			
			let comment = $("#commentInput" + id).val();
			
			$.ajax({
				type:"post"
				, url:"/post/comment/create"
				, data:{"postId":id , "contents":comment}
				, success:function(data) {
					if(data.result == "success") {
						location.reload();
					} else {
						alert("댓글입력 실패");
					}
				}
				, error:function() {
					alert("댓글입력 에러");
				}
			});
			
			
		});
		
		$(".like-icon").on("click", function() {
			
			// 좋아요한 대상 게시글 pk
			// data-post-id
			let id = $(this).data("post-id");
			
			$.ajax({
				type:"post"
				, url:"/post/like"
				, data:{"postId":id}
				, success:function(data) {
					if(data.result == "success") {
						location.reload();
					} else {
						alert("좋아요 실패");
					}
				}
				, error:function() {
					alert("좋아요 에러")
				}
			});
			
			
		});
		
		$("#imageIcon").on("click", function() {
			$("#fileInput").click();
		});
		
		$("#saveBtn").on("click", function() {
			
			let contents = $("#contentsInput").val();
			let file = $("#fileInput")[0].files[0];
			
			if(contents == "") {
				alert("내용을 입력하세요");
				return ;
			}
			
			// 파일 선택에 대한 유효성 검사 
			if(file == null) {
				alert("파일을 선택하세요");
				return ;
			}
			
			
			let formData = new FormData();
			formData.append("contents", contents);
			formData.append("imageFile", file);
			
			$.ajax({
				type:"post"
				, url:"/post/create"
				, data:formData
				, enctype:"multipart/form-data"
				, processData:false
				, contentType:false
				, success:function(data) {
					if(data.result == "success") {
						location.reload();
					} else {
						alert("글쓰기 실패");
					}
				}
				, error:function() {
					alert("글 쓰기 에러");
				}
				
			});
			
			
		});
	
	});
</script>

</body>
</html>