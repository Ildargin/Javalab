<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Customer</title>
</head>
<body>
	<div align="center">
		<h2>New Customer</h2>
		<form action="save" method="post" >
			<table border="0" cellpadding="5">
				<tr>
					<td>Name: </td>
					<td><input type="text" name="name" /></td>
				</tr>
				<tr>
					<td>Email: </td>
					<td><input type="text" name="email" /></td>
				</tr>
				<tr>
					<td>Address: </td>
					<td><input type="text" name="address" /></td>
				</tr>		
				<tr>
					<td colspan="2"><input type="submit" value="Save"></td>
				</tr>						
			</table>
		</form>
	</div>
</body>
</html>