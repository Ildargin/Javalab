<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Customer Manager</title>
  </head>
  <style>
      div {
          display: flex;
          flex-direction: column;
          margin: 0 auto;
          width: 50%;
      }

      table, th, tr, td {
          border: 1px solid gray;
          padding: 5px;
          margin: 5px;
      }

      td {
          text-align: center;
      }
  </style>
  <body>
    <div >
      <h2>Customer Manager</h2>
      <form method="get" action="search">
        <input type="text" name="keyword" /> &nbsp;
        <input type="submit" value="Search" />
      </form>
      <h3><a href="new">New Customer</a></h3>
      <table  >
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>E-mail</th>
          <th>Address</th>
          <th>Action</th>
        </tr>
          <#if customers??>
          <#list customers as customer>
            <tr>
              <td>${customer.id}
              <td>${customer.name}
              <td>${customer.email}
              <td>${customer.address}
              <td>
                <a href="edit?id=${customer.id}">Edit</a>
                &nbsp;&nbsp;&nbsp;
                <a href="delete?id=${customer.id}">Delete</a>
              </td>
            </tr>
          </#list>
          </#if>
      </table>
    </div>
  </body>
</html>
