import org.codehaus.groovy.grails.plugins.web.taglib.JavascriptTagLib
import org.codehaus.groovy.grails.plugins.yui.Yui
import org.codehaus.groovy.grails.web.util.WebUtils
import org.springframework.web.context.request.RequestContextHolder

class YuiJavascriptTagTests extends grails.test.GroovyPagesTestCase {

    void testYuiJavascript() {
        def taglib = new JavascriptTagLib()
        def result = taglib.javascript(library: 'yui')
        assertEquals("<script type=\"text/javascript\" src=\"/myapp/js/yui/${Yui.version}/yahoo-dom-event/yahoo-dom-event.js\"></script>\n" +
                "<script type=\"text/javascript\" src=\"/myapp/js/yui/${Yui.version}/connection/connection-min.js\"></script>\n", fixEOLs(result))
    }

    void setUp() {
        RequestContextHolder.currentRequestAttributes().currentRequest."${WebUtils.INCLUDE_CONTEXT_PATH_ATTRIBUTE}" = '/myapp'        
    }
}
