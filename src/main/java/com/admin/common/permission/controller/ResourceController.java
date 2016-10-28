package com.admin.common.permission.controller;

import com.admin.common.page.PageInfo;
import com.admin.common.page.PageUtils;
import com.admin.common.permission.model.Operate;
import com.admin.common.permission.model.OperateType;
import com.admin.common.permission.model.Resource;
import com.admin.common.permission.service.ResourceService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: wy
 * Date: 12-9-7
 * Time: 下午2:28
 * 资源控制器
 */

@Controller
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("resourceQuery")
    public ModelAndView query(@RequestParam(value = "name", required = false, defaultValue = "") String resourceName,
                              @RequestParam(value = "url", required = false, defaultValue = "") String url,
                              @RequestParam(value = "type", required = false, defaultValue = "") String type,
                              @RequestParam(value = "pageIndex", required = false, defaultValue = "0") Integer pageIndex) {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setStartPageIndex(pageIndex);

        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("resourceName", resourceName);
        queryParams.put("url", url);
        queryParams.put("type", type);


        pageInfo = resourceService.query(pageInfo, queryParams);

        PageUtils.set(pageInfo, "resourceQuery.do", queryParams);

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("pageInfo", pageInfo);
        HashMap<String, Object> queryResourceParams = new HashMap<String, Object>();
        queryResourceParams.put("type", "1");//模块
        model.put("resourceInfos", resourceService.query(queryResourceParams));
        model.put("name", resourceName);
        model.put("url", url);
        model.put("type", type);

        return new ModelAndView("resource", model);
    }

    @RequestMapping(value = "resourceAdd", method = RequestMethod.POST)
    public ModelAndView add(@RequestParam("resourceName") String resourceName,
                            @RequestParam(value = "baseUrl", required = false, defaultValue = "") String baseUrl,
                            @RequestParam(value = "resourceUrl", required = false, defaultValue = "") String url,
                            @RequestParam("index") int index,
                            @RequestParam("type") String type,
                            @RequestParam(value = "parentId", required = false, defaultValue = "") Integer parentId,
                            @RequestParam(value = "operateType", required = false, defaultValue = "") OperateType[] operateTypes) {

        Resource resource = new Resource();

        if (!StringUtils.equals(type, "1") && null != operateTypes) {
            List<Operate> operates = new ArrayList<Operate>();
            for (OperateType operateType : operateTypes) {
                Operate operate = new Operate();
                operate.setOperateType(operateType);
                operates.add(operate);
            }
            resource.setOperates(operates);
        }

        if (!StringUtils.equals(type, "1")) {
            Resource parentResource = new Resource();
            parentResource.setId(parentId);
            resource.setParentResource(parentResource);
        } else {
            resource.setBaseUrl(baseUrl); //模块时才设置
        }


        resource.setResourceName(resourceName);
        resource.setType(type);
        resource.setUrl(url);
        resource.setIndex(index);
        String result = resourceService.add(resource);

        Map<String, Object> model = new HashMap<String, Object>();
        HashMap<String, Object> queryResourceParams = new HashMap<String, Object>();
        queryResourceParams.put("type", "1");//模块
        model.put("resourceInfos", resourceService.query(queryResourceParams));

        if (null == result) {
            //成功
            model.put("result", true);
            model.put("errorMsg", "新建资源成功");
        } else {
            model.put("result", false);
            model.put("errorMsg", result);
        }

        return new ModelAndView(new RedirectView("resourceQuery.do"), model);
    }

    @RequestMapping(value = "resourceEdit", method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam("editId") Integer id,
                             @RequestParam("editResourceName") String resourceName,
                             @RequestParam(value = "editBaseUrl", required = false, defaultValue = "") String baseUrl,
                             @RequestParam("editResourceUrl") String url,
                             @RequestParam("editIndex") Integer index,
                             @RequestParam(value = "editParentId", required = false, defaultValue = "") Integer parentId,
                             @RequestParam(value = "editOperateType", required = false, defaultValue = "") OperateType[] operateTypes) {
        Resource resource = new Resource();
        resource.setId(id);
        if (null != operateTypes) {
            List<Operate> operates = new ArrayList<Operate>();
            for (OperateType operateType : operateTypes) {
                Operate operate = new Operate();
                operate.setOperateType(operateType);
                operate.setResourceId(id);
                operates.add(operate);
            }
            resource.setOperates(operates);
        }
        resource.setBaseUrl(baseUrl);
        resource.setUrl(url);
        resource.setIndex(index);
        resource.setResourceName(resourceName);

        if (null != parentId && parentId.intValue() != id) {
            Resource parentResource = new Resource();
            parentResource.setId(parentId);
            resource.setParentResource(parentResource);
        }

        String result = resourceService.edit(resource);

        Map<String, Object> model = new HashMap<String, Object>();
        HashMap<String, Object> queryResourceParams = new HashMap<String, Object>();
        queryResourceParams.put("type", "1");//模块
        model.put("resourceInfos", resourceService.query(queryResourceParams));

        if (null == result) {
            //成功
            model.put("result", true);
            model.put("errorMsg", "修改资源成功");
        } else {
            model.put("result", false);
            model.put("errorMsg", result);
        }

        return new ModelAndView(new RedirectView("resourceQuery.do"), model);
    }


    @RequestMapping(value = "resourceDelete", method = RequestMethod.POST)
    public ModelAndView delete(@RequestParam("deleteId") Integer id) {
        Resource resource = new Resource();
        resource.setId(id);

        String result = resourceService.delete(resource);

        Map<String, Object> model = new HashMap<String, Object>();
        HashMap<String, Object> queryResourceParams = new HashMap<String, Object>();
        queryResourceParams.put("type", "1");//模块
        model.put("resourceInfos", resourceService.query(queryResourceParams));

        if (null == result) {
            //成功
            model.put("result", true);
            model.put("errorMsg", "删除资源成功");
        } else {
            model.put("result", false);
            model.put("errorMsg", result);
        }

        return new ModelAndView(new RedirectView("resourceQuery.do"), model);
    }

    @RequestMapping("getMenuInfo")
    @ResponseBody
    public Object queryResource(@RequestParam("moduleId") String moduleId) {
        HashMap<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("parentId", moduleId);
        queryParams.put("type", "2");
        List<Resource> resources = resourceService.query(queryParams);

        return resources;
    }
}
