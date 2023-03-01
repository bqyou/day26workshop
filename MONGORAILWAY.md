IMPORT INTO RAILWAYMONGO

1. Get uri string from railway
2. Connection uri string = {RAILWAYURL}/?directConnection=true&appName=mongosh+1.6.0&authSource=admin
3. set local variable with "{uri string}" include the ""
4. mongoimport --uri %localVariable% -d databasename -c collectionname --file filepath --jsonArray(if its an array) --drop
5. for connection between app and railway mongo, use only the railway url to import as variable etc set MONGO_URL=RAILWAYURI, variable name for repo will be MONGO_URL
