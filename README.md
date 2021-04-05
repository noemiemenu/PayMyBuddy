# PayMyBuddy

Appli qui permet aux clients de transférer de l'argent pour gérer leurs finances ou payer leurs amis.

## Launch postgresql server on docker

```bash
docker run --name paymybuddy-postgres -p 5432:5432 -e POSTGRES_PASSWORD=paymybuddy -e POSTGRES_USER=paymybuddy -e POSTGRES_DB=paymybuddy -d postgres
```

