<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wanchana
  Date: 15/7/2558
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<script>--%>

    <%--$('#viewControlSelector').on('change', function () {--%>

        <%--alert('its WORKING !!!');--%>

        <%--if (this.value == 'Category') {--%>

            <%--$("#tblCategory").show();--%>
            <%--$("#tblSubCategory").hide();--%>
        <%--} else if (this.value == 'SubCategory') {--%>
            <%--$("#tblCategory").hide();--%>
            <%--$("#tblSubCategory").show();--%>
        <%--} else {--%>
            <%--alert('No God Please No');--%>
        <%--}--%>
    <%--})--%>
<%--</script>--%>

<div class="container row">
    <h3>รายวิชา</h3>
    <hr>


    <%--<div class="row">--%>
    <%--<div class="col-md-3 col-lg-offset-4" align="center" style="margin-top: 20px;">--%>
    <%--<label><h4>แสดง : วิชา</h4></label>--%>
    <%--</div>--%>
</div>


<div class="container">
    <div class="row">
        <div class="panel-collapse" id="searchCollapse">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h4>ค้นหา...</h4>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-2 col-lg-offset-2 text-right">
                            <label>รหัสวิชา :</label>
                        </div>
                        <div class="col-md-1" style="width: 10%">
                            <input class="form-control" type="text"/>
                        </div>

                        <div class="col-md-1 text-right">
                            <label>วิชา :</label>
                        </div>
                        <div class="col-md-4" >
                            <input class="form-control" type="text"/>
                        </div>





                    </div>
                    <br/>

                    <div class="row">
                        <%--<div class="col-md-2 text-right">--%>
                            <%--<label>ชื่อวิชา</label>--%>
                        <%--</div>--%>
                        <%--<div class="col-md-6">--%>
                            <%--<div class="input-group">--%>
                                <%--<input class="form-control" type="text"/>--%>

                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                    <hr/>

                    <div class="row" id="btnSearch">
                        <div class="col-md-12 text-center">
                            <button class="btn btn-default" type="button">ค้นหา</button>
                            <button class="btn btn-default" type="button">ล้างข้อมูล</button>
                        </div>
                    </div>
                </div>
            </div>


            <%--<div class="col-md-4" align="left" style="margin-top: 20px;">--%>
            <%--<select class="form-control" id="viewControlSelector">--%>
            <%--<option id = "selectCategory" value="Category" selected>วิชา</option>--%>
            <%--<option id = "selectSubCategory" value="SubCategory" >หัวข้อเรื่อง</option>--%>
            <%--</select>--%>
            <%--</div>--%>
        </div>
        <br/>
        <br/>




            <button data-toggle="modal" data-target="#createCat" class="btn btn-success" align="center"
                    style="width:50px;height:35px"> +
            </button>



        <%--<div class="input-group-btn">--%>
            <%--<button class="btn btn-success" data-toggle="modal" data-target=#createCat">--%>
                <%----%>
            <%--</button>--%>
        <%--</div>--%>

        <div class="row">
            <div class="col-md-12">
                <table class="table table-bordered" id="tblCategory">
                    <col width="20%"/>
                    <col width="60%"/>
                    <col width="20%"/>
                    <thead class="bg-primary">
                    <tr>
                        <th style="text-align: center; color: white;">รหัสวิชา</th>
                        <th style="text-align: center; color: white;">วิชา</th>
                        <th style="text-align: center; color: white">แอคชั่น</th>
                    </tr>
                    </thead>
                    <tbody id="tbodyCategory">


                    <%--<c:forEach var="category" items="${LIST_OF_CATEOGRIES}">--%>
                        <%--<tr>--%>
                            <%--<td>${category.id}</td>--%>
                            <%--<td>${category.name}</td>--%>
                            <%--<td style="text-align: center">--%>
                                <%--<button type="button" class="btn btn-warning"><span--%>
                                        <%--class="glyphicon glyphicon-pencil"></span>--%>
                                <%--</button>--%>
                                <%--<button type="button" class="btn btn-danger"><span--%>
                                        <%--class="glyphicon glyphicon-trash"></span>--%>
                                <%--</button>--%>
                            <%--</td>--%>
                        <%--</tr>--%>
                    <%--</c:forEach>--%>

                    </tbody>
                </table>


            </div>
        </div>


        <%--<script>--%>

            <%--$("#tblSubCategory").hide();--%>

            <%--$('#viewControlSelector').on('change', function () {--%>
                <%--if (this.value == 'Category') {--%>
                    <%--$("#tblCategory").show();--%>
                    <%--$("#tblSubCategory").hide();--%>
                <%--} else if (this.value == 'SubCategory') {--%>
                    <%--$("#tblCategory").hide();--%>
                    <%--$("#tblSubCategory").show();--%>
                <%--} else {--%>
                    <%--alert('No God Please No');--%>
                <%--}--%>
            <%--})--%>
        <%--</script>--%>

<%@include file="modal/createCategoryModal.jsp"%>
<spring:url value="../../../resources/js/pageScript/exam/manageCategory.js" ></spring:url>