function connettiDB(){


	var host = document.getElementById("host").value;
    var porta = document.getElementById("porta").value;
    var user = document.getElementById("user").value;
    var password = document.getElementById("password").value;



    var params = new reqParams("");
    params.addParams("host",host)
          .addParams("porta",porta)
          .addParams("user",user)
          .addParams("password",password)

    
    //Chiama la servlet passando i parametri ottenuti
    connettiDBReq("connettiDB", params.getParams);
}


function connettiDBReq(url , params) {
    var http = sendRequest(url);
    
    http.onreadystatechange = function() {
        //Gestisci i vari response
        if(http.readyState == 4 && http.status == 200) {

			if(http.responseText == "-1"){
                alert("E' occorso qualche problema col server. Riprovare.");
            }
			else{
        		location.href = http.responseText;
        	}
        }
    }
    
    http.send(params);
}