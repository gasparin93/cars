# cars
A quickie web service for car towing lookups. 

Behavior:
- If neither plate nor state supplied, server will return ALL values
- If plate is not supplied, server will return ALL values WHERE plate is empty
- If plate is supplied, server will return ALL values WHERE plate matches
- If state is supplied, server will return ALL values WHERE plate matches (including empty) AND state matches