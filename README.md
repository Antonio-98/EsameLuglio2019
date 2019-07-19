# EsameLuglio2019
Repository dedicata all'upload del progetto per il corso di programmazione ad oggetti di Paolo Cacciatore e Giulia Temperini.

# Overview
Il progetto qui proposto fornisce un'applicazione WEB in JAVA implementata tramite l'utilizzo di SpringBoot.
Tale applicazione prevede la gestione di un dataset tramite richieste HTTP da parte del client.
Tale dataset in formato *CSV* è reperibile al seguente URL:
[http://data.europa.eu/euodp/data/api/3/action/package_show?id=yGVKnIzbkC2ZHpT6jQouDg](http://data.europa.eu/euodp/data/api/3/action/package_show?id=yGVKnIzbkC2ZHpT6jQouDg)

# Funzionamento applicazione
## Avvio 
All'avvio dell'applicazione viene effettuata la decodifica del JSON dell'URL sopra indicato, contenente il link al dataset. Successivamente quest'ultimo viene scaricato, salvato nella directory e rappresentato tramite un'opportuna struttura dati.
***Nota***:il download del dataset avviene solo al primo avvio dell'applicazione.
## Richieste HTTP
Tramite richieste GET con rotte determinate il client può richiedere la restituzione di particolari dati o effettuare delle operazioni di filtraggio sul dataset.
Di seguito vengono specificate le rotte relative a ciascuna richiesta.
### Restituzione dati

 - Restituzione dei metadati in formato JSON:   
 > `/metadata`
 - Restituzione statistiche sui dati in formato JSON.
 Tale computazione è applicata solamente all'attributo *pc_schools*.
 Indicatori disponibili: somma, valore minimo, valore massimo, valore medio, deviazione standard.   

> `/sum`
>  `/min`
>  `/max`
>  `/avg`
>  `/stddev`
>  `/stats`
**Nota**: Quest'ultima richiesta prevede la restituzione dell'elenco di tutti gli indicatori.
 - Restituzione di tutti i dati in formato JSON:   
>  `/data`
 - Restituzione di un singolo dato in formato JSON:   
> `/data/{indice}`
 - Conteggio elementi unici per gli attributi di tipo Stringa. Per ogni elemento unico è indicato il numero di occorrenze.
> `/elementiunici/{fieldName}`

### Filtri 
Sono previste due tipologie di filtri: semplici e combinati.
#### Filtri semplici 

 - Restituzione di tutti gli elementi che presentano il valore dell'attributo fieldName uguale a value.

1. Per gli attributi di tipo Stringa

> `/filtroStringa/{field}/{value}`

 2. Per gli attributi numerici

> `/filtroValore/{field}/{value}`
> 
***Esempio:*** 
> /filtroStringa/ref_area/IT
> Restituisce tutti gli elementi con proprietà ref_area=IT;

- Restituzione di tutti gli elementi che presentano il valore dell'attributo  field ***gt*** o ***lt*** o ***eq*** rispetto value.
Con operator si specifica l'operatore condizionale scelto.

> `/filtroValore/{field}/{operator}/{value}`
> 
***Nota***: Applicabile solo ad attributi numerici.

#### Filtri combinati
- ***And*** o ***or*** di due richieste condizionali.

    

> `/filtroValore/{logicOperator}/{operator1}/{value1}/{operator2}/{value2}`


Ciascuna richiesta condizionale è definita da operatorX e valueX.
logicOperator specifica l'operatore d'insieme.
L'attributo al quale applicare la computazione è solo value, per ciò non necessita essere specificato.
***Esempio:***

> /filtroValore/and/gt/0.5/lt/0.7
> 
> Restituisce l'elenco degli elementi che presentano value>0.5 e
> value<0.7. L'elenco restituito non presenta duplicati.

- Restituzione di tutti gli elementi che presentano il valore di ref_area=value1 e quello di value  ***gt*** o ***lt*** o ***eq*** rispetto value2.

> `/filtro/{value1}/{operator2}/{value2}`
> 
***Esempio:***
> 
> /filtro/IT/lt/0.6
>
> Restituisce l'elenco degli elementi che presentano ref_area=IT e value<0.6.

 
 
# Diagrammi UML
## Diagramma delle classi

## Diagramma dei casi d'uso 


