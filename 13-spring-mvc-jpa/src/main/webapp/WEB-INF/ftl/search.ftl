<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Search Result</title>
  </head>
  <body>
    <div align="center">
      <h2>Search Result</h2>
      <table border="1" cellpadding="5">
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>E-mail</th>
          <th>Address</th>
        </tr>
          <#list customers as customer>
            <tr>
              <td>${customer.id}</td>
              <td>${customer.name}</td>
              <td>${customer.email}</td>
              <td>${customer.address}</td>
            </tr>
          </#list>
      </table>
    </div>
  </body>
</html>
