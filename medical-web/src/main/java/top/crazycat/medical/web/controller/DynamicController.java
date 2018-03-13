package top.crazycat.medical.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.crazycat.medical.web.engine.GroovyEngine;
import top.crazycat.medical.web.engine.GroovySourceUtil;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liyongbing
 * @date: 2018/1/19
 * description:
 */
@Controller
public class DynamicController {

    @Resource
    private GroovySourceUtil groovySourceUtil;

    @RequestMapping("/")
    public void index(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/md/index.htm").forward(request,response);
    }
    @RequestMapping("/favicon.ico")
    public void favicon(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/static/favicon.ico").forward(request,response);
    }



    @RequestMapping("/md/{cname}.htm")
    public String render(ModelMap modelMap, @PathVariable("cname") String cname, HttpServletRequest request, HttpServletResponse response){
        String link = cname;
        link = link.replaceAll("\\.","/");
        modelMap.mergeAttributes(renderHandler(link,request,response));
        return link;
    }

    @RequestMapping("/md/{cname}.ajax")
    @ResponseBody
    public Object ajaxRender( @PathVariable("cname") String cname, HttpServletRequest request, HttpServletResponse response){
        String link = cname;
        link = link.replaceAll("\\.","/");
        return renderHandler(link+"_ajax",request,response);
    }

    private Map<String, ?> renderHandler(String link, HttpServletRequest request, HttpServletResponse response) {
        Object rs = null;
        Reader script = null;
        try {
            script = groovySourceUtil.findSource(link+".groovy","utf-8");
            if(null == script){//无脚本兼容
                return new HashMap<>();
            }
            rs = GroovyEngine.execute(script,GroovyEngine.DEFAULT_FUNC,request,response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if(null != script){
                try {
                    script.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return (Map<String, ?>) rs;
    }
}
