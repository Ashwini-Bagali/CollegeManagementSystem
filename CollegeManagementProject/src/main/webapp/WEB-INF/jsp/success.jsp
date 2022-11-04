<html>
<head>
<link rel="stylesheet" href="css/cardstyle.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <script src="https://kit.fontawesome.com/1cf483120b.js" crossorigin="anonymous"></script>
</head>
  <body>
  <h1>${result}</h1>
   <table border="1">
		<th>id</th>
		<th>name</th>
		<th>email</th>
		<th>PhoneNo</th>
				<th>Course</th>
		
		  	<c:forEach var="detail" items="${listStd}" >
			<tr>
				<td>${detail.id}</td>
				<td>${detail.name}</td>
				<td>${detail.email}</td>
				<td>${detail.phno}</td>
				<td>${detail.course}</td>
				
			</tr>
		</c:forEach>
	</table>
	<form action="search/ash" >
	<button >search</button>
	</form>
	<button></button>
  </body>
</html>