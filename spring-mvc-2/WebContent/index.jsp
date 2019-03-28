<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#testjson").click(function(){
			var url = this.href;
			var args = {};
			$.post(url,args,function(data){
				alert("ok");
				for(var i= 0 ; i <data.length;i++){
					var id=data[i].id;
					var lastname=data[i].lastName;
					alert(id+lastname);
				}
				
			});
			return false;
		});
	})
</script>
</head>
<body>
	<a href="testExceptionhandler?i=10"> Test Exceptionhandler </a>
	<br><br>
	
	
	<a href="testIntercepters"> Test iercepters </a>
	<br><br>
		
	<form action="testFileUpload" method="POST" enctype="multipart/form-data">
	flie<input type="file" name="file">
	name<input type="text" name="desc"> 
	<input type="submit" value="submit">
	
	</form>	

	<a href="emps"> all emps </a>
	<br><br>
	<a href="testJson" id="testjson"> testJson </a>
	
		<br><br>
		
	<a href="testResponseEnity"> Test ResponseEnity </a>
	
	<br><br>
	

</body>
</html>