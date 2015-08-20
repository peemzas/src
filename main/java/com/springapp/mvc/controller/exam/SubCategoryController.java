package com.springapp.mvc.controller.exam;

import com.google.gson.Gson;
import com.springapp.mvc.domain.QueryUserDomain;
import com.springapp.mvc.domain.exam.QueryCategoryDomain;
import com.springapp.mvc.domain.exam.QuerySubCategoryDomain;
import com.springapp.mvc.pojo.User;
import com.springapp.mvc.pojo.exam.Category;
import com.springapp.mvc.pojo.exam.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Phuthikorn_T on 8/7/2015.
 */
@Controller
@RequestMapping("/TDCS")
public class SubCategoryController {

    @Autowired
    QueryUserDomain queryUserDomain;

    @Autowired
    QuerySubCategoryDomain querySubCategoryDomain;

    @Autowired
    QueryCategoryDomain queryCategoryDomain;


    @RequestMapping(method = RequestMethod.POST, value = "/exam/addSubCategory")
    @ResponseBody
//
//    public void  addSubCategory(ModelMap model, @Valid SubCategory subCategory
//            ,HttpServletRequest request, HttpServletResponse response){
//
//        User createBy = queryUserDomain.getCurrentUser(request);
//        subCategory.setCreateBy(createBy);
//
//        querySubCategoryDomain.insertSubCategory(subCategory);

    //  ----------------------------------------
    public void addSubCategory(Model model,
                               @RequestParam(value = "categoryId", required = true) String categoryId,
                               @RequestParam(value = "subcategoryName", required = true) String subcategoryName,
                               HttpServletRequest request, HttpServletResponse response) {
//            throws Exception {

        SubCategory subCategory = new SubCategory();

        subCategory.setName(subcategoryName);
        subCategory.setCategory(queryCategoryDomain.getCategoryById(categoryId));

        User currentUser = queryUserDomain.getCurrentUser(request);

        subCategory.setCreateBy(currentUser);

        querySubCategoryDomain.insertSubCategory(subCategory);
    }

    //end add
    @RequestMapping(value = "/exam/getAllSubCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> getAllSubCategory() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json;charset=UTF-8");

        List<SubCategory> subcategories = querySubCategoryDomain.getListSubCategories();
        String json = new Gson().toJson(subcategories);

        return new ResponseEntity<String>(json, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/exam/deleteSubCategory", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity deleteSubCategory(@ModelAttribute("id") Integer subCategoryId) {

        querySubCategoryDomain.deleteSubCategory(subCategoryId);


        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/exam/editSubCategory", method = RequestMethod.POST)
    @ResponseBody
//    public ResponseEntity editSubCategory(@Valid SubCategory subCategory,HttpServletRequest request){
//
//        HttpHeaders headers = new HttpHeaders();
//
//        headers.add("Content-Type", "application/json;charset=UTF-8");
//        User createBy = queryUserDomain.getCurrentUser(request);
//        subCategory.setCreateBy(createBy);
//        querySubCategoryDomain.editSubCategory(subCategory);
//
//
//        return new ResponseEntity(headers, HttpStatus.OK);
//    }


    public void editSubCategory(Model model,
                                @RequestParam(value = "categoryId", required = true) String categoryId,
                                @RequestParam(value = "subcategoryName", required = true) String subcategoryName,
                                HttpServletRequest request, HttpServletResponse response) {

        SubCategory subCategory = new SubCategory();

        subCategory.setName(subcategoryName);
        subCategory.setCategory(queryCategoryDomain.getCategoryById(categoryId));

        User currentUser = queryUserDomain.getCurrentUser(request);

        subCategory.setCreateBy(currentUser);

        querySubCategoryDomain.editSubCategory(subCategory);


    }
}