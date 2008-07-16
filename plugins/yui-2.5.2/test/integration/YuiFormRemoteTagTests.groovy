import org.codehaus.groovy.grails.web.util.WebUtils
import org.springframework.web.context.request.RequestContextHolder

class YuiFormRemoteTagTests extends grails.test.GroovyPagesTestCase {

    void testYuiFormRemoteWithUpdate() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" update="success" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){YAHOO.util.Dom.get('success').innerHTML = o.responseText;}, failure: function(o){}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }
    
    void testYuiFormRemoteWithUpdateMap() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" update="[success: \'success\', failure: \'error\']" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){YAHOO.util.Dom.get('success').innerHTML = o.responseText;}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }

    void testYuiFormRemoteWithBefore() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" before="before()" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"before();YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }
    
    void testYuiFormRemoteWithAfter() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" after="after()" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);after();return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }
    
    void testYuiFormRemoteWithOnSuccess() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" onSuccess="success()" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){success();}, failure: function(o){}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }
    
    void testYuiFormRemoteWithOnFailure() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" onFailure="failure()" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){failure();}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }

    void testYuiFormRemoteWithOnLoading() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" onLoading="loading()" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }
    
    void testYuiFormRemoteWithOnLoaded() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" onLoaded="loaded()" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();}, failure: function(o){}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }

    void testYuiFormRemoteWithOnComplete() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" onComplete="complete()" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){complete();}, failure: function(o){complete();}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }
    
    void testYuiFormRemoteWithAllOnEvents() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();success();complete();}, failure: function(o){failure();complete();}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }

    void testYuiFormRemoteWithAllOnEventsAndUpdate() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="success" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){failure();complete();}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }
    
    void testYuiFormRemoteWithAllOnEventsAndUpdateMap() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="[success: \'success\', failure: \'error\']" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;failure();complete();}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }

    void testYuiFormRemoteWithExtraAttributes() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" attr1="one" attr2="two" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" attr1=\"one\" attr2=\"two\" name=\"myform\" id=\"myform\"></form>", template
    }
    
    void testYuiFormRemoteGetWithoutParams() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" method="GET" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }

    void testYuiFormRemoteGetWithSingleParam() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" method="GET" params="[var1: \'one\']" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1?'+'var1=one', {success: function(o){}, failure: function(o){}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template        
    }    
    
    void testYuiFormRemoteGetWithMultipleParams() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" method="GET" params="[var1: \'one\', var2: \'two\']" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1?'+'var1=one&var2=two', {success: function(o){}, failure: function(o){}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template        
    }
    
    void testYuiFormRemotePostWithoutParams() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" method="POST" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, null);return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template
    }
    
    void testYuiFormRemotePostWithSingleParam() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" method="POST" params="[var1: \'one\']" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, 'var1=one');return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template        
    }
    
    void testYuiFormRemotePostWithMultipleParams() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" method="POST" params="[var1: \'one\', var2: \'two\']" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"YAHOO.util.Connect.setForm('myform');YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, 'var1=one&var2=two');return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" name=\"myform\" id=\"myform\"></form>", template        
    }
    
    void testYuiFormRemoteGetWithEverything() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" method="GET" params="[var1: \'one\', var2: \'two\']" before="before()" after="after()" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="[success: \'success\', failure: \'error\']" attr1="one" attr2="two" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"before();YAHOO.util.Connect.setForm('myform');loading();YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1?'+'var1=one&var2=two', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;failure();complete();}}, null);after();return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" attr1=\"one\" attr2=\"two\" name=\"myform\" id=\"myform\"></form>", template
    }

    void testYuiFormRemotePostWithEverything() {
        def template = '<g:formRemote url="[controller: \'mycontroller\', action: \'myaction\', id: 1]" method="POST" params="[var1: \'one\', var2: \'two\']" before="before()" after="after()" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="[success: \'success\', failure: \'error\']" attr1="one" attr2="two" name="myform"/>'
        assertOutputEquals "<form onsubmit=\"before();YAHOO.util.Connect.setForm('myform');loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;failure();complete();}}, 'var1=one&var2=two');after();return false\" method=\"POST\" action=\"/myapp/mycontroller/myaction/1\" attr1=\"one\" attr2=\"two\" name=\"myform\" id=\"myform\"></form>", template
    }
    
    void setUp() {
        RequestContextHolder.currentRequestAttributes().currentRequest."${WebUtils.INCLUDE_CONTEXT_PATH_ATTRIBUTE}" = '/myapp'
        RequestContextHolder.currentRequestAttributes().currentRequest."org.codehaus.grails.INCLUDED_JS_LIBRARIES" = ['yui']
    }
}
