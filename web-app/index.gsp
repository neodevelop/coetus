<html>
    <head>
        <title>Bienvenido a Coetus</title>
		<meta name="layout" content="main" />
    </head>
    <body>
        <h1 style="margin-left:20px;">Bienvenido a Coetus</h1>
		<br>
		<p style="margin-left:20px;width:80%">
		Este proyecto esta hecho por <a href="http://springhispano.org" target="_blank">SpringHispano.org</a>, aquí podrás registrarte a los eventos que hemos organizado para ti, además, si te gusta este proyecto podrás descargarlo directamente desde este <a href="">enlace</a>.
		</p>
		<p style="margin-left:20px;width:80%">
		Con Coetus, podrás administrar eventos, asignar roles de organización, permitir que el público se registre y este informado del status del evento, adicional a esto, podrás agregar las charlas o conferencias y a los ponentes que las impartan.
		</p>
		<p style="margin-left:20px;width:80%">
		Coetus, es un desarrollo Open Source hecho con Grails.
		</p>
        <div class="dialog" style="margin-left:20px;width:60%;">
            <ul>
              <g:each var="c" in="${grailsApplication.controllerClasses}">
                    <li class="controller"><g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link></li>
              </g:each>
            </ul>
        </div>
    </body>
</html>
