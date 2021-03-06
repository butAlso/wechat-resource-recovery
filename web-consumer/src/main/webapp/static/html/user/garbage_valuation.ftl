<@override name="content">
<div class="container">
    <div class="page-header">
        <h1>智能估价系统
            <small>最新上线</small>
        </h1>
    </div>
    <form>
        <div class="form-group">
            <label for="">产品种类</label>
            <input type="email" class="form-control">
        </div>
        <div class="form-group">
            <label for="">产品型号</label>
            <input type="password" class="form-control">
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox">外壳受损
            </label>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox">屏幕损坏
            </label>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox">无法开机
            </label>
        </div>
        <div class="form-group">
            <label for="">上传产品照片</label>
            <input type="file">
            <p class="help-block">上传多张图片有助于准确估价哦～</p>
        </div>
        <button type="button" class="btn btn-block btn-default submit">提交
        </button>
    </form>
    <br>
    <div class="well" style="display: none">您的本次估价结果为：<span class="random"></span>元</div>
</div>
</@override>

<@override name="js">
<script>
    $(".submit").click(function () {
        Toast('正在进行智能估价，请稍后', 2000);
        setTimeout(valuation, 2000);

        function valuation() {
            /*400 - 700*/
            var value = parseInt(Math.random() * 300) + 400;
            $('.well .random').text(value);
            $(".well").fadeIn()
        }
    });
</script>
<#--<script src="/web-consumer/static/index/"></script>-->
</@override>
<@extends name="layout.ftl"/>
