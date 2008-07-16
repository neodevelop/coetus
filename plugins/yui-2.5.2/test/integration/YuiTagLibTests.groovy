import org.codehaus.groovy.grails.plugins.yui.Yui
import org.codehaus.groovy.grails.web.util.WebUtils
import org.springframework.web.context.request.RequestContextHolder

class YuiTagLibTests extends grails.test.GroovyPagesTestCase {

    void testYuiJavascriptTag() {
        def template = '<yui:javascript dir="calendar" file="calendar-min.js" />' 
        assertOutputEquals "<script type=\"text/javascript\" src=\"/myapp/js/yui/${Yui.version}/calendar/calendar-min.js\"></script>", template
    }

    void testYuiJavascriptTagWithVersion() {
        def template = '<yui:javascript dir="calendar" file="calendar-min.js" version="2.4.1" />' 
        assertOutputEquals "<script type=\"text/javascript\" src=\"/myapp/js/yui/2.4.1/calendar/calendar-min.js\"></script>", template
    }
    
    void testYuiStylesheetTag() {
        def template = '<yui:stylesheet dir="calendar/assets" file="calendar.css" />' 
        assertOutputEquals "<link rel=\"stylesheet\" type=\"text/css\" href=\"/myapp/js/yui/${Yui.version}/calendar/assets/calendar.css\" />", template
    }

    void testYuiStylesheetTagWithVersion() {
        def template = '<yui:stylesheet dir="calendar/assets" file="calendar.css" version="2.4.1" />' 
        assertOutputEquals "<link rel=\"stylesheet\" type=\"text/css\" href=\"/myapp/js/yui/2.4.1/calendar/assets/calendar.css\" />", template
    }
    
    void setUp() {
        RequestContextHolder.currentRequestAttributes().currentRequest."${WebUtils.INCLUDE_CONTEXT_PATH_ATTRIBUTE}" = '/myapp'        
    }
}
