import org.codehaus.groovy.grails.web.util.WebUtils
import org.springframework.web.context.request.RequestContextHolder

class YuiRemoteFunctionTagTests extends grails.test.GroovyPagesTestCase {

    void testYuiRemoteFunctionWithUpdate() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" update="success">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){YAHOO.util.Dom.get('success').innerHTML = o.responseText;}, failure: function(o){}}, null);", template
    }
    
    void testYuiRemoteFunctionWithUpdateMap() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" update="[success: \'success\', failure: \'error\']">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){YAHOO.util.Dom.get('success').innerHTML = o.responseText;}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;}}, null);", template
    }

    void testYuiRemoteFunctionWithBefore() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" before="before()">My Action</g:remoteFunction>'
        assertOutputEquals "before();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);", template
    }
    
    void testYuiRemoteFunctionWithAfter() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" after="after()">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);after();", template
    }
    
    void testYuiRemoteFunctionWithOnSuccess() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" onSuccess="success()">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){success();}, failure: function(o){}}, null);", template
    }
    
    void testYuiRemoteFunctionWithOnFailure() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" onFailure="failure()">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){failure();}}, null);", template
    }

    void testYuiRemoteFunctionWithOnLoading() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" onLoading="loading()">My Action</g:remoteFunction>'
        assertOutputEquals "loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);", template
    }
    
    void testYuiRemoteFunctionWithOnLoaded() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" onLoaded="loaded()">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();}, failure: function(o){}}, null);", template
    }

    void testYuiRemoteFunctionWithOnComplete() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" onComplete="complete()">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){complete();}, failure: function(o){complete();}}, null);", template
    }
    
    void testYuiRemoteFunctionWithAllOnEvents() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()">My Action</g:remoteFunction>'
        assertOutputEquals "loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();success();complete();}, failure: function(o){failure();complete();}}, null);", template
    }

    void testYuiRemoteFunctionWithAllOnEventsAndUpdate() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="success">My Action</g:remoteFunction>'
        assertOutputEquals "loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){failure();complete();}}, null);", template
    }
    
    void testYuiRemoteFunctionWithAllOnEventsAndUpdateMap() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="[success: \'success\', failure: \'error\']">My Action</g:remoteFunction>'
        assertOutputEquals "loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;failure();complete();}}, null);", template
    }

    void testYuiRemoteFunctionWithExtraAttributes() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" attr1="one" attr2="two">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);", template
    }
    
    void testYuiRemoteFunctionGetWithoutParams() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" method="GET">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);", template
    }

    void testYuiRemoteFunctionGetWithSingleParam() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" method="GET" params="[var1: \'one\']">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1?'+'var1=one', {success: function(o){}, failure: function(o){}}, null);", template
    }
    
    void testYuiRemoteFunctionGetWithMultipleParams() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" method="GET" params="[var1: \'one\', var2: \'two\']">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1?'+'var1=one&var2=two', {success: function(o){}, failure: function(o){}}, null);", template
    }
    
    void testYuiRemoteFunctionPostWithoutParams() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" method="POST">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);", template
    }

    void testYuiRemoteFunctionPostWithSingleParam() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" method="POST" params="[var1: \'one\']">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, 'var1=one');", template
    }
    
    void testYuiRemoteFunctionPostWithMultipleParams() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" method="POST" params="[var1: \'one\', var2: \'two\']">My Action</g:remoteFunction>'
        assertOutputEquals "YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, 'var1=one&var2=two');", template
    }
    
    void testYuiRemoteFunctionGetWithEverything() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" method="GET" params="[var1: \'one\', var2: \'two\']" before="before()" after="after()" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="[success: \'success\', failure: \'error\']" attr1="one" attr2="two">My Action</g:remoteFunction>'
        assertOutputEquals "before();loading();YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1?'+'var1=one&var2=two', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;failure();complete();}}, null);after();", template
    }
    
    void testYuiRemoteFunctionPostWithEverything() {
        def template = '<g:remoteFunction controller="mycontroller" action="myaction" id="1" method="POST" params="[var1: \'one\', var2: \'two\']" before="before()" after="after()" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="[success: \'success\', failure: \'error\']" attr1="one" attr2="two">My Action</g:remoteFunction>'
        assertOutputEquals "before();loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;failure();complete();}}, 'var1=one&var2=two');after();", template
    }
    
    void setUp() {
        RequestContextHolder.currentRequestAttributes().currentRequest."${WebUtils.INCLUDE_CONTEXT_PATH_ATTRIBUTE}" = '/myapp'
        RequestContextHolder.currentRequestAttributes().currentRequest."org.codehaus.grails.INCLUDED_JS_LIBRARIES" = ['yui']
    }
}
