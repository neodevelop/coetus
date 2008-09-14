<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<title>Coetus :: [<g:layoutTitle default="Grails" />]</title>
		<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'terrafirma.css')}" />
		<link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
		<script src="http://www.google.com/jsapi"></script>
		<script>
			// Load prototype
			google.load("prototype", "1.6.0.2");
		</script>
		<g:layoutHead />
	</head>
	<body>
		<div id="outer">
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
					<div id="date">
						<g:message code="welcome" default="Welcome" />
						<g:isLoggedIn><g:loggedInUserInfo field="userRealName" /> <avatar:gravatar email="${loggedInUserInfo(field:'email')}" size="25"/></g:isLoggedIn>
						<g:isNotLoggedIn><g:message code="guest" default="Guest" /></g:isNotLoggedIn>
					</div>
				</div>
				<div id="primarycontent">
					<util:checkUsers />
					
					<g:layoutBody />
				</div>
				<div id="secondarycontent">
					<events:currentEvents title="${message(code:'event.currents',default:'Currents Events')}" />
					<events:myEvents title="${message(code:'event.my',default:'My Events')}" />
					<!-- secondary content start -->
					<h3><g:meta name="app.name"/>-<g:meta name="app.version"/></h3>
					<div class="content">
						Download the  <a href="http://code.google.com/p/coetus/" target="_blank">complete source code</a> to Coetus. Contribute patches and enhancements! <br/>
						<script type="text/javascript" src="http://www.ohloh.net/projects/15794/widgets/project_thin_badge"></script>
					</div>
					<!-- secondary content start -->
					<h3>Acerca de Nosotros</h3>
					<div class="content">
						<img src="${createLinkTo(dir:'images',file:'banner.jpg')}" class="picB" alt="" />
						<p>
							Somos los fundadores del portal <strong><a target="_blank" href="http://www.springhispano.org">SpringHispano.org</a></strong>, nos gusta lo que hacemos: desarrollamos software y compartimos nuestras experiencias en este portal...
						</p>
					</div>
					<div style="padding-left: 80px;">
						<a href="http://www.grails.org/" target="_blank">
							<img src="${createLinkTo(dir:'images',file:'grails_button.gif')}" alt="Powered By Grails" border="0"/>
						</a>
					</div>
					<div style="padding-left: 80px;">
						<g:remoteLink controller="attendee" action="testAjax" update="message">Delete Book</g:remoteLink>
						<div id="message"></div>
					</div>
				</div>
				<div id="footer">
					&copy; .: <g:meta name="app.name"/>. <g:meta name="app.version"/> on <a href="http://grails.org" target="_blank">Grails</a> <g:meta name="app.grails.version"/> By SynergyJ.com - Design by <a target="_blank" href="http://www.nodethirtythree.com/">NodeThirtyThree</a> :.
				</div>
			</div>
		</div>
	
	</body>
</html>
