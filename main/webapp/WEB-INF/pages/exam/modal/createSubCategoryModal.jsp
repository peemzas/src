<%--
  Created by IntelliJ IDEA.
  User: PTang_000
  Date: 8/6/2015
  Time: 11:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="modal fade" id="createSub">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                aria-hidden="true">&times;</span></button>
        <h3 class="modal-title" align="center">เพิ่มหัวข้อเรื่อง</h3>
      </div>
      <div class="modal-body">
        <div class="row form-group">
          <div class="col-md-4" align="right">
            <h4><label class="label"
                       style="color: black ; font-weight: 100">เลือกวิชา
              :</label></h4>
          </div>
          <div class="input-group col-md-5">
            <input type="text" class="form-control" name="cat" style="width: 245px"/>
            <span class="input-group-addon" id="search"><a class="glyphicon glyphicon-search" href="#"></a></span>
          </div>
        </div>
        <div class="row form-group">
          <div class="col-md-4" align="right">
            <h4><label class="label"
                       style="color:black;font-weight: 100">หัวข้อเรื่อง
              :</label></h4>
          </div>
          <div class="col-md-6" style="padding-left: 0px">
            <input type="text" class="form-control" name="subcat"/>
          </div>
          <button class="btn btn-primary" type="submit" data-dismiss="modal">ตกลง</button>
        </div>
      </div>
    </div>
    <!-- /.modal-content -->
  </div>
  <!-- /.modal-dialog -->
</div>
<!-- /.modal -->