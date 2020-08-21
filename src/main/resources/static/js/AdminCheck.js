$(document).ready(function (){
    var unInp=document.getElementById("un");
    var pwInp=document.getElementById("pw");
    $("#check-btn").click(function(){
        if(unInp.value=="admin"&&pwInp.value=="123123"){
            window.location.href="/AdminFunc";
        }else{
            alert("Wrong input!");
        }
    })
})