<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="ProductImage" method="post" enctype="multipart/form-data">

		<input type="file" name="image" onchange="readURL(this)" targetID="preview_progressbarTW_img" accept="image/gif, image/jpeg, image/png" /> 
		<br>
		<img id="preview_progressbarTW_img" src="#" width="200px" height="200px"/>
		<br>
		 <input type="submit" value="Add Image">

	</form>

	<!-- JavaScript part -->

	<script>
		function readURL(input) {

			if (input.files && input.files[0]) {

				var imageTagID = input.getAttribute("targetID");

				var reader = new FileReader();

				reader.onload = function(e) {

					var img = document.getElementById(imageTagID);

					img.setAttribute("src", e.target.result)

				}

				reader.readAsDataURL(input.files[0]);

			}

		}
	</script>



</body>
</html>