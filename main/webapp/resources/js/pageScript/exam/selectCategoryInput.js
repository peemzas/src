






var getSearchCategoryInputValue = function () {
    return $('#selectCategoryToSelection').children('.category:selected').attr('categoryId');
}

var getSearchSubCategoryInputValue = function () {
    return $('#selectSubCategoryToSelection').children('.subCategory:selected').attr('subCategoryName');
}

var updateCategoryList = function () {
    clearCategoryList()
    var ajaxDat = $.ajax({
        type: "POST",
        url: "/TDCS/exam/getAllCategory",
        success: function (catList) {
            catList.forEach(function (category) {
                $('#selectCategoryToSelection').append('<option class="category" categoryId="' + category.id + '"' +
                ' categoryName="' + category.name + ' value=' + category.id + '">' + category.id + ' : ' + category.name + '')
            })
        },
        error: function () {
            alert("error in updateCategoryList()")
        }
    })
}

var updateSubCategoryList = function () {
    var catSelect = $('#selectCategoryToSelection');
    var subcatSelect = $('#selectSubCategoryToSelection');
    subcatSelect.empty();
    subcatSelect.append("<option selected value=''></option>");
    var data = null;
    if (catSelect.children('.category:selected').val()) {
        data = catSelect.children('.category:selected').attr('categoryId')
    } else(
        data = ""
    )
    console.log(data.length)

    var ajaxDat = $.ajax({
        type: "POST",
        url: "/TDCS/exam/getAllSubCategoryInCategory",
        data: {
            categoryId: data
        },
        success: function (item) {
            if (jQuery.isEmptyObject(item)) {
                subcatSelect.empty();
                if (catSelect.val() == "") {
                    subcatSelect.append("<option selected value=''></option>");
                }
            }
            if (item) {
                item.forEach(function (item) {
                    subcatSelect.append('<option class="subCategory" subCategoryId="' + item.id + '"' +
                    ' subCategoryName="' + item.name + '" subCategoryId="' + item.id + '"' +
                    'value="' + item.name + '""> ' + item.name+
                    '</option>')
                })
            }
        },
        error: function () {
            alert("error in updateSubCategoryList()")
        }
    })
}
var clearCategoryList = function () {
    var categorySelect = $('#selectCategoryToSelection');
    categorySelect.empty();
    categorySelect.append("<option selected value=''></option>");
}

$(document).ready(function () {
    clearCategoryList()
    updateCategoryList()
})
$('#selectCategoryToSelection').on('change', function () {
    console.log("event on category change")
    $('selectSubCategoryToSelection').children('.subCategory').remove()
    updateSubCategoryList()
})

var catAndSubcatSelectNothing = function () {
    $('#selectCategoryToSelection option:selected').removeAttr("selected");
    $('#selectCategoryToSelection').val("");
    $('#selectCategoryToSelection option[value=""]').attr('selected', 'selected');
    updateSubCategoryList();

}



$("#selectCategoryToSelection").keyup(function (e) {
    if (e.which > 0) {
        e.preventDefault();
        listcatSelectInput();

    }
});
function listcatSelectInput() {
    //alert("LOV");
    var availableall = [];
    var categoryId = $("#selectCategoryToSelection").val();

    var data = $.ajax({
        type: "POST",
        url: "/TDCS/exam/getAllCategory",

        async: false,

        success: function (data) {
            data.forEach(function (value) {
                availableall.push(value.id + ' : ' + value.name);
            });
            //alert("SUCC");
        },
        error: function (data) {
            alert('error while request...');
        }
    });
    var search = $("#selectCategoryToSelection").val();
    $("#selectCategoryToSelection").typeahead('destroy').typeahead({
        source: availableall,
        minLength: 0,
        items: 20,
        maxLength: 2
    }).focus().val('').keyup().val(search);
};


/////To Dropdown
$("#selectCategoryToSelection").on('change', function () {

        $("#selectSubCategoryToSelection").empty();
        var categoryId = $("#selectCategoryToSelection").val();

        var subcategoryName = $("#selectSubCategoryToSelection").val();
        //categoryId += " ";
        //var length = categoryId.length
        //alert(length);

        //categoryId = categoryId.substr(0, categoryId.indexOf(' '));
        if(categoryId !=""){
            if(categoryId.indexOf(':')!=-1){
                categoryId.indexOf(':');
                var categoryId2 =  categoryId.substr(0, categoryId.indexOf(' '));
                categoryId = categoryId2;
                var data = $.ajax({
                    type: "POST",
                    url: "/TDCS/exam/getSubCategoryToDropDown",
                    data: {
                        categoryId: categoryId
                        //subcategoryName: subcategoryName
                    },
                    async: false,

                    success: function (data) {
                        data.forEach(function (value) {
                            $("#selectSubCategoryToSelection").append(
                                '<option >' + value.SubCategory.name + '</option>'
                            )
                        });
                    },
                    error: function (data) {
                        alert('error while request...');
                    }
                });
                if (($("#selectSubCategoryToSelection").val() == null)) {
                    $("#selectSubCategoryToSelection").append(
                        '<option >' + "ไม่มีหัวข้อเรื่องภายใต้หมวดหมู่นี้" + '</option>'
                    )
                }
                else if (($("#sSubCat").val() != null)) {
                    $("#sSubCat").append(
                        '<option value="">' + "ทั้งหมด" + '</option>'
                    )
                }
            }else{
                //console.log(categoryId+" 1 part");

                var data = $.ajax({
                    type: "POST",
                    url: "/TDCS/exam/getSubCategoryToDropDown",
                    data: {
                        categoryId: categoryId
                        //subcategoryName: subcategoryName
                    },
                    async: false,

                    success: function (data) {
                        data.forEach(function (value) {
                            $("#selectSubCategoryToSelection").append(
                                '<option >' + value.SubCategory.name + '</option>'
                            )
                        });

                    },
                    error: function (data) {
                        alert('error while request...');
                    }

                });
                if (($("#selectSubCategoryToSelection").val() == null)) {
                    $("#selectSubCategoryToSelection").append(
                        '<option >' + "ไม่มีหัวข้อเรื่องภายใต้หมวดหมู่นี้" + '</option>'
                    )
                }
                else if (($("#sSubCat").val() != null)) {
                    $("#sSubCat").append(
                        '<option value="">' + "ทั้งหมด" + '</option>'
                    )
                }
            }

        }
    }
)



