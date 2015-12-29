
<h2>${todoList.name}</h2>

<form action="/todolists/${todoList.id}" method="post">
  <input type="hidden" name="id" value="">

  <p>
    <label for="new_name">New name</label> <input type="text" name="new_name" value="" >
    <input type="submit" value="Change &rarr;">
  </p>
</form>