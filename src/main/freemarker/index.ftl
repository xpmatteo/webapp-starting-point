<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>My Lists</title>
  </head>
  <body>
    <h1>My lists</h1>

    <ul>
      <#list todoLists as todoList>
        <li>
          <a href='/todolists/${todoList.id}'>${todoList.name}</a>
        </li>
      </#list>
    </ul>

    <hr>

    <form action="/todolists" method="post">
      <p>
        <label for="name">Name</label>
        <br>
        <input type="text/submit/hidden/button" name="name" value="" id="name">
      </p>
      <p><input type="submit" value="Create new Todolist &rarr;"></p>
    </form>
  </body>
</html>

