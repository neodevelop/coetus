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
						<h1><span><g:meta name="app.name"/></span><sup><g:meta name="app.version"/></sup></h1>
						<h2>by synergyj.com</h2>
					</div>
					<div id="splash"></div>

					<div id="menu">
						<ul>
							<li class="first"><a class="home" href="${createLinkTo(dir:'')}"><g:message code="home" default="Home" /></a></li>
							<g:isNotLoggedIn><li><a href="<g:createLink controller="register" />"><g:message code="signin" default="Sign in" /></a></li></g:isNotLoggedIn>
							<g:isNotLoggedIn><li><a href="<g:createLink controller="login" />"><g:message code="login" default="Login" /></a></li></g:isNotLoggedIn>
							
							<g:isLoggedIn><li><a href="<g:createLink controller="logout" />"><g:message code="logout" default="logout" /></a></li></g:isLoggedIn>
						</ul>
					<div id="date"><g:message code="welcome" default="Welcome" /> <g:loggedInUserInfo field="userRealName"><g:message code="guest" default="Guest" /></g:loggedInUserInfo></div>
					</div>

					<div id="primarycontent">
						<g:layoutBody />
					</div>
					
					<div id="secondarycontent">

						<g:currentEvents title="${message(code:'event.currents',default:'Currents Events')}" />
						
						<g:myEvents title="${message(code:'event.my',default:'My Events')}" />

						<!-- secondary content start -->
						<h3><g:meta name="app.name"/>-<g:meta name="app.version"/></h3>
						<div class="content">
							Download the 
							<a href="http://code.google.com/p/coetus/" target="_blank">complete source code</a>
							to Coetus. Contribute patches and enhancements!
						</div>
						
						<!-- secondary content start -->
						<h3>Acerca de Nosotros</h3>
						<div class="content">
							<img src="${createLinkTo(dir:'images',file:'banner.jpg')}" class="picB" alt="" />
							<p>Somos los fundadores del portal <strong><a target="_blank" href="http://www.springhispano.org">SpringHispano.org</a></strong>, nos gusta lo que hacemos: desarrollamos software y compartimos nuestras experiencias en este portal...
							</p>
						</div>
						
						<div style="padding-left: 80px;">
			      			<a href="http://www.grails.org/" target="_blank">
			      				<img src="${createLinkTo(dir:'images',file:'grails_button.gif')}" alt="Powered By Grails" border="0"/>
			      			</a>
			      		</div>
						
						
						

					</div>

					<div id="footer">
						&copy; .: <g:meta name="app.name"/>. <g:meta name="app.version"/> on Grails <g:meta name="app.grails.version"/> By SynergyJ.com - Design by <a target="_blank" href="http://www.nodethirtythree.com/">NodeThirtyThree</a> :.
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
