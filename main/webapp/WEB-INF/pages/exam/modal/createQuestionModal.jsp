<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--Create Question Modal-->


<div class="modal fade" id="createQuest">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h3 class="modal-title" align="center" id="createQuestModalTitle">สร้างข้อสอบ</h3>
            </div>
            <div class="modal-body" id="modalBody">
                <div class="row form-group">
                    <div class="col-md-4" align="right">
                        <h5><label style=" font-weight: 100">วิชา :</label></h5>
                    </div>
                    <div class="input-group col-md-5">
                        <input type="text" class="form-control" name="cat" id="categoryInputForCreateQuestion"/>
                        <span class="input-group-addon" id="selectCat"><i class="glyphicon glyphicon-search"></i></span>
                    </div>
                </div>

                <div class="row form-group">
                    <div class="col-md-4" align="right">
                        <h5><label style="font-weight: 100">หัวข้อเรื่อง :</label></h5>
                    </div>
                    <div class="input-group col-md-5">
                        <input type="text" class="form-control" name="subcat" id="subCategoryInputForCreateQuestion"/>
                        <span class="input-group-addon" id="selectSubCat"><i
                                class="glyphicon glyphicon-search"></i></span>
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-md-4" align="right">
                        <h5><label style="font-weight: 100">ประเภทข้อสอบ :</label></h5>
                    </div>
                    <div class="input-group col-md-5">
                        <select class="form-control" id="select-QuestionType">
                            <option disabled selected></option>
                            <option id="obj" value="Objective">ปรนัย</option>
                            <option id="sub" value="Subjective">อัตนัย</option>
                        </select>
                    </div>
                    <br>

                    <div class="modal-footer" id="question">

                        <div class="row form-group" id="questionContainer">
                            <div class="col-md-4">
                                <h5><label style="font-weight: 100">คำถาม :</label></h5>
                            </div>
                            <div class="col-md-5" style="padding: 0px">
                                <textarea id="questionDescription" class="form-control" style="resize: none"></textarea>
                            </div>
                        </div>
                        <div class="row form-group form-inline text-left" id="difficultyContainer" style="padding: 0;">
                            <div class="col-md-4 text-right">
                                <h5>ระดับความยาก :</h5>
                            </div>
                            <div class="col-md-3" align="left">
                                <div class="form-inline">
                                    <label><input class="form-control" type="radio" name="level" value="3"
                                                  style="box-shadow: none;"><h5 style="display: inline;">ยาก </h5>
                                    </label>
                                    <label style="margin-left: 10px;"><input class="form-control" type="radio"
                                                                             name="level" value="2"
                                                                             style="box-shadow: none;"><h5
                                            style="display: inline;">ปานกลาง </h5></label>
                                    <label style="margin-left: 10px;"><input class="form-control" type="radio"
                                                                             name="level" value="1"
                                                                             style="box-shadow: none;"><h5
                                            style="display: inline;">ง่าย </h5></label>
                                </div>
                            </div>

                        </div>
                        <div class="row form-group">
                            <div class="col-md-4 text-right">
                                <h5><label style="font-weight: 100">คะแนน :</label></h5>
                            </div>
                            <div class="col-md-2" style="padding: 0;">
                                <input class="form-control" id="questionScoreForCreateQuestion" style="width: 100%">
                            </div>
                        </div>

                        <div class="row form-group" id="answerInput" style="display: none">
                            <div class="col-md-4">
                                <h5>ตัวเลือกคำตอบ</h5>
                            </div>
                            <div class="row">

                                <div class="col-md-8 col-md-offset-2" style="padding: 0px" align="left">
                                    <span class="input-group">
                                        <input class="form-control" type="text" id="choice1"/>
                                        <span class="input-group-addon choiceRadioAddon">
                                            <input class="correctRadio" type="radio" name="choiceRadio"
                                                   value="1"/>
                                            <div class=" glyphicon glyphicon-ok"></div>
                                    </span>
                                    </span>

                                    <br>

                                    <div class="input-group">
                                        <input class="form-control" type="text" id="choice2"/>
                                    <span class="input-group-addon choiceRadioAddon">
                                            <input class="correctRadio" type="radio" name="choiceRadio" value="2"/>
                                        <div class=" glyphicon glyphicon-ok"></div>
                                    </span>
                                    </div>

                                    <br>

                                    <div class="input-group">
                                        <input class="form-control" type="text" id="choice3"/>
                                        <span class="input-group-addon choiceRadioAddon">
                                            <input class="correctRadio" type="radio" name="choiceRadio" value="3"/>
                                            <div class=" glyphicon glyphicon-ok"></div>
                                    </span>
                                    </div>

                                    <br>

                                    <div class="input-group">
                                        <input class="form-control" type="text" id="choice4"/>
                                        <span class="input-group-addon choiceRadioAddon">
                                            <input class="correctRadio" type="radio" name="choiceRadio" value="4"/>
                                            <div class=" glyphicon glyphicon-ok "></div>
                                    </span>
                                    </div>

                                </div>
                            </div>
                        </div>
                        <%--End Objective--%>

                        <div class="col-md-offset-1" id="submitBtnContainer" hidden align="center">
                            <button class="btn btn-primary" id="submitCreateBtn" data-dismiss="modal">ตกลง</button>
                            <button class="btn btn-warning" data-dismiss="modal">ยกเลิก</button>
                        </div>


                    </div>
                    <!--Modal footer-->
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<!-- End Modal Create Question -->


<!-- End Create Question Modal -->

<script src="../../../../resources/js/pageScript/exam/createQuestionModal.js" charset="UTF-8"></script>

<style>
    .input-group-addon.success {
        color: rgb(255, 255, 255);
        background-color: rgb(92, 184, 92);
        border-color: rgb(76, 174, 76);
    }

    .input-group-addon.warning {
        color: rgb(255, 255, 255);
        background-color: rgb(240, 173, 78);
        border-color: rgb(238, 162, 54);
    }
</style>