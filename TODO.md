
## remove Router code -- it's not needed

## servlet refactor: add proper controllers in servlet

## improve graphics esp. single list page

## check/uncheck items

## Check HTTP status code in End2end test -- I tried testing for redirection after post, but somehow it always gives me 200 ???

## The controller DOES NOT USE THE DOMAIN OBJECT !!!  invent domain rules so that the domain object makes more sense

## Il EventStoreRepository non mi convince

 - chiamare il metodo di dominio per rename potrebbe
   - avere effetti diversi nel tempo, nel caso la regola di biz associata sia cambiata
   - essere inconsistente con le proiezioni!!!
   - scatenare altri domain events (e produrre ricorsivamente un nuovo rename domain event...)

 - si dovrebbe ricostruire l'oggetto evento originale?  e far gestire a lui le modifiche da fare all'oggetto?


## Il commit e rollback da database dovrebbero essere gestiti da fuori

## DomainEvent -- use reflection for ToString