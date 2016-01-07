<#include "layout.ftl">
<@layout "My Lists">
  <ul id="my-lists">
    <#list todoLists as todoList>
      <li>
        <a href='/todolists/${todoList.id}'>${todoList.name}</a>
      </li>
    </#list>
  </ul>

  <hr />

  <form action="/todolists" method="post">
    <p>
      <input type="text" name="name" value="" placeholder="List Name" id="name" />
      <input type="submit" value="Create new" />
    </p>
  </form>
</@layout>
