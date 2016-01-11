<#include "layout.ftl">
<@layout "My Lists: ${todoList.name}">

  <h2>${todoList.name}</h2>

  <form action="/todolists/${todoList.id}" method="post">
    <input type="hidden" name="id" value="" />
    <p>
      <label for="new_name">New name</label> <input type="text" name="new_name" value="" />
      <input type="submit" placeholder="New name" value="Change &rarr;"/>
    </p>
  </form>

  <ul id='todo'>
    <#list todoItems!"" as todoItem>
        <li>
          <form action="/todoitems/${todoItem.id}" method="post">
            <input type="checkbox" name="done" value="true" onclick="form.submit()" />
            ${todoItem.todo_item_text}
          </form>
        </li>
    </#list>
  </ul>

  <form action="/todolists/${todoList.id}" method="post">
    <p>
      <input type="text" name="new_item" placeholder="e.g. Buy milk" />
      <input type="submit" value="+ Add this item" />
    </p>
  </form>

  <ul id='done'>
    <#list todoItems!"" as todoItem>
    <li>
      <form action="/todoitems/${todoItem.id}" method="post">
        <input type="checkbox" name="done" value="false" onclick="form.submit()" checked="checked" />
        ${todoItem.todo_item_text}
      </form>
    </li>
    </#list>
  </ul>

</@layout>
