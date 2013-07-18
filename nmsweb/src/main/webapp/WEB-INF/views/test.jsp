<html>
<head>
    <title>링크클릭하면 목록이 부드럽게 펼쳐집니다.</title>

<style type="text/css">
.scroll-list {
font-size: 12px;
background-color: #efefef;
width: 200px;
height: 1px;
overflow: auto;
line-height:120%;
padding:10;
}
li a{
color: #333333;
}
</style>

<scRIPT LANGUAGE="Javascript">
<!--
function startCateScrollScroll() {
    setTimeout("slideCateScroll()", 10);
}
function slideCateScroll() {
    var Sel_Height=250;
        el = document.getElementById("scroll-list");
    if (el.heightPos == null || (el.isDone && el.isOn == false)) {
        el.isDone = false;
        el.heightPos = 1;
        el.heightTo = Sel_Height;
    } else if (el.isDone && el.isOn){
        el.isDone = false;
        el.heightTo = 1;
    }
    if (Math.abs(el.heightTo - el.heightPos) > 1) {
        el.heightPos += (el.heightTo - el.heightPos) / 10;
        el.style.height = el.heightPos + "px";
        startCateScrollScroll();
    } else {
    if (el.heightTo == Sel_Height) {
        el.isOn = true;
    } else {
        el.isOn = false;
    }
        el.heightPos = el.heightTo;
        el.style.height = el.heightPos + "px";
        el.isDone = true;
    }
}
//-->
</scRIPT>

</head>
<body>

<a href="# onclick="slideCateScroll()" style="background-color:999999;width:200;padding:10;color:white"><B>레이어 확장</B></a>

<div id="scroll-list" class="scroll-list">
    <ul>
<li><a href="#">HOME</a><BR />
<li><a href="#">자바스크립트</a><BR />
<li><a href="#">플래쉬</a><BR />
<li><a href="#">커뮤니티</a><BR />
<li><a href="#">사이트맵</a><BR />
<li><a href="#">HOME</a><BR />

    </ul>
</div>

</body>
</html>