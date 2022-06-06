<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.css">
<title>新增文章</title>
<style>
* {
	margin: 0;
	padding: 0;
}

body, html {
	width: 100%;
	height: 100%;
}

.textbody {
	background-color: #f6f8fc;
	text-align: center;
}

.title {
	padding: 20px
}

.text_title {
	resize: none;
	width: 60%;
	white-space: nowrap; /* 禁止換行 */
	overflow: hidden; /* 不顯示捲軸 */
}

.text_area {
	resize: none;
	width: 60%;
}

.text {
	padding: 10px;
}

.wordsNum1 {
	width: 80%;
	position: relative;
	text-align: right;
}

.wordsNum2 {
	width: 80%;
	position: relative;
	text-align: right;
}
</style>
</head>

<body>
	<div class="textbody">
		<div class="title">

			<h3>新增文章</h3>
		</div>
		<hr>
		<form action="PostNew" enctype="multipart/form-data" method="post" id="sub">
			上傳圖片：<input type="file" name="image">
			<hr>
			<h5 class="text">文章標題:</h5>
			<textarea class="text_title" name="title" rows="1" maxlength="100" required></textarea>
			<p class="wordsNum1">0/100</p>
			<h5 class="text">文章內容:</h5>
			<textarea class="text_area" name="postedText" rows="20" maxlength="5000" required></textarea>
			<p class="wordsNum2">0/5000</p>
			<input type='hidden' name='update' value="${post.postId}">
			<input type="submit" id="yesno" name="add" value="發表文章" />
		</form>

	</div>

	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.2/jquery-confirm.min.js"></script>
	<script>
		$(function() {
			var checkStrLengths = function(str, maxLength) {
				var maxLength = maxLength;
				var result = 0;
				if (str && str.length > maxLength) {
					result = maxLength;
				} else {
					result = str.length;
				}
				return result;
			}
			$(".text_title").on('input propertychange', function() {

				//獲取輸入內容
				var userDesc = $(this).val();

				//判斷字數
				var len;
				if (userDesc) {
					len = checkStrLengths(userDesc, 100);
				} else {
					len = 0
				}

				//顯示字數
				$(".wordsNum1").html(len + '/100');
			});
			$(".text_area").on('input propertychange', function() {

				//獲取輸入內容
				var userDesc = $(this).val();

				//判斷字數
				var len;
				if (userDesc) {
					len = checkStrLengths(userDesc, 5000);
				} else {
					len = 0
				}

				//顯示字數
				$(".wordsNum2").html(len + '/5000');
			});
			

			$('#yesno').click(function(event) {
		        if(($.confirm({
		          title: '',
		          animation: 'zoom',
		          closeAnimation: 'scale',
		          content: '請問是否確定送出？',
		          buttons: {
		            是: function() {
		                $('#sub').submit();
		            },
		            否: function() {
		               
		            }
		          }
		        }))){
		        event.preventDefault();
		      }
		      });

		})
	</script>
</body>
</html>