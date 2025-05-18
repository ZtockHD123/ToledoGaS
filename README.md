# Credenciales para pedir el token de acceso son: 
## correo -> juani@yopmail.com contraseña -> 1qaz2wsx

- Si se pide manualmente el token por la powershell seguir los siguientes pasos y comandos:

1° $t = invoke-WebRequest "https://api.cne.cl/api/login?email=EMAIL&password=PASSWORD" -Method POST  //Aqui tendras asignado el token a la variable t dentro de powershell

2° $t.content | Set-clipboard  //Aqui habras copiado en el portapapeles el token, tendras algo así como vv

{"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOi8vYXBpLmNuZS"} lo que interesa es toodo el codigo alfanumerico (le recorté como 30 caracteres al ejemplo)

------El token tiene una duracion de 2 horas------
