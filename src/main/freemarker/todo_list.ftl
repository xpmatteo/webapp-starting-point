<#include "layout.ftl">
<@layout "My Lists: ${todoList.name}">

  <style type="text/css">
    #list-name {
      display: inline;
    }
    #toggle-rename {
      font-style: italic;
      color: gray;
      font-size: small;
    }
    form#rename {
      margin-bottom: 3em;
    }
  </style>

  <h2 id="list-name">${todoList.name}</h2>
  <a href="#" id='toggle-rename'>rename</a>

  <form action="/todolists/${todoList.id}" method="post" id="rename" hidden="hidden">
    <input type="hidden" name="id" value="" />
    <p>
      <input type="text" value="${todoList.name}" name="new_name" value="" />
      <input type="submit" value="Change &rarr;"/>
    </p>
  </form>

  <ul id='todo' style='list-style: none'>
    <#list todoItems!"" as todoItem>
      <#if !todoItem.done>
        <li>
          <form action="/todoitems/${todoItem.id}" method="post">
            <input type="checkbox" name="done" value="true" onclick="form.submit()" />
            ${todoItem.todo_item_text}
          </form>
        </li>
      </#if>
    </#list>
  </ul>

  <form action="/todolists/${todoList.id}" method="post">
    <p>
      <input type="text" name="new_item" placeholder="e.g. Buy milk" autofocus="autofocus" />
      <input type="submit" value="+ Add this item" />
    </p>
  </form>

  <ul id='done'  style='list-style: none'>
    <#list todoItems!"" as todoItem>
      <#if todoItem.done>
      <li>
        <form action="/todoitems/${todoItem.id}" method="post">
          <input type="checkbox" name="done" value="false" onclick="form.submit()" checked="checked" />
          ${todoItem.todo_item_text}
        </form>
      </li>
      </#if>
    </#list>
  </ul>

  <script src='https://code.jquery.com/jquery-1.12.0.min.js'></script>
  <script>
    $(function() {
      $("#toggle-rename").click(function () {
        $('#rename').toggle(100);
      });
    });
  </script>

</@layout>
