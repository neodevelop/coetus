<html>
	<head>
		<title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'terrafirma.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
	</head>
	<body>

		<div id="outer">
			<div id="upbg"></div>
				<div id="inner">
					<div id="header">
						<h1><span>coetus</span><sup>1.0</sup></h1>
						<h2>by synergyj.com</h2>
					</div>
					<div id="splash"></div>

					<div id="menu">
						<ul>
							<li class="first"><a class="home" href="${createLinkTo(dir:'')}"><g:message code="home" default="Home" /></a></li>
							<li><a href="#">Registro</a></li>
							<li><a href="#">Recomienda</a></li>
							<li><a href="#">Resumen</a></li>
							<li><a href="#">Contactanos</a></li>
						</ul>
					<div id="date">Bienvenido</div>
					</div>

					<div id="primarycontent">
						<g:layoutBody />
					</div>
					
					<div id="secondarycontent">

						<!-- secondary content start -->
		
						<h3>Acerca de Nosotros</h3>
						<div class="content">
							<img src="${createLinkTo(dir:'images',file:'banner.jpg')}" class="picB" alt="" />
							<p>Somos los fundadores del portal <strong><a target="_blank" href="http://www.springhispano.org">SpringHispano.org</a></strong>, nos gusta lo que hacemos: desarrollamos software y compartimos nuestras experiencias en este portal...
							</p>
						</div>
			
						<h3>Controllers</h3>
						<div class="content">
							<ul class="linklist">
							</ul>
						</div>

						<!-- secondary content end -->

					</div>


					<div id="footer">
						&copy; .: Coetus. By SynergyJ.com - Design by <a target="_blank" href="http://www.nodethirtythree.com/">NodeThirtyThree</a> :.
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
