/**
 * Created by Phuthikorn_T on 14/8/2558.
 */

//$('#submitCreateBtn').text('ยืนยัน');
//$('#createQuestModalTitle').text('แก้ไขข้อสอบ');

$(document).ready(function () {
    listAllQuestion();

    $(".actionDeleteBtn").on('click', function () {
        var questionId = parseInt($(".actionDeleteBtn").parents('tr').attr('questionId'));
        deleteQuestion(questionId);
    })

    $(".actionEditBtn").on('click',function(){
        $('#submitCreateBtn').text('ยืนยัน');
        $('#createQuestModalTitle').text('แก้ไขข้อสอบ');
    })

    $('.createQuestionBtn').on('click',function(){
        $('#createQuestModalTitle').text('สร้างข้อสอบ');
        $('#submitCreateBtn').text('ตกลง');
    })

})

editQuestion = function () { // THIS FUNCTION IS CALLED FROM webapp/WEB-INF/pages/exam/modal/createQuestionModal.jsp
    // IF THIS ISN'T WORKING TRY PUT THE CODE IN editQuestion() in createQuestionModal.jsp instead
    alert("manageQuestion.js Yeaahhh");

    var categoryName = $("#categoryInputForCreateQuestion").val();
    var subCategoryName = $("#subCategoryInputForCreateQuestion").val();
    var questionTypeString = $("#select-QuestionType").val();
    var score = $("#questionScoreForCreateQuestion").val();
    var choiceDesc = null;
    var questionDesc = $("#questionDescription").val();
    var difficulty = $("input[name='level']:checked").val();
    var correctChoice = $(".correctRadio:checked").val();

    var questionType = null;
    if (questionTypeString == 'Objective') {
        questionType = 1;
    } else if (questionType == 'Subjective') {
        questionType = 2;
    }

    if (questionType == 1) {
        choiceDesc = new Array($('#choice1').val(), $('#choice2').val(), $('#choice3').val(), $('#choice4').val());
    }

    var dat = $.ajax({
        type: 'POST',
        url: '/TDCS/exam/editQuestion',
        data: {
            categoryName: categoryName,
            subCategoryName: subCategoryName,
            questionDesc: questionDesc,
            choiceDescArray: choiceDesc.toString(),
            correctChoice: parseInt(correctChoice),
            questionType: questionType,
            difficulty: parseInt(difficulty),
            score: parseInt(score)
        }
        ,
        success: function () {
            alert('Success');
        },
        error: function () {
            alert('Error');
        }
    })
}

var listAllQuestion = function () {
    var questionList = $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/TDCS/exam/getAllQuestion",
            async: false,
            success: function (questionList) {
                questionList.forEach(function (quest) {
                    console.log(quest.id);
                    console.log(quest);
                    var createDate = new Date(quest.createDate);
                    var formattedDate = createDate.getDate()+"/"+createDate.getMonth()+"/"+createDate.getFullYear();

                    $("#tableBody").append('<tr questionId=' + quest.id + '>' +
                    '<td class="questionId">' + quest.id + '</td>' +
                    '<td class="questionDescription">' + quest.description + '</td>' +
                    '<td class="questionType">' + quest.questionTypeDesc + '</td>' +
                    '<td class="questionDifficulty">' + quest.difficultyDesc + '</td>' +
                    '<td class="questionScore">' + quest.score + '</td>' +
                    '<td class="questionCategory">' + quest.categoryName + '</td>' +
                    '<td class="questionSubCategory">' + quest.subCategoryName + '</td>' +
                    '<td class="questionCreateBy">' + quest.createByEmpId + '</td>' +
                    '<td class="questionCreateDate">' + formattedDate+ '</td>' +
                    '<td>' +
                    '<div class="btn-group">' +
                    '<button class="btn dropdown-toggle" data-toggle="dropdown">' +
                    'เลือก<span class="caret"></span>' +
                    '</button>' +
                    '<ul class="dropdown-menu">' +
                    '<li><a href="#">ดู</a></li>' +
                    '<li><a class="actionEditBtn" data-toggle = "modal" data-target = "#createQuest">แก้ไข</a></li>' +
                    '<li><a class="actionDeleteBtn">ลบ</a></li>' +
                    '</ul>' +
                    '</div>' +
                    '</td>' +
                    "</tr>")
                })

            }
        }
    )
}

var deleteQuestion = function (questionId) {
    $.ajax({
        type: 'POST',
        url: '/TDCS/exam/deleteQuestion',
        data: {
            questionId: questionId
        },
        success: function () {
            alert("Delete Success");
        }, error: function () {
            alert("Failed");
        }
    })
}
