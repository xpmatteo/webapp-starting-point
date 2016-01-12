
## Make check test pass

## The controller DOES NOT USE THE DOMAIN OBJECT !!!  invent domain rules so that the domain object makes more sense
## Add arbitrary business rules
   - add wip to lists: cannot add things when there are more than WIP todo things.  but you can still uncheck things
   - add list name modifiers: swedish chef, pig latin...

## servlet refactor: add proper controllers in servlet
   - add router
   - router returns a command/query handler

## improve graphics esp. single list page

## uncheck items

## Check HTTP status code in End2end test -- I tried testing for redirection after post, but somehow it always gives me 200 ???

## Il commit e rollback da database dovrebbero essere gestiti da fuori

## Use UUID type, not string?

## WebRequest to take regexps like di questo tipo: "/foo/(?<bar>\w+)" to define a new parameter "bar"

## WebRequest should always imply "^ ... $"


