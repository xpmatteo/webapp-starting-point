# Freemarker cheat sheet

## Loop

    <#list people!"" as person>
        <li>${person.lastName} ${person.firstName}</li>
    </#list>

Default operator stops Freemarker from complaining when a variabile is not set

    ${foo!""}

## Macro definition

    <#macro cardImage source name>
      <img src="/images/cards/${source}.jpg" alt="${name}" width="100px"/>
    </#macro>

Use of a macro:

    <@cardImage source=card name=card />

## Function definition

    <#function name param1 param2 ... paramN>
      ...
      <#return returnValue>
      ...
    </#function>

## Setting a variable (e.g., for testing)

    <#assign seasons = ["winter", "spring", "summer", "autumn"]>

    <#assign x="Hello ${user}!">


