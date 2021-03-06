var $list;

$(function () {
    initList();
    $("#payBtn").click(function () {
        getPay();
    });
    addressLinkage();

    $("input[type='datetime-local']").attr("value", getFormat());
    $("input[type='checkbox']").change(function () {
        $(".addressInput").toggle();
        $(".oldAddress").toggle();
    });
});

function getFormat() {
    var format = "";
    var nTime = new Date();
    format += nTime.getFullYear() + "-";
    format += (nTime.getMonth() + 1) < 10 ? "0" + (nTime.getMonth() + 1) : (nTime.getMonth() + 1);
    format += "-";
    format += nTime.getDate() < 10 ? "0" + (nTime.getDate()) : (nTime.getDate());
    format += "T";
    format += nTime.getHours() < 10 ? "0" + (nTime.getHours()) : (nTime.getHours());
    format += ":";
    format += nTime.getMinutes() < 10 ? "0" + (nTime.getMinutes()) : (nTime.getMinutes());
    format += ":00";
    return format;
}

function initList() {
    var node;
    $list = $(".list");
    $list.empty();
    if (sessionStorage.getItem("shoppingCar")) {
        var shoppingCar = JSON.parse(sessionStorage.getItem("shoppingCar"));
        $.each(shoppingCar, function (index, element) {
            node = '<li>\
                    <section class="list-left">\
                        <h5 class="title">' + element.name + '</h5>\
                        <p class="catpath">单价：' + element.price + '元/kg</p>\
                    </section>\
                    <section class="list-right">\
                        <span class="date">重量:' + element.weight + 'KG</span>\
                        <span class="cityname">删除</span>\
                    </section>\
                    <div class="clearfix"></div>\
                </li>';
            $list.append(node);
        })
    } else {
        cleanList();
    }
}

function getPay() {
    var temData = {};
    var data = {};
    $.each($("form").serializeArray(), function (index, element) {
        temData[element.name] = element.value;
    });
    data.orderDetails = JSON.parse(sessionStorage.getItem("shoppingCar"));
    data.orderItem = {
        address: {
            area: temData.area,
            city: temData.city,
            detail: temData.detail,
            province: temData.province
        },
        collectEndTime: (temData.collectEndTime + ":00").replace(/T/g, ' '),
        collectFromTime: (temData.collectFromTime + ":00").replace(/T/g, ' ')
    };
    if (temData.useOldAddress == 1) {
        data.orderItem.address = oldAddress;
    }
    console.log(data);
    $.ajax({
        type: 'post',
        url: LOCALHOST + "/order",
        // dataType: 'json',
        headers: {
            "Accept": "text/plain; charset=utf-8",
            "Content-Type": "application/json; charset=utf-8"
        },
        data: JSON.stringify(data),
        complete: function (XMLHttpRequest, textStatus) {
        },
        success: function (data) {
            console.log(data);
            if (data == "创建成功") {
                sessionStorage.removeItem("shoppingCar");
                Toast("订单已提交", 2000);
                cleanList();
                $('#myModal').modal('hide');
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function cleanList() {
    var node = '<span>你还没有添加任何废品哦～</span>';
    $list.empty().append(node);
    $(".btn").hide();
}