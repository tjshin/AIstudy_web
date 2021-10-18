class UserStorage {
    constructor() {
        if(window.XMLHttpRequest){
            this.xhr = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            this.xhr = ActiveXObject("Microsoft.XMLHTTP");
        } 
        //console.log(this.xhr);
    }
    
    sendKeyword() {
      return new Promise((resolve, reject) => {
 
        this.xhr.onreadystatechange = () => {
            if(this.xhr.readyState==4){
                if(this.xhr.status==200){
                   // alert(this.xhr.responseText);
                    resolve(this.xhr.responseText);
                }else{
                    reject(new Error(this.xhr.status));
                }
            }
        }
        let keyword = document.search.keyword.value;
        console.log('....'+ keyword.length);
 
        let params = "keyword=" + keyword;
        if(keyword.length >0){
     this.xhr.open("POST","suggest.jsp");
     this.xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            this.xhr.send(params);
        }else{
	hide('suggest');
}
      });
    }      
}
function select(selectedKeyword) {
    document.search.keyword.value = selectedKeyword;
     
    hide('suggest');
}
function show(id){
    if(id) {
        document.getElementById(id).style.display='';
    }
}
function hide(id){
    if(id) {
        document.getElementById(id).style.display='none';
    }
}