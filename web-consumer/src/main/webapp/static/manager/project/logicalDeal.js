var LOCALHOST = "http://localhost:8080/web-consumer/manager";
var baseUrl="http://localhost:8080/web-consumer/manager/index"
String.prototype.replaceAll=function (str1,str2) {
    return this.replace(new  RegExp(str1,"gm"),str2);
}

function selectInfo(param) {
    // var type=encodeURI(param)
    // window.location.href=(window.location.search==null)?baseUrl+ "?param='+type+'" : baseUrl+"?param='+type+'";
    // console.log(window.location.href)
    $.ajax({
        type:"GET",
        url:LOCALHOST+"/user/"+param,
        dataType:"html",
        complete: function(XMLHttpRequest,textStatus){

        } ,
        success: function(data){
            $(".main-content").html(data);
            // var url=window.location.href;
            // var stateObj = { app: "event" ,mod:"owner",act:"add"};
            // var hrefs=$.param(stateObj);
            // history.pushState(stateObj,"", hrefs);
            window.location.hash="?user=1"
            }
        ,
        error: function(error){
            console.log(error);
        }
    })
}


function categoryInfo(){
    $.ajax({
        type:"GET",
        url:LOCALHOST+"/garbage",
        dataType:"html",
        complete: function(XMLHttpRequest,textStatus){

        } ,
        success: function(data){
            $(".main-content").html(data);
        },
        error: function(error){
            console.log(error);
        }
    })
}


function receive(){
    $.ajax({
        type:"GET",
        url:LOCALHOST+"/message/receive",
        dataType:"html",
        complete: function(XMLHttpRequest,textStatus){

        } ,
        success: function(data){
            $(".main-content").html(data);
        },
        error: function(error){
            console.log(error);
        }
    })
}

$(function () {
    (function ($) {
        $.getUrlParam = function (name,hrefString) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
            var r = hrefString.substr(1).match(reg);
            return r ? decodeURIComponent(r[2]) : null;
        }
    })(jQuery);
    function getQueryString(key) {
        var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
        var result = '?userKind=0&userName=%E4%B8%81%E5%8D%8E'.substr(1).match(reg);
        return result ? decodeURIComponent(result[2]) : null;
    }
})