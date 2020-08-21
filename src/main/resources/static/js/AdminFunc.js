window.onload=function () {
    //数据库初始化
    var startBtn1=document.getElementById("ini-btn");
    startBtn1.onclick=function () {
        window.location.href="/InitialPage"
    };
};


$("input[type='file']").change(function(){
    var file = this.files[0];
    console.log(file);
});
