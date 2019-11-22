//todo: user变成两个, 把很多换成bind
// tab control
var tab = document.querySelector('.tab');
var lis = tab.querySelectorAll('li');
var items = document.querySelectorAll('.database-item');
for (var i = 0; i < lis.length; ++i) {
    lis[i].setAttribute('index', i);
    lis[i].onclick = function () {
        for (var i = 0; i < lis.length; ++i) {
            lis[i].className = '';
            items[i].style.display = 'none';
            console.log('set lis and item ' + i)
        }
        var index = this.getAttribute('index');
        currentPage = index;
        lis[index].className = 'active';
        items[index].style.display = 'block';
        console.log(index);
    }
}


function show(foo) {
    console.log(foo);
    foo.css('display', 'block');
}

function unshow(foo) {
    foo.css('display', 'none');
}

function verifyPerson() {
    var userName = $("#person_in_username").val();
    var name = $("#person_in_name").val();
    var age_str = $("#person_in_age").val();
    var telenum = $("#person_in_telenum").val();
    var flag = true;
    var un_maxlen = 10;
    var telregex = /^177\d{8}|138\d{8}|136\d{8}$/;
    var username_regex = /^hnu.*|HNU.*$/;


    var curr;
    curr = $("#username_error");
    if (!username_regex.test(userName)) {
        curr.text("User name must start with hnu/HNU");
        show(curr);
        flag = false;
    }
    else if (userName.length > 10) {
        curr.text("The username is too long");
        show(curr);
        flag = false;
    } else {
        unshow(curr);
    }
    curr = $("#name_error");
    if (name.length === 0) {
        curr.text('Name should not be empty');
        curr.show();
        flag = false;
    } else {
        curr.hide();
    }

    if (age_str.length > 0) {
        var age = parseInt(age_str);
        curr = $("#age_error");
        if (age < 0 || age > 150) {
            curr.text("You've entered a wrong age");
            show(curr);
            flag = false;
        } else if (!(/\d{1,3}$/.test(age_str))) {
            curr.text('Not a valid age number(0-160)');
            show(curr);
        } else {
            unshow(curr);
        }
    }

    curr = $("#telenum_error");
    if (telenum !== '' && !telregex.test(telenum)) {
        curr.text('Wrong telephone number format');
        show(curr);
        flag = false;
    } else {
        unshow(curr);
    }
    return flag;
}

function verifyUser() {
    var userName = $("#in_user_username").val();
    var password = $("#in_user_password").val();
    var flag = true;
    var curr = $("#user_username_error");
    var username_regex = /^hnu.*|HNU.*$/;
    if (!username_regex.test(userName)) {
        curr.text("User name must start with hnu/HNU");
        show(curr);
        flag = false;
    }
    else if (userName.length > 10) {
        curr.text("The username is too long");
        show(curr);
        flag = false;
    } else {
        unshow(curr);
    }
    curr = $("#user_password_error");
    if (password.length <= 8 || password.length > 16) {
        curr.text('password should contains (8 to 16) characters');
        show(curr);
        flag = false;
    } else {
        unshow(curr);
    }
    return flag;
}


// $("#person_insert_btn").click(function () {
//     if (!verifyPerson())
//         return;
//     var userForm = $("#personForm");
//     userForm.attr('action', '/person/insert');
//     userForm.submit();
//     console.log('submitted insert');
// });不
function userInsertCheckEmpty() {

    var userName = $("#in_user_username").val();
    var password = $("#in_user_password").val();
    return !(userName === '' || password === '');
}

function userRemoveCheckEmpty() {

    var userName = $("#in_user_username").val();
    return userName !== '';
}

function personInsertCheckEmpty() {

    var userName = $("#person_in_username").val();
    var name = $("#person_in_name").val();
    var age_str = $("#person_in_age").val();
    var telenum = $("#person_in_telenum").val();
}


function personRemoveCheckEmpty() {

    var userName = $("#person_in_username").val();
    var name = $("#person_in_name").val();
    var age_str = $("#person_in_age").val();
    var telenum = $("#person_in_telenum").val();
}

$("#person_insert_btn").bind("click", function () {
    if (!verifyPerson())
        return;
    $.ajax({
        type: "post",
        url: "person/insert.aspx",
        dataType: "json",
        data: $('#personForm').serialize(),
        //那么什么时候用contentType: "application/json;charset=utf-8"呢，后台不是接收单个字符串，而是一个实体类时就用它了。
        // contentType: "application/json;charset=utf-8",
        success: function (result) {
            alert('success: ' + result.msg); //拿到结果
        },
        fail: function (result) {
            alert('fail: ' + result.msg);
        }
    });
});

$("#person_remove_btn").bind('click', function () {
    // if (!verifyPerson())
    //     return;
    $.ajax({
        type: "post",
        url: "person/remove.aspx",
        // dataType: "json",
        data: $('#personForm').serialize(),
        //那么什么时候用contentType: "application/json;charset=utf-8"呢，后台不是接收单个字符串，而是一个实体类时就用它了。
        // contentType: "application/json;charset=utf-8",
        success: function (result) {
            alert('success: ' + result.msg); //拿到结果
        },
        fail: function (result) {
            alert('fail: ' + result.msg);
        }
    });
    // var userForm = $("#personForm");
    // userForm.attr('action', '/person/remove');
    // userForm.submit();
    // console.log('submitted remove');
});


$("#user_insert_btn").bind('click', function () {
    if (!verifyUser())
        return;
    console.log('click user insert');
    $.ajax({
        type: "post",
        url: "user/insert.aspx",
        dataType: "json",
        data: $('#userForm').serialize(),
        //那么什么时候用contentType: "application/json;charset=utf-8"呢，后台不是接收单个字符串，而是一个实体类时就用它了。
        // contentType: "application/json;charset=utf-8",
        success: function (result) {
            alert('success: ' + result.msg); //拿到结果
        },
        fail: function (result) {
            alert('fail: ' + result.msg);
        }
    });
    // userForm.attr('action', '/user/insert');
    // userForm.submit();
    // console.log('submitted insert');
});
$("#user_remove_btn").bind('click', function () {
    // if (!verifyUser())
    //     return;
    $.ajax({
        type: "post",
        url: "user/remove.aspx",
        dataType: "json",
        data: $('#userForm').serialize(),
        //那么什么时候用contentType: "application/json;charset=utf-8"呢，后台不是接收单个字符串，而是一个实体类时就用它了。
        // contentType: "application/json;charset=utf-8",
        success: function (result) {
            alert('success' + result.msg); //拿到结果
        },
        fail: function (result) {
            alert('fail:' + result.msg);
        }
    });
});


$("#queryDataase").bind('click', function () {
    // $.ajax({
    //     type: "get",
    //     url: "database"
    //     // dataType: "json",
    // });
    window.open("/database");
});
