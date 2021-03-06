$(function () {
    initCityList();
    $("#registerBtn").click(function () {
        register();
    });
    addressLinkage();
});

function register() {
    var registerData = {};
    var t = $("form").serializeArray();
    $.each(t, function () {
        registerData[this.name] = this.value;
    });
    registerData.address = {
        province: registerData.province,
        city: registerData.city,
        area: registerData.area
    };
    console.log(registerData);
    $.ajax({
        type: 'POST',
        url: LOCALHOST + "/register",
        dataType: 'json',
        data: registerData,
        complete: function (XMLHttpRequest, textStatus) {
        },
        success: function (data) {
            console.log(data)
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function initCityList() {
    $.ajax({
        type: 'GET',
        url: LOCALHOST + "/address/linkage",
        dataType: 'json',
        // data: data,
        complete: function (XMLHttpRequest, textStatus) {
        },
        success: function (data) {
            var $list = $("select[name='province']");
            var node = '';
            $.each(data, function (index, element) {
                node = '<option value="' + this + '">' + this + '</option>';
                $list.append(node);
            });
        },
        error: function (err) {
            console.log(err);
        }
    });
}
