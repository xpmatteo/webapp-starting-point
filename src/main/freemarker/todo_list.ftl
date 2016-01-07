<#include "layout.ftl">
<@layout "My Lists: ${todoList.name}">
<h2>${todoList.name}</h2>

<form action="/todolists/${todoList.id}" method="post">
  <input type="hidden" name="id" value="" />

  <p>
    <label for="new_name">New name</label> <input type="text" name="new_name" value="" />
    <input type="submit" placeholder="New name" value="Change &rarr;"/>
  </p>

  <#assign todoItems = [
    {"text":"First thing to do", "checked": "true"},
    {"text":"Second thing to do", "checked": "false"},
    {"text":"Third thing to do", "checked": "false"}
  ]>

  <#list todoItems!"" as todoItem>
      <li>${todoItem.text}</li>
  </#list>

  <form action="/todolists/${todoList.id}" method="post">
    <p>
      <input type="text" name="new_item" placeholder="e.g. Buy milk" />
      <input type="submit" value="+ Add this item" />
    </p>
  </form>

</form>
</@layout>