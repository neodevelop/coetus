import org.codehaus.groovy.grails.web.util.WebUtils
import org.springframework.web.context.request.RequestContextHolder

class YuiRemoteFieldTagTests extends grails.test.GroovyPagesTestCase {

    void testYuiRemoteFieldWithUpdate() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" update="success" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){YAHOO.util.Dom.get('success').innerHTML = o.responseText;}, failure: function(o){}}, 'value='+this.value);\" />", template
    }
    
    void testYuiRemoteFieldWithUpdateMap() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" update="[success: \'success\', failure: \'error\']" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){YAHOO.util.Dom.get('success').innerHTML = o.responseText;}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;}}, 'value='+this.value);\" />", template
    }

    void testYuiRemoteFieldWithBefore() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" before="before()" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"before();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, 'value='+this.value);\" />", template
    }
    
    void testYuiRemoteFieldWithAfter() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" after="after()" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, 'value='+this.value);after();\" />", template
    }
    
    void testYuiRemoteFieldWithOnSuccess() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" onSuccess="success()" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){success();}, failure: function(o){}}, 'value='+this.value);\" />", template
    }
    
    void testYuiRemoteFieldWithOnFailure() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" onFailure="failure()" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){failure();}}, 'value='+this.value);\" />", template
    }

    void testYuiRemoteFieldWithOnLoading() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" onLoading="loading()" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, 'value='+this.value);\" />", template
    }
    
    void testYuiRemoteFieldWithOnLoaded() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" onLoaded="loaded()" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();}, failure: function(o){}}, 'value='+this.value);\" />", template
    }

    void testYuiRemoteFieldWithOnComplete() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" onComplete="complete()" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){complete();}, failure: function(o){complete();}}, 'value='+this.value);\" />", template
    }
    
    void testYuiRemoteFieldWithAllOnEvents() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();success();complete();}, failure: function(o){failure();complete();}}, 'value='+this.value);\" />", template
    }

    void testYuiRemoteFieldWithAllOnEventsAndUpdate() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="success" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){failure();complete();}}, 'value='+this.value);\" />", template
    }
    
    void testYuiRemoteFieldWithAllOnEventsAndUpdateMap() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="[success: \'success\', failure: \'error\']" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;failure();complete();}}, 'value='+this.value);\" />", template
    }

    void testYuiRemoteFieldWithExtraAttributes() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" attr1="one" attr2="two" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, 'value='+this.value);\" attr1=\"one\" attr2=\"two\" />", template
    }
    
    void testYuiRemoteFieldGetWithoutParams() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" method="GET" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1?'+'value='+this.value, {success: function(o){}, failure: function(o){}}, null);\" />", template
    }

    void testYuiRemoteFieldGetWithSingleParam() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" method="GET" params="[var1: \'one\']" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1?'+'value='+this.value+'&var1=one', {success: function(o){}, failure: function(o){}}, null);\" />", template
    }
    
    void testYuiRemoteFieldGetWithMultipleParams() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" method="GET" params="[var1: \'one\', var2: \'two\']" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1?'+'value='+this.value+'&var1=one&var2=two', {success: function(o){}, failure: function(o){}}, null);\" />", template
    }
    
    void testYuiRemoteFieldPostWithoutParams() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" method="POST" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, 'value='+this.value);\" />", template
    }

    void testYuiRemoteFieldPostWithSingleParam() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" method="POST" params="[var1: \'one\']" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, 'value='+this.value+'&var1=one');\" />", template
    }
    
    void testYuiRemoteFieldPostWithMultipleParams() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" method="POST" params="[var1: \'one\', var2: \'two\']" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){}, failure: function(o){}}, 'value='+this.value+'&var1=one&var2=two');\" />", template
    }
    
    void testYuiRemoteFieldGetWithEverything() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" method="GET" params="[var1: \'one\', var2: \'two\']" before="before()" after="after()" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="[success: \'success\', failure: \'error\']" attr1="one" attr2="two" paramName="myparam" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"before();loading();YAHOO.util.Connect.asyncRequest('GET', '/myapp/mycontroller/myaction/1?'+'myparam='+this.value+'&var1=one&var2=two', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;failure();complete();}}, null);after();\" attr1=\"one\" attr2=\"two\" />", template
    }

    void testYuiRemoteFieldPostWithEverything() {
        def template = '<g:remoteField controller="mycontroller" action="myaction" id="1" method="POST" params="[var1: \'one\', var2: \'two\']" before="before()" after="after()" onSuccess="success()" onFailure="failure()" onLoading="loading()" onLoaded="loaded()" onComplete="complete()" update="[success: \'success\', failure: \'error\']" attr1="one" attr2="two" paramName="myparam" name="myname" value="myvalue"/>'
        assertOutputEquals "<input type=\"text\" name=\"myname\" value=\"myvalue\" onkeyup=\"before();loading();YAHOO.util.Connect.asyncRequest('POST', '/myapp/mycontroller/myaction/1', {success: function(o){loaded();YAHOO.util.Dom.get('success').innerHTML = o.responseText;success();complete();}, failure: function(o){YAHOO.util.Dom.get('error').innerHTML = o.statusText;failure();complete();}}, 'myparam='+this.value+'&var1=one&var2=two');after();\" attr1=\"one\" attr2=\"two\" />", template
    }
    
    void setUp() {
        RequestContextHolder.currentRequestAttributes().currentRequest."${WebUtils.INCLUDE_CONTEXT_PATH_ATTRIBUTE}" = '/myapp'
        RequestContextHolder.currentRequestAttributes().currentRequest."org.codehaus.grails.INCLUDED_JS_LIBRARIES" = ['yui']
    }
}
