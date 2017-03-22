function check() {
    for(var i=0;i<document.registerform.elements.length-1;i++) {
       if(document.registerform.elements[i].value=="") {
           alert("当前表单不能有空项");
           document.registerform.elements[i].focus();
           return false;
       }
    }
    return true;
}

function checkGood(){
	
}