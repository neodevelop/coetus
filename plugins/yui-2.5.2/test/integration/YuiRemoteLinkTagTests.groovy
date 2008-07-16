import org.codehaus.groovy.grails.web.util.WebUtils
import org.springframework.web.context.request.RequestContextHolder

class YuiRemoteLinkTagTests extends grails.test.GroovyPagesTestCase {
    
    void testYuiRemoteLinkWithUpdate() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" update="success">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){YAHOO.util.Dom.get('success').innerHTML = o.responseText;}, failure: function(o){}}, null);return false;\">My Action</a>", template
    }
    
    void testYuiRemoteLinkWithUpdateMap() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" update="[success: \'success\', failure: \'error\']">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){YAHOO.util.Dom.get('success').innerHTML = o.responseText;}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;}}, null);return false;\">My Action</a>", template
    }

    void testYuiRemoteLinkWithBefore() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" before="before()">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"before();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);return false;\">My Action</a>", template
    }
    
    void testYuiRemoteLinkWithAfter() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" after="after()">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);after();return false;\">My Action</a>", template
    }
    
    void testYuiRemoteLinkWithOnSuccess() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" onSuccess="success()">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){success();}, failure: function(o){}}, null);return false;\">My Action</a>", template
    }
    
    void testYuiRemoteLinkWithOnFailure() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" onFailure="failure()">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){failure();}}, null);return false;\">My Action</a>", template
    }

    void testYuiRemoteLinkWithOnLoading() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" onLoading="loading()">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);return false;\">My Action</a>", template
    }
    
    void testYuiRemoteLinkWithOnLoaded() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" onLoaded="loaded()">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();}, failure: function(o){}}, null);return false;\">My Action</a>", template
    }

    void testYuiRemoteLinkWithOnComplete() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" onComplete="complete()">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){complete();}, failure: function(o){complete();}}, null);return false;\">My Action</a>", template
    }
    
    void testYuiRemoteLinkWithAllOnEvents() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();success();complete();}, failure: function(o){failure();complete();}}, null);return false;\">My Action</a>", template
    }

    void testYuiRemoteLinkWithAllOnEventsAndUpdate() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="success">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){failure();complete();}}, null);return false;\">My Action</a>", template
    }
    
    void testYuiRemoteLinkWithAllOnEventsAndUpdateMap() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="[success: \'success\', failure: \'error\']">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;failure();complete();}}, null);return false;\">My Action</a>", template
    }

    void testYuiRemoteLinkWithExtraAttributes() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" attr1="one" attr2="two">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);return false;\" attr1=\"one\" attr2=\"two\">My Action</a>", template
    }
    
    void testYuiRemoteLinkGetWithoutParams() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" method="GET">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);return false;\">My Action</a>", template
    }

    void testYuiRemoteLinkGetWithSingleParam() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" method="GET" params="[var1: \'one\']">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1?var1=one\" onclick=\"YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1?'+'var1=one', {success: function(o){}, failure: function(o){}}, null);return false;\">My Action</a>", template
    }
    
    void testYuiRemoteLinkGetWithMultipleParams() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" method="GET" params="[var1: \'one\', var2: \'two\']">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1?var1=one&var2=two\" onclick=\"YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1?'+'var1=one&var2=two', {success: function(o){}, failure: function(o){}}, null);return false;\">My Action</a>", template
    }
    
    void testYuiRemoteLinkPostWithoutParams() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" method="POST">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1\" onclick=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);return false;\">My Action</a>", template
    }

    void testYuiRemoteLinkPostWithSingleParam() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" method="POST" params="[var1: \'one\']">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1?var1=one\" onclick=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, 'var1=one');return false;\">My Action</a>", template
    }
    
    void testYuiRemoteLinkPostWithMultipleParams() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" method="POST" params="[var1: \'one\', var2: \'two\']">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1?var1=one&var2=two\" onclick=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, 'var1=one&var2=two');return false;\">My Action</a>", template
    }
    
    void testYuiRemoteLinkGetWithEverything() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" method="GET" params="[var1: \'one\', var2: \'two\']" before="before()" after="after()" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="[success: \'success\', failure: \'error\']" attr1="one" attr2="two">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1?var1=one&var2=two\" onclick=\"before();loading();YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1?'+'var1=one&var2=two', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;failure();complete();}}, null);after();return false;\" attr1=\"one\" attr2=\"two\">My Action</a>", template
    }
    
    void testYuiRemoteLinkPostWithEverything() {
        def template = '<g:remoteLink controller="mycontroller" action="myaction" id="1" method="POST" params="[var1: \'one\', var2: \'two\']" before="before()" after="after()" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="[success: \'success\', failure: \'error\']" attr1="one" attr2="two">My Action</g:remoteLink>'
        assertOutputEquals "<a href=\"/myapp/mycontroller/myaction/1?var1=one&var2=two\" onclick=\"before();loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;failure();complete();}}, 'var1=one&var2=two');after();return false;\" attr1=\"one\" attr2=\"two\">My Action</a>", template
    }
    
    void setUp() {
        RequestContextHolder.currentRequestAttributes().currentRequest."${WebUtils.INCLUDE_CONTEXT_PATH_ATTRIBUTE}" = '/myapp'
        RequestContextHolder.currentRequestAttributes().currentRequest."org.codehaus.grails.INCLUDED_JS_LIBRARIES" = ['yui']
    }
}
