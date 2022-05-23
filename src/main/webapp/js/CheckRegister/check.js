function blurPass(id) {

    var upperLetter = /[A-Z]/g;
    var lowerLetter = /[a-z]/g;
    var havenum = /[0-9]/g;

    if (id.value == "") {
    	
        document.getElementById("alertpw1").innerHTML = "*此欄為必填";
        return false
    }

    else if (!id.value.match(upperLetter)) {
    	
        document.getElementById("alertpw1").innerHTML = "*至少包含1大寫字母";
        return false;
    }

    else if (!id.value.match(lowerLetter)) {
    	
        document.getElementById("alertpw1").innerHTML = "*至少包含1小寫字母";
        return false;
    }

    else if (!id.value.match(havenum)) {
    	
        document.getElementById("alertpw1").innerHTML = "*至少包含1數字";
        return false;
    }

    else if (id.value.length < 6) {
    	
        document.getElementById("alertpw1").innerHTML = "*密碼不能少於6位數";
        return false;
        
    } else {
    	
        document.getElementById("alertpw1").innerHTML = "";
        return true;
 
    }
}


function blurRepass(id) {
	
    var password = document.getElementById("pwd1").value;

    if (id.value == "") {
    	
        document.getElementById("alertpw2").innerHTML = "*此欄為必填";
        return false;
 
    }

    else if (id.value != password) {
    	
        document.getElementById("alertpw2").innerHTML = "*請確認密碼一致!";
        return false;

    } else {
    	
        document.getElementById("alertpw2").innerHTML = "";
        return true;
        
    }
}

function blurName(id) {

    if (id.value == "") {
    	
        document.getElementById("alertname").innerHTML = "*此欄為必填";
        return false;

    } else {
    	
        document.getElementById("alertname").innerHTML = "";
        return true;
        
    }
}

function CheckPost() {
	
    if (blurPass(pwd1) && blurRepass(pwd2) && blurName(name1)) {
    	return true;
    	
    	} else {    
    		
    alert("請確認輸入符合規則!");
    return false;
    
    }
}