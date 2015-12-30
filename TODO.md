
## Il EventStoreRepository non mi convince

 - chiamare il metodo di dominio per rename potrebbe
   - avere effetti diversi nel tempo, nel caso la regola di biz associata sia cambiata
   - essere inconsistente con le proiezioni!!!
   - scatenare altri domain events (e produrre ricorsivamente un nuovo rename domain event...)

 - si dovrebbe ricostruire l'oggetto evento originale?  e far gestire a lui le modifiche da fare all'oggetto?


## Il commit e rollback da database dovrebbero essere gestiti da fuori

